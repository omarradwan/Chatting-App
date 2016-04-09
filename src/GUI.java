

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javafx.scene.control.TextArea;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;


public class GUI extends JFrame  implements MouseListener,WindowListener,KeyListener ,ActionListener{

	private JTextArea chatbox=null ;
	
	private TextField sendingarea=null;
	 private String username ;
	 private JButton send;
	 private JButton logoff;
	 private static client theclient;
	  GUI(String username) throws IOException{
		  super(username);
		
		//  theclient.main(String args[]);
		  this.addWindowListener(this);
		  this.setSize(600,400);
		  
		  this.setResizable(true);
		  this.setLayout(new BorderLayout());
		  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  
		  chatbox =new JTextArea();
		 
		 this.add(chatbox,"Center");
	 chatbox.setFont((new Font("Arial",Font.PLAIN,16)));
		  Panel p =new Panel();
		  p.setLayout(new FlowLayout());
		  sendingarea =new TextField(40);
		  sendingarea.addActionListener(this);
		  sendingarea.setFont((new Font("Arial",Font.PLAIN,16)));
		  
		  p.add(sendingarea);
		  
		  
		  send=new JButton("send");
		  send.addMouseListener(this);
		  p.add(send);
		  
		  logoff=new JButton("logoff");
		  logoff.addMouseListener(this);
		  p.add(logoff);
		  this.add(p,"South");
		  this.setVisible(true);
		  
		  
		  
		  
		  
		  
		  
		
		  
		  
		  
	  }
	  
	
	public static void main (String[]args) throws IOException{
		GUI g=new GUI("chatbox");
		  theclient=new client(g);
	}
	
	
	
	  
	
	
	private void add(TextArea chatbox2, String string) {
		// TODO Auto-generated method stub
		
	}







	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	
		if(sendingarea.getText().length()<1){
			
		}else{
	
			
			String send=sendingarea.getText();
			
			String[]array=send.split("#");
		System.out.println(array.length);
			if(array.length>1){
				
			chatbox.append(theclient.getName()+ ": "+array[1] +"\n");
			
			}
			theclient.setMessage(sendingarea.getText());
			
			
			sendingarea.setText("");
			if(array.length>1){
	
			}
		}
		 sendingarea.requestFocusInWindow();
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public JTextArea getChatbox() {
		return chatbox;
	}


	public void setChatbox(JTextArea chatbox) {
		this.chatbox = chatbox;
	}


	public TextField getSendingarea() {
		return sendingarea;
	}


	public void setSendingarea(TextField sendingarea) {
		this.sendingarea = sendingarea;
	}


	public JButton getSend() {
		return send;
	}


	public void setSend(JButton send) {
		this.send = send;
	}


	@Override
	public void actionPerformed(ActionEvent event) {
	
		if(sendingarea.getText().length()<1){
			
		}else{

			
			String send=sendingarea.getText();
			
			String[]array=send.split("#");
		System.out.println(array.length);
			if(array.length>1){
				
			chatbox.append(theclient.getName()+ ": "+array[1] +"\n");
			
			}
			theclient.setMessage(sendingarea.getText());
			
			
			sendingarea.setText("");
			if(array.length>1){
				
	
			}
		}
		 sendingarea.requestFocusInWindow();
		
		
		
		
	}

}
