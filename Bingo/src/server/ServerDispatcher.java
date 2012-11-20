package server;

/**Lucas was here.
 * Nakov Chat Server
 * (c) Svetlin Nakov, 2002
 *
 * ServerDispatcher class is purposed to listen for messages received
 * from clients and to dispatch them to all the clients connected to the
 * chat server.
 */

import java.net.*;
import java.util.*;

public class ServerDispatcher extends Thread {
	private Vector<String> mMessageQueue = new Vector<String>();
	private Vector<ClientInfo> mClients = new Vector<ClientInfo>();

	/**
	 * Adds given client to the server's client list.
	 */
	public synchronized void addClient(ClientInfo aClientInfo) {
		String aMessage = "joined the game";
		Socket socket = aClientInfo.mSocket;
		String senderIP = socket.getInetAddress().getHostAddress();
		String senderPort = "" + socket.getPort();
		aMessage = senderIP + ":" + senderPort + " : " + aMessage;
		System.out.println(aMessage);
		mClients.add(aClientInfo);
	}

	/**
	 * Deletes given client from the server's client list if the client is in
	 * the list.
	 */
	public synchronized void deleteClient(ClientInfo aClientInfo) {
		int clientIndex = mClients.indexOf(aClientInfo);
		if (clientIndex != -1)
			mClients.removeElementAt(clientIndex);
	}

	/**
	 * Adds given message to the dispatcher's message queue and notifies this
	 * thread to wake up the message queue reader (getNextMessageFromQueue
	 * method). dispatchMessage method is called by other threads
	 * (ClientListener) when a message is arrived.
	 */
	public synchronized void dispatchMessage(String aMessage) {

		mMessageQueue.add(aMessage);
		notify();
	}

	/**
	 * @return and deletes the next message from the message queue. If there is
	 *         no messages in the queue, falls in sleep until notified by
	 *         dispatchMessage method.
	 */
	private synchronized String getNextMessageFromQueue()
			throws InterruptedException {
		while (mMessageQueue.size() == 0)
			wait();
		String message = (String) mMessageQueue.get(0);
		mMessageQueue.removeElementAt(0);
		return message;
	}

	/**
	 * Sends given message to all clients in the client list. Actually the
	 * message is added to the client sender thread's message queue and this
	 * client sender thread is notified.
	 */
	private synchronized void sendMessageToAllClients(String aMessage) {
		for (int i = 0; i < mClients.size(); i++) {
			ClientInfo clientInfo = (ClientInfo) mClients.get(i);
			clientInfo.mClientSender.sendMessage(aMessage);
		}
	}

	/**
	 * Infinitely reads messages from the queue and dispatch them to all clients
	 * connected to the server.
	 */
	public void run() {
		try {
			while (true) {
				String message = getNextMessageFromQueue();
				sendMessageToAllClients(message);
			}
		} catch (InterruptedException ie) {
			ie.printStackTrace();
			// Thread interrupted. Stop its execution
		}
	}

	public synchronized void declareWinner(ClientInfo clientInfo) {
		String aMessage = "won the game";
		Socket socket = clientInfo.mSocket;
		String senderIP = socket.getInetAddress().getHostAddress();
		String senderPort = "" + socket.getPort();
		aMessage = senderIP + ":" + senderPort + " : " + aMessage;
		System.out.println(aMessage);
		mMessageQueue.add(aMessage);
		notify();

	}

}