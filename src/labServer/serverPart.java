package labServer;

import java.awt.*;

import javax.swing.*;

import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.sql.*;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.net.*;

import labClient.clientPart;

public class serverPart extends Thread implements Runnable {

	protected int serverPort = 8000;
	protected static ServerSocket serverSocket = null;
	protected Thread runningThread = null;
	protected ExecutorService threadPool = Executors.newFixedThreadPool(100);
	Socket socket;
	
	public serverPart(Socket socket) {
		this.socket = socket;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		JFrame topFrame;
		

		clientPart client = new clientPart();
		
		
		topFrame = new JFrame();
        topFrame.setSize(600,250);
        topFrame.setTitle("Customer Lookup");
        topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        topFrame.add(client);
        topFrame.setVisible(true);
        
        try {
        	serverSocket = new ServerSocket(8000);
        	System.out.println("Listening....");
        	while (true) {
        		Socket scket = serverSocket.accept();
        		System.out.println("Connected....");
        		
        		new Thread(new serverPart(scket)).start();
        	}
        }
        catch (Exception e) {
        	System.out.println(e);
        }
	}
	
	public void run() {
		
	}
		/*
		public void run() {
			synchronized (this) {
				this.runningThread = Thread.currentThread();
		}
		
		openServerSocket();
		
		Connection con;
        Statement stmt;
        ResultSet rs;
        
        try {
        	//Get the Connection
        	con = DriverManager.getConnection("jdbc:mysql://localhost/customer");
        	
        	//create a statement
        	stmt = con.createStatement();
        	
        	//Execute the query
        	rs = stmt.executeQuery("select * from cust_info");
        	while(rs.next()) {
        		System.out.println(runningThread + rs.getString("custname") + "  " + rs.getString("custphone"));
        	}
        	rs.close();
        	stmt.close();
        }
        catch(Exception e)
        {
        	System.out.println("Thread " + runningThread + "is finished");
        }
        
		while(! isStopped()) {
			Socket clientSocket = null;
			try {
				clientSocket = this.serverSocket.accept();
			}
			catch(IOException e) {
				if (isStopped()) {
					System.out.println("Server Stopped");
					break;
				}
			}
			//this.threadPool.execute(new clientPart(clientSocket, "Thread Pooled Server"));
		}
		this.threadPool.shutdown();
		JOptionPane.showConfirmDialog(null, "Server Stopped");
	}

	private synchronized boolean isStopped() {
		// TODO Auto-generated method stub
		return false;
	}

	private void openServerSocket() {
		// TODO Auto-generated method stub
		try {
        	this.serverSocket = new ServerSocket(this.serverPort);
        	JOptionPane.showConfirmDialog(null, "Server Started");
        }
        catch(Exception e) {
        	JOptionPane.showConfirmDialog(null, e);
        }
	}*/
	
}
