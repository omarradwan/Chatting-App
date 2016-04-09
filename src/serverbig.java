//package Networkfinal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.text.html.HTMLDocument.Iterator;

public class serverbig {
	public  static ArrayList<ServerThreads> serverThreads = new ArrayList<ServerThreads>();
	private static TreeMap<String, Integer>onlines=new TreeMap<String,Integer>();
	public static TreeMap<String, Integer> getOnlines() {
		return onlines;
	}


	private static int numberOfServers = 0;
	private static Socket socket;
	private final static int PORT =2000;
	private static ServerSocket serverSocket;
	
	public static String onlineNames(){
		
		String x="";
		  System.out.println("Map Values Before: ");
		  for (Entry<String, Integer> entry : serverbig.getOnlines().entrySet()) {
		        x+=( entry.getKey() + "#");
		    }
		
		return x;
		
	}
	public static ArrayList<ServerThreads> getServerThreads() {
		return serverThreads;
	}


	public static void setServerThreads(ArrayList<ServerThreads> serverThreads) {
		serverbig.serverThreads = serverThreads;
	}


	public static void main(String[] args) throws IOException {
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server Worked");
		while (true) {
			socket = serverSocket.accept();
			ServerThreads ch = new ServerThreads(socket,++numberOfServers);
			serverThreads.add(ch);
			ch.start();
		
		}

	}

}
