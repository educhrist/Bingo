package server;

/**Lucas was here.
 * Nakov Chat Server - (c) Svetlin Nakov, 2002
 *
 * ClientListener class is purposed to listen for client messages and
 * to forward them to ServerDispatcher.
 */

import java.io.*;
import java.net.*;

public class ClientListener extends Thread {
	private ServerDispatcher mServerDispatcher;
	private ClientInfo mClientInfo;
	private BufferedReader mIn;
	private Sorteio mSorteio = null;

	public ClientListener(ClientInfo aClientInfo,
			ServerDispatcher aServerDispatcher, Sorteio aSorteio)
			throws IOException {
		mClientInfo = aClientInfo;
		mServerDispatcher = aServerDispatcher;
		mSorteio = aSorteio;
		Socket socket = aClientInfo.mSocket;
		mIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	}

	/**
	 * Verify if the client completed a column, the entire ticket, or nothing.
	 * 
	 * @param message
	 */
	private void verifyClaim(String message) {
		Ticket ticket = mClientInfo.ticket;
		if (message.equals("bingo")) {
			if (Verifica.verificaBingo(mSorteio, ticket)) {
				mSorteio.interrupt();
				mServerDispatcher.declareWinner(mClientInfo);
			} 
		} else if (message.contains("coluna")) {
			if (Verifica.verificaColuna(message, mSorteio, ticket)) {
				mServerDispatcher.dispatchMessage("Coluna " + message.charAt(7)
						+ " foi completada.");
			} 
		}

	}

	/**
	 * Until interrupted, reads messages from the client socket, forwards them
	 * to the server dispatcher's queue and notifies the server dispatcher.
	 */
	public void run() {
		try {
			while (!isInterrupted()) {
				String message = mIn.readLine();
				if (message == null)
					break;
				System.out.println(message);
				verifyClaim(message);
			}
		} catch (IOException ioex) {
			ioex.printStackTrace();
			// Problem reading from socket (communication is broken)
		}

		// Communication is broken. Interrupt both listener and sender threads
		mClientInfo.mClientSender.interrupt();
		mServerDispatcher.deleteClient(mClientInfo);
	}

}