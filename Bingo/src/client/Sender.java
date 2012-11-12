package client;

import java.io.PrintWriter;

class Sender
{
private PrintWriter mOut;
 
public Sender(PrintWriter aOut)
{
        mOut = aOut;
}
 
    /**
     * Until interrupted reads messages from the standard input (keyboard)
     * and sends them to the chat server through the socket.
     */
public void send(String message)
{

mOut.println(message);
mOut.flush();
}
}