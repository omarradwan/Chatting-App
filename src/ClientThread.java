//package Networkfinal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class ClientThread extends  Thread{
 private Socket socket;
 private BufferedReader br;
private String SenderName;
private String message;
private static GUI gui;
public ClientThread(Socket socket,BufferedReader br,GUI gui ) throws IOException{
	
	this.socket=socket;
	this.br=br;
	this.gui=gui;
}

public void run(){
	
	try {
		while (true) {
			
			String messageBody=br.readLine().trim();
			

			String[]array=messageBody.split("#");
			SenderName=array[0];
			 message=array[3];
			
		
		gui.getChatbox().append(SenderName+": "+message +"\n");
		}
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	
	
	
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public BufferedReader getBr() {
	return br;
}

public void setBr(BufferedReader br) {
	this.br = br;
}

public String getSenderName() {
	return SenderName;
}

public void setSenderName(String senderName) {
	SenderName = senderName;
}
}
