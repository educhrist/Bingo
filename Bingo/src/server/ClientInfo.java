package server;
/**Lucas was here.
 * Nakov Chat Server
 * (c) Svetlin Nakov, 2002
 *
 * ClientInfo class contains information about a client, connected to the server.
 */
 
import java.net.Socket;
 
public class ClientInfo
{
    public Socket mSocket = null;
    public ClientListener mClientListener = null;
    public Ticket ticket = new Ticket();
    public ClientSender mClientSender = null;
    public Sorteio mSorteio = null;
}