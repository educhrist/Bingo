package client;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/*
 * @author Wagner Dias
 */
public class JTestingPanel extends JPanel {

	private ArrayList<JButton> botoes;
	public static final String SERVER_HOSTNAME = "localhost";
    public static final int SERVER_PORT = 2002;
    static JLabel label = null;
    static Sender sender = null;
    
	public static void main(String[] args) {
		
		//Swing
		
		
				JTestingPanel gst = new JTestingPanel();
				JFrame f = new JFrame("Bingo Law");
				f.getContentPane().add(gst);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.pack();
				f.setVisible(true);
		
		//Sockets
		BufferedReader in = null;
        PrintWriter out = null;
        
        try {
           // Connect to Bingo Law
           Socket socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);
           in = new BufferedReader(
               new InputStreamReader(socket.getInputStream()));
           out = new PrintWriter(
               new OutputStreamWriter(socket.getOutputStream()));
           System.out.println("Connected to server " +
              SERVER_HOSTNAME + ":" + SERVER_PORT);
        } catch (IOException ioe) {
           System.err.println("Can not establish connection to " +
               SERVER_HOSTNAME + ":" + SERVER_PORT);
           ioe.printStackTrace();
           System.exit(-1);
        }
 
        // Create and start Sender thread
        sender = new Sender(out);
 
        try {
           // Read messages from the server and print them
            String message;
           while ((message=in.readLine()) != null) {
//               System.out.println(message);
        	   label.setText(message);
           }
        } catch (IOException ioe) {
           System.err.println("Connection to server broken.");
           ioe.printStackTrace();
        }
	}

	private static final long serialVersionUID = 4221317269750527161L;

	public JTestingPanel() {
		
		botoes = new ArrayList<JButton>(30);
		setLayout(new GridLayout(7, 5));
		String bingo = "BINGO";
		for (int i = 0; i < 5; i++) {
		final JButton buttoncol = new JButton(String.valueOf(bingo.charAt(i)));
		buttoncol.setBackground(Color.white);
		add(buttoncol);
		botoes.add(buttoncol);
		buttoncol.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				sender.send("coluna " + b.getText());
				
			}
		});
		}
		
		
		
		for (int i = 5; i < 29; i++) {

			if (i == 17) {
				//JButton button = new JButton("");
				label = new JLabel("Sorteio");
				add(label);
				//button.setEnabled(false);
			}

			JButton button = new JButton("" + i);
			button.setBackground(Color.white);
			add(button);
			botoes.add(button);
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				//System.out.println(e);
				
				JButton b = (JButton)e.getSource();
				if(b.getBackground().equals(Color.green)){
					b.setBackground(Color.white);
				}else{
					b.setBackground(Color.green);
				}
				System.out.println();
				}
			});
		}
		JButton buttonBingo = new JButton("BINGO!");
		add(buttonBingo);
		botoes.add(buttonBingo);
		buttonBingo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sender.send("bingo");
			}
		});
	}



}
