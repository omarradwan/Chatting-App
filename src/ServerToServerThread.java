//package Networkfinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ServerToServerThread extends Thread {
	private server server;
	 private Socket socket;
	 private BufferedReader br;
	private String SenderName;
	private String message;
	
	
	public ServerToServerThread(Socket socket,BufferedReader br, server server  ) throws IOException{
		this.socket=socket;
		this.br=br;
		this.server = server;
	}
	public void run(){
		
		try {
			while (true) {
				System.out.println();
				String messageBody=br.readLine().trim();
				System.out.println("balabezo");
				String[]array=messageBody.split("#");
				SenderName=array[0];
			    if(SenderName.equals("serverbig")){
			    	server.getNames().clear();
			    	for (int i = 1; i < array.length; i++) {
						server.getNames().add(array[i]);
					}
			    }
			    else {
			    	System.out.println("");
			    	String reciever = array[1];
			    	int index = server.getIndexof(reciever);
			    	server.Chatsockets.get(index).getOut().write(messageBody+"\n");
			    	server.Chatsockets.get(index).getOut().flush();
			    }
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
	

}
