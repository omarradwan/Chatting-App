//package Networkfinal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.TreeMap;

public class server implements Runnable{
	public  ArrayList<chatsockets> Chatsockets = new ArrayList<chatsockets>();
	public ArrayList<chatsockets> getChatsockets() {
		return Chatsockets;
	}

	private ArrayList<String> names =new ArrayList<String>();
	private int numberOfUsers = 0;
	private Socket socket;
	public ArrayList<String> getNames() {
		return names;
	}

	private final int PORT = 6000;
	private ServerSocket serverSocket;
	private Socket ServerToServerSocket ;
	private BufferedReader inFromServer;
	private	BufferedWriter outToServer;

	public server (int port) throws IOException {
		serverSocket = new ServerSocket(port);
		System.out.println("Server Worked");
		ConnectToServer( InetAddress.getByName("localhost"), 2000);
		run();
	}

	public boolean containName(String s) {
		for (int i = 0; i < Chatsockets.size(); i++){
			if (Chatsockets.get(i).getUserName() != null
					&& Chatsockets.get(i).getUserName().equals(s))
				return true;
		}
		return false;

	}

	public int getIndexof(String s) {
		for (int i = 0; i < Chatsockets.size(); i++)
			if (Chatsockets.get(i).getUserName() != null
			&& Chatsockets.get(i).getUserName().equals(s))
				return i;
		return -1;
	}

	public  ArrayList<String> AllOnlineNames() {
		ArrayList<String> ans = new ArrayList<String>();
		for (int i = 0; i < Chatsockets.size(); i++){
			if (Chatsockets.get(i).getUserName() != null)
				ans.add(Chatsockets.get(i).getUserName());
		}
		return ans;

	}
	public boolean isAlone() {
		return numberOfUsers < 1 ? true : false;
	}
	public void ConnectToServer(InetAddress address, int port)
			throws IOException {
		
		ServerToServerSocket = new Socket(address, port);
		outToServer = new BufferedWriter(new OutputStreamWriter(ServerToServerSocket.getOutputStream()));
		inFromServer = new BufferedReader(new InputStreamReader(ServerToServerSocket.getInputStream()));
		ServerToServerThread serverthread=new ServerToServerThread(ServerToServerSocket, inFromServer,this);
		serverthread.start();
		//TTL =2;
	}
	public static void main(String[] args) throws IOException {
		Thread t =new Thread(new server(6000));
		t.start();

	

	}

	public BufferedReader getInFromServer() {
		return inFromServer;
	}

	public void setInFromServer(BufferedReader inFromServer) {
		inFromServer = inFromServer;
	}

	public  BufferedWriter getOutToServer() {
		return outToServer;
	}

	public void setOutToServer(BufferedWriter outToServer) {
		outToServer = outToServer;
	}

	public void destroyChatSocket(int n) {
		Chatsockets.get(n).interrupt();
		Chatsockets.remove(n);
	}


	public Socket getSocket() {
		return socket;
	}

	@Override
	public void run() {
		while (true) {

			try {
				socket = serverSocket.accept();	
				chatsockets ch = new chatsockets(socket , this);
				Chatsockets.add(ch);
				ch.start();
				numberOfUsers++;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}