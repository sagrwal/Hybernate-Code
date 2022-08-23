package com.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc2 {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?userSSL=false";
		String user ="hbstudent";
		String pass ="hbstudent";
		
		try {
			System.out.println("Connecting to databace: " + jdbcUrl);
			
			Connection myConn = DriverManager.getConnection(jdbcUrl,user,pass);
			System.out.println("Connection successfully");
			
		}catch(Exception e){
			e.printStackTrace();
			
		}

	}

}
