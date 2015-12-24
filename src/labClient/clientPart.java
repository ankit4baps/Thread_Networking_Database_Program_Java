package labClient;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.sql.*;

public class clientPart  extends JPanel implements ActionListener {
	
		private static String dbUrl = "jdbc:mysql://localhost/customer";
		private static Connection dbCon;
		private static String userId = "root";
		private static String pass = "admin";
		static String custName, custPhone;
		
		public static Connection getConnection() throws SQLException {
			if(dbCon == null) {
				dbCon = DriverManager.getConnection(dbUrl, userId, pass);
			}
			return dbCon;
		}
		
		public static void shutdown() throws SQLException {
			if(dbCon != null) {
				dbCon.close();
			}
		}
		
	      private JLabel lastNameLbl = new JLabel("Last Name: ");
	      private static JTextField lastNameTxtField = new JTextField(20);

	      private JLabel firstNameLbl = new JLabel("First Name: ");
	      private static JTextField firstNameTxtField = new JTextField(10);

	      private JLabel phoneNumLbl = new JLabel("Phone:  (");
	      private static JTextField areaCodeTxtField = new JTextField(4);
	      private JLabel endAreaCodeLbl = new JLabel(")");
	      private static JTextField localNumTxtField = new JTextField(10);

	      private JButton nameSearchBtn = new JButton("Name Search");
	      private JButton phoneSearchBtn = new JButton("Phone Search");
	      
	      private static JTextArea recordTextField = new JTextArea(10, 15);

	      private JPanel namePanel = new JPanel();
	      private JPanel phonePanel = new JPanel();
	      private JPanel btnPanel = new JPanel();
	      private JPanel lstPhPanel = new JPanel();
	      
	      public clientPart() {
	    	  setLayout(new GridLayout(5, 1));

	          namePanel.add(lastNameLbl);
	          namePanel.add(lastNameTxtField);
	          namePanel.add(firstNameLbl);
	          namePanel.add(firstNameTxtField);
	          add(namePanel);

	          phonePanel.add(phoneNumLbl);
	          phonePanel.add(areaCodeTxtField);
	          phonePanel.add(endAreaCodeLbl);
	          phonePanel.add(localNumTxtField);
	          add(phonePanel);

	          phoneSearchBtn.addActionListener(this);
	          nameSearchBtn.addActionListener(this);
	          btnPanel.add(nameSearchBtn);
	          btnPanel.add(phoneSearchBtn);
	          add(btnPanel);
	          
	          lstPhPanel.add(recordTextField);
	          add(lstPhPanel);

	      }

	      public void actionPerformed(ActionEvent evt) {
	    	  JButton sourceBtn;

	    	  sourceBtn = (JButton) evt.getSource();
	    	  if (sourceBtn == phoneSearchBtn) {
	    		  try {
	    			  printPhoneMatch();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	  } else if (sourceBtn == nameSearchBtn) {
	    		
	    			  try {
						printNameMatch();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
	    	  }
	      }
	      
	      
	  public static void printNameMatch() throws SQLException {
		  Socket addSocket = null;
		  Connection con;
		  Customer cus;
		  Name nm;
		  String fullName;
		  
		  fullName = lastNameTxtField.getText() + " " + firstNameTxtField.getText();
		  
		  try {
	    	  addSocket = new Socket("localhost", 8000);
	    	  JOptionPane.showConfirmDialog(null, "Socket and Threading Started");
	    	  System.out.println("Socket and Threading Started");
	    	  System.out.println("-----------------------------");
	    	  //Query for search
	    	  //String queryStr = "select custname, custphone" + "FROM cust_info" + "WHERE custphone = '" + localnum + "'";
	    	 
	    	  //Get the Conection
	    	  con = getConnection();
	    	  
	    	  //Create a Statement
	    	  Statement queryStmt = con.createStatement();
	    	  
	    	  //Execute the Query
	    	  ResultSet rs;
	    	  rs = queryStmt.executeQuery("select custname, custphone from cust_info where custname = '" + fullName + "'");
	    	  if(rs.next()) {
	    		  do{
	    			  custName = rs.getString("custname");
	    			  custPhone = rs.getString("custphone");
	    			  System.out.println(custName + " - " + custPhone);
	    			  recordTextField.setText(custName + " - " + custPhone);  
	    		  } while(rs.next());
	    	  }
	    	  else {
	    		  System.out.println("Record Not Found In Database");
	    		  recordTextField.setText("Record Not Found In Database");
	    	  }
	    	  rs.close();
	    	  queryStmt.close();
	    	  
	    	  //addSocket.close();
	    	  System.out.println("-----------------------------");
	    	  System.out.println("Socket and Threading Closed");
	    	  JOptionPane.showConfirmDialog(null, "Socket and Threading Closed");
	      }
	      catch(Exception e) {
	    	  System.out.println(e);
	      }
	      /*catch(UnknownHostException e) {
	    	  JOptionPane.showConfirmDialog(null, "Can not connect to Server");
	      }
	      catch(IOException e) {
	    	  JOptionPane.showConfirmDialog(null, "Unable to Open Server");
	      }*/
	      
	  }

		public static void printPhoneMatch() throws SQLException {
	    	  Socket addSocket = null;
	    	  Connection con;
	    	  Customer cus;
	    	  PhoneNum phone;
	    	  String areaCodeStr, localnum;
	    	  
	    	  localnum = areaCodeTxtField.getText() + localNumTxtField.getText();
	    	  
		      try {
		    	  addSocket = new Socket("localhost", 8000);
		    	  JOptionPane.showConfirmDialog(null, "Socket and Threading Started");
		    	  System.out.println("Socket and Threading Started");
		    	  System.out.println("-----------------------------");
		    	  //Query for search
		    	  //String queryStr = "select custname, custphone" + "FROM cust_info" + "WHERE custphone = '" + localnum + "'";
		    	 
		    	  //Get the Conection
		    	  con = getConnection();
		    	  
		    	  //Create a Statement
		    	  Statement queryStmt = con.createStatement();
		    	  
		    	  //Execute the Query
		    	  ResultSet rs;
		    	  rs = queryStmt.executeQuery("select custname, custphone from cust_info where custphone = '" + localnum + "'");
		    	  if(rs.next()) {
		    		  do {
		    			  custName = rs.getString("custname");
		    			  custPhone = rs.getString("custphone");
		    			  System.out.println(custName + " - " + custPhone);
		    			  recordTextField.setText(custName + " - " + custPhone);
		    		  } while(rs.next());
		    	  }
		    	  else {
		    		  System.out.println("Record Not Found In Database");
		    		  recordTextField.setText("Record Not Found In Database");
		    	  }
		    	  rs.close();
		    	  queryStmt.close();
		    	  
		    	  //addSocket.close();
		    	  System.out.println("-----------------------------");
		    	  System.out.println("Socket and Threading Closed");
		    	  JOptionPane.showConfirmDialog(null, "Socket and Threading Closed");
		      }
		      catch(Exception e) {
		    	  System.out.println(e);
		      }
		      /*catch(UnknownHostException e) {
		    	  JOptionPane.showConfirmDialog(null, "Can not connect to Server");
		      }
		      catch(IOException e) {
		    	  JOptionPane.showConfirmDialog(null, "Unable to Open Server");
		      }*/
		      
	      }
}