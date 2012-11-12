package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Lucas
 *
 */
public class Bingo {

	/**
	 * Codigo derivado do Nakov Chat Server.
	 * Nakov Chat Server
	 * (c) Svetlin Nakov, 2002
	 * http://www.nakov.com
	 */
	public static final int LISTENING_PORT = 2002;
	public static int minClients = 2;
	public static void main(String[] args) {
		
//		Ticket a = new Ticket();
//		
//		Sorteio b = new Sorteio();
//		
//		System.out.println(b.sorteia());
//		System.out.println(b.sorteia());
//		System.out.println(b.sorteia());
//		System.out.println(a.getMap());
//		System.out.println(a.getList());
//		System.out.println(b.getLista());
		
//		Sorteio sorteio = new Sorteio();
//		Ticket ticket = new Ticket();
//		 Scanner scanner = new Scanner(System.in);
//		 String in;
//		while(true){
//			System.out.println(ticket.getList());
//			System.out.println(sorteio.sorteia());
//			in = scanner.nextLine();
//			System.out.println("IN foi:" + in);
//			if(in.equals("bingo")){
//				if(Verifica.verificaBingo(sorteio, ticket)){
//					System.out.println("Parabens, mané.");
//					break;
//				}else{
//					System.out.println("MENTIRA!");
//				}
//			}else if(in.contains("coluna")){
//				if(Verifica.verificaColuna(in, sorteio, ticket)){
//					System.out.println("Parabens, mané.");
//					break;
//				}else{
//					System.out.println("MENTIRA!");
//				}
//			}
//		}
		
		// Open server socket for listening
        ServerSocket serverSocket = null;
        try {
           serverSocket = new ServerSocket(LISTENING_PORT);
           System.out.println("Bingo Law started on port " + LISTENING_PORT);
        } catch (IOException se) {
           System.err.println("Can not start listening on port " + LISTENING_PORT);
           se.printStackTrace();
           System.exit(-1);
        }
 
        // Start ServerDispatcher thread
        ServerDispatcher serverDispatcher = new ServerDispatcher();
        serverDispatcher.start();
        
        Sorteio sorteio = new Sorteio(serverDispatcher);
        // Accept and handle client connections
        int clients = 0;
        while (true) {
           try {
               Socket socket = serverSocket.accept();
               clients++;
               if((clients == minClients) && !(sorteio.isAlive()) && !(sorteio.isInterrupted())){
            	   sorteio.run();
               }
               ClientInfo clientInfo = new ClientInfo();
               clientInfo.mSocket = socket;
               ClientListener clientListener =
                   new ClientListener(clientInfo, serverDispatcher, sorteio);
               ClientSender clientSender =
                   new ClientSender(clientInfo, serverDispatcher);
               clientInfo.mClientListener = clientListener;
               clientInfo.mClientSender = clientSender;
               clientListener.start();
               clientSender.start();
               serverDispatcher.addClient(clientInfo);
           } catch (IOException ioe) {
               ioe.printStackTrace();
           }
        }
        
		
	}
	
	
}
