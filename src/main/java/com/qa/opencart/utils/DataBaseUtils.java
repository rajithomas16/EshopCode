package com.qa.opencart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.WebDriver;

public class DataBaseUtils {
	
	private WebDriver driver;
	private JavaScriptUtil jsutil;
	
	public DataBaseUtils(WebDriver driver) {//constructor called when an object of elemntutil class is created
		this.driver=driver;//driver in red here is chromedriver and it is passed to  var driver(in blue) and 
		//then used as driver by remaining methods down in blue
		jsutil=new JavaScriptUtil(driver);
		
	}
	
	//Step 1:Method to connect to database
	
	public Connection connecttoDB(String dataBaseName) {
		String localhost="localhost";
		String port;
		port = "4";
		String serviceName="web";
		String username="user1";
		String password="user1";
		Connection con = null ;
		String connectionString = "jdbc:oracle:thin:@"+localhost+":"+port+"/"+serviceName;
		
		try {
			
		 con = DriverManager.getConnection(connectionString,username,password);
		 
		 System.out.println("--connection Established---");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return con;
		
	}
	//Step 2:Create statement using connection object
	
	//Method to fetch the paperless billing data based on sequence number from Ensemble
	
	public void fetchData(String DataBaseName,String paperlessvalue,String bpseqno) 
	{
		Connection con=connecttoDB(DataBaseName);
		try {
			Statement stmt=con.createStatement();
			//STep 3.execute the query
			int noOfCopies = -1; 
			
			String q1="Select select BP_BAN, BP_NO_OF_COPIES, BP_SEQ_NO from cuappc.billing_parameters where BP_BAN ";
			String q2 = "' and BP_SEQ_NO = '"+bpseqno+"'";
			String query =q1+q2;
			System.out.println(query);
			ResultSet rs=stmt.executeQuery(query);//return a result set 
			System.out.println("---------------------------Query Executed---------------------------");
			while(rs.next()){ //checks if value is present in resultset
				System.out.println(rs.getInt("BP_BAN")+"  "+rs.getInt("BP_NO_OF_COPIES")+"  "+rs.getInt("BP_SEQ_NO")); 
				noOfCopies = rs.getInt("BP_NO_OF_COPIES");
			       			}
			}
		catch(Exception e)
		{ 
			System.out.println(e.getMessage());                                       
		}  	
			
		finally{
			// close the connection object
			System.out.println(" ---------------------------DB Connection will be closed---------------------------");
			 try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	//return noOfCopies;
}
}
/**
	 * Method to connect to a database 
	 
	public Connection connect_To_Database(String databaseName){
		IntDataContainer dataContainer = commonData.getContainer(databaseName);
		String localhost = dataContainer.getFieldValue("localhost");
		String port = dataContainer.getFieldValue("port");
		String serviceName = dataContainer.getFieldValue("service_name");
		String username = dataContainer.getFieldValue("username");
		String password = dataContainer.getFieldValue("password");
		Connection con = null;
		String connectionString = "jdbc:oracle:thin:@"+localhost+":"+port+"/"+serviceName;
		System.out.println(connectionString);
		try{  
			// load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			System.out.println("---------------------------OracleDriver located now---------------------------");
			// create  the connection object  
			con = DriverManager.getConnection(connectionString,username,password);                                  
			System.out.println("---------------------------Connection established---------------------------");
		}
		catch(Exception e)
		{ 
			System.out.println(e.getMessage());                                       
		}
		return con;  
	}

	
	 * Oracle JDBC Connection method to fetch Paperless billing details based on Sequence Number of an order from Ensemble tables
	 
	@SuppressWarnings("finally")
	public int fetch_Paperless_Billing_Details(String databaseName, String paperlessBillingValue, String bpSeqNo) throws SQLException{
		Connection con = connect_To_Database(databaseName);
		int noOfCopies = -1; 
		try{  
			// create the statement object  
			Statement stmt=con.createStatement();
			System.out.println("-------------------Now query will be executed---------------------------");
			// execute query
			String q1 = "select BP_BAN, BP_NO_OF_COPIES, BP_SEQ_NO from cuappc.billing_parameters where BP_BAN = '"+ OrderSummarySteps.accountnum;
			String q2 = "' and BP_SEQ_NO = '"+bpSeqNo+"'";
			String query =q1+q2;
			System.out.println(query);
			ResultSet rs=stmt.executeQuery(query);
			System.out.println("---------------------------Query Executed---------------------------");
			while(rs.next()){ 
				System.out.println(rs.getInt("BP_BAN")+"  "+rs.getInt("BP_NO_OF_COPIES")+"  "+rs.getInt("BP_SEQ_NO")); 
				noOfCopies = rs.getInt("BP_NO_OF_COPIES");
			}
		}
		catch(Exception e)
		{ 
			System.out.println(e.getMessage());                                       
		}  
		finally{
			// close the connection object
			System.out.println(" ---------------------------DB Connection will be closed---------------------------");
			  .close();
		}
		return noOfCopies;
		
	}

	
	  Oracle JDBC Connection method to fetch Paperless billing email details based on BAN of an order from Ensemble table
	  
	public String fetch_Paperless_Billing_Email_Details(String databaseName) throws SQLException{
		Connection con = connect_To_Database(databaseName);
		String emailEnsemble = ""; 
		try{  
			// create the statement object  
			Statement stmt=con.createStatement();
			System.out.println("-------------------Now query will be executed---------------------------");
			// execute query
			String q1 = "select CPO_BAN, CPO_VALUE from cuappc.cpo_cust_info where CPO_BAN = '"+ OrderSummarySteps.accountnum;
			String q2 = "' and CPO_NAME = 'EMAIL_ADDRESS'";
			String query =q1+q2;
			System.out.println(query);
			ResultSet rs=stmt.executeQuery(query);
			System.out.println("---------------------------Query Executed---------------------------");
			while(rs.next()){ 
				System.out.println(rs.getString("CPO_BAN")+"  "+rs.getString("CPO_VALUE")); 
				emailEnsemble = rs.getString("CPO_VALUE");
			}
		}
		catch(Exception e)
		{ 
			System.out.println(e.getMessage());                                       
		}  
		finally{
			// close the connection object
			System.out.println(" ---------------------------DB Connection will be closed---------------------------");
			con.close();
		}
		return emailEnsemble;
	}
	
}**/

