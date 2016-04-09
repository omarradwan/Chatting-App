//package Networkfinal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class chatsockets extends Thread {
	private Socket socket;
	private server server;
	private BufferedReader in;
	private BufferedWriter out;
	private BufferedWriter out2;
	private BufferedWriter outToServer;
	private BufferedReader inFromServer;
	String name = null;

	public chatsockets(Socket socket , server server) throws IOException {
		this.socket = socket;
		this.server = server;
		out = new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.inFromServer=server.getInFromServer();
		this.outToServer=server.getOutToServer();
		
	}
	
	public String getUserName() {
		return this.name;

	}

	public void userChooseAnoutherUser(String RecieveMessage) throws IOException {				
					String temp = RecieveMessage;
					//System.out.println(temp);
					String message [] = temp.split("#");
					String username = message[1];
					String text = "";
				    text += message[3];
					int cnt = server.getIndexof(username);
					System.out.println("gbna elcnt " +cnt);
					if(cnt >-1){
					out2 = server.Chatsockets.get(cnt).getOut();
					out2.write(temp+ "\n");
					out2.flush();
					}
					else {
						server.getOutToServer().write(RecieveMessage+"\n");
						server.getOutToServer().flush();
					}
					if (text.equals("QUIT") || text.equals("BYE")) {
						server.getOutToServer().write("logoff#"+this.name+"\n");
						server.getOutToServer().flush();
						server.destroyChatSocket(cnt);
					}
		}
	

	public void getNameFromUser() throws IOException {
		while (true) {
			String u_name = in.readLine().trim();
			if (server.getNames().contains(u_name)) {
				out.write("server#"+u_name+"#2#"+"please choose another name \n");
				out.flush();
			} else {
				this.name = u_name;
				out.write("ACCEPTED"+"\n");
				out.flush();
				server.getOutToServer().write("login#"+this.name+"\n");
				server.getOutToServer().flush();
				break;

			}
		}

	}

	public synchronized void run() {

		try {
			
			this.getNameFromUser();
			while (true) {
				String RecieveMessage = in.readLine().trim();
				if (RecieveMessage.equals("GetUsers")) {
					ArrayList<String> myNames = server.getNames();
					System.out.println(server.getNames().size());
					String allNames = "";
					for (int i = 0; i < myNames.size(); i++) {
						allNames+=myNames.get(i)+",";
					}
					out.write("server#"+name+"#2#"+allNames+"\n");
					out.flush();
			} else {
				System.out.println("wasalet llserver");
					this.userChooseAnoutherUser(RecieveMessage);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}

	private void destroyThis() {
	
			server.destroyChatSocket(server.getIndexof(this.name));
		}


	public BufferedWriter getOut2() {
		return out2;
	}

	public void setOut2(BufferedWriter out2) {
		this.out2 = out2;
	}

	public Socket getSocket() {
		return socket;
	}

	public BufferedReader getIn() {
		return in;
	}

	public BufferedWriter getOut() {
		return out;
	}

}