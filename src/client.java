//package Networkfinal;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.naming.ldap.Control;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class client {

	private static Socket socket;
	private static BufferedReader in;
	private static BufferedReader inFromConsole;
	private static BufferedWriter out;
	private static String Name;
	private static ClientThread recieving;
	private static int TTL  ;
	private static GUI gui;
    private static String message =null ;
	public client(GUI gui) throws IOException{
		this.gui=gui;
		String host = "localhost";
		int port = 6000;
		InetAddress address = InetAddress.getByName(host);
		runThis(address, port);

	}
	
	public static void ConnectToServer(InetAddress address, int port)
			throws IOException {
	 	socket = new Socket(address, port);
		out = new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream()));
	 	in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		TTL =2;
	}

	public static boolean join(String name) throws IOException {
		out.write(name + '\n');
		out.flush();
		String signal = in.readLine().trim();
		if (signal.equals("ACCEPTED")) {
			recieving =new ClientThread(socket,in,gui);
			recieving.start();
			return true;
		}
		
		return false;
	}

	public static String getName() {
		return Name;
	}

	public static void setName(String name) {
		Name = name;
	}


	public static void printRequest() throws IOException {
		out.write("GetUsers \n");
		out.flush();
		JOptionPane.showMessageDialog(null,"Write the name of the user you want to chat with,followed by # and then your message");
	
		
	}



	public static void Chat(String source,String NameOfDistenation,int TTL, String message)
			throws IOException {
		String s = source+"#"+NameOfDistenation+"#"+TTL+"#" + message; 
		out.write( s + "\n");
		out.flush();
	}

	public static void Conversation(String message) throws IOException {
		String[] x = message.split("#");
		String destination = x[0];
		String text = "";
	    text = x[1];
	 
	    	Chat(Name,destination,2, text);
	   
	}

	public static void runThis(InetAddress address, int port)
			throws IOException {
		ConnectToServer(address, port);
	
		while (true) {
	
			  String name = JOptionPane.showInputDialog(
					  "Enter your name, please.", null);
           
			System.out.println(name);
			if (join(name)) {
				Name = name;
				break;
			} else
				 JOptionPane.showMessageDialog(null, "your name is already used , please re enter your name ");
				

		}
		JOptionPane.showMessageDialog(null, "If You want to Know Online user Write 1."+"\n"+" If You Know the Name Of Your Frind Write it with the message!"+"\n");
		
		
		while (true) {
			
	
			while ((message==null))
			{
				
			}
			if (message.equals("1")) {
				printRequest();
				message=null;
			} else if (message.equals("BYE") || message.equals("QUIT")) {
				Quit();
			} else {
				Conversation(message);
				message=null;
			}
			
		
		}
		
	}

	public static void Quit() throws IOException {
		socket.close();
		inFromConsole.close();
		gui.dispatchEvent(new WindowEvent(gui, WindowEvent.WINDOW_CLOSING));
		JOptionPane.showMessageDialog(null, "you closed the chat ");
	}

	public static void main(String args[]) throws IOException {
		String host = "localhost";
		int port = 6000;
		InetAddress address = InetAddress.getByName(host);
		runThis(address, port);

	}

	public static String getMessage() {
		return message;
	}

	public static void setMessage(String message) {
		client.message = message;
	}
}
