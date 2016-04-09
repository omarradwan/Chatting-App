//package Networkfinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThreads extends Thread {
	Socket socket;
	int num;
	public BufferedWriter getOut() {
		return out;
	}
	public void setOut(BufferedWriter out) {
		this.out = out;
	}



	private BufferedReader in;
	private BufferedWriter out;
	private BufferedWriter out2;


	public ServerThreads(Socket socket,int x) throws IOException{
		this.socket = socket;
		out = new BufferedWriter(new OutputStreamWriter(
				this.socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

		this.num=x;


	}
	public void userChooseAnoutherUser(String RecieveMessage) throws IOException {

		String temp = RecieveMessage;
		//System.out.println(temp);
		String message [] = temp.split("#");
		String username = message[1];
		String text = "";
		text += message[3];
		int cnt = serverbig.getOnlines().get(username);
		System.out.println("cnt = "+cnt);
		ServerThreads s = serverbig.getServerThreads().get(cnt-1)	;	
		out2 = s.getOut();
		out2.write(temp+ "\n");
		out2.flush();

		/*if (text.equals("QUIT") || text.equals("BYE")) {						
			server.destroyChatSocket(cnt);
		}*/
	}






	public void run() {

		try {

			//this.getNameFromUser();
			while (true) {
				String RecieveMessage=in.readLine().trim();
				   System.out.println(RecieveMessage);
					String [] split = RecieveMessage.split("#");
					if(split[0].equals("login")){
						serverbig.getOnlines().put(split[1], num);
						System.out.println(serverbig.getOnlines().size());
						String names=serverbig.onlineNames();
						
						for (int i = 0; i < serverbig.getServerThreads().size(); i++) {
							System.out.println(names);
							serverbig.getServerThreads().get(i).getOut().write("serverbig#"+names+"\n");
							serverbig.getServerThreads().get(i).getOut().flush();
						}
					}else{
						if(split[0].equals("logoff")){
							serverbig.getOnlines().remove(split[1]);
							String names=serverbig.onlineNames();
							for (int i = 0; i < serverbig.getServerThreads().size(); i++) {
								serverbig.getServerThreads().get(i).getOut().write("serverbig#"+names+"\n");
								serverbig.getServerThreads().get(i).getOut().flush();
							}
						}else{
							this.userChooseAnoutherUser(RecieveMessage);

						}
						}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}


}
