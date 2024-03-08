package com.Hospital_Management_System;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Doctor {
	private Connection connection;
	
	public Doctor(Connection connection) {
		this.connection = connection;
	}
	
	
	public void viewDoctors() {
		String query = "select * from doctors";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);//Preparestament is better for normal statement because it performnace is better than normal statement.
			ResultSet resultset = preparedStatement.executeQuery();//ResultSet :- It holds whichever data or table is coming from the database and set point "next" and print it.
			System.out.println("Doctors: ");
			System.out.println("+------------+-------------------------+------------+---------+");
			System.out.println("| Doctor Id  | Name                    | Specialization       |");
			System.out.println("+------------+-------------------------+----------------------+");
			while(resultset.next()) {
				int id = resultset.getInt("id");
				String name = resultset.getString("name");
				String specialization = resultset.getString("specialization");
				System.out.printf("|%12s|%-25s|%-22s|\n",id,name,specialization);
				System.out.println("+------------+-------------------------+----------------------+");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public boolean getDoctorById(int id) {
		String query = "SELECT * FROM doctors WHERE id = ?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultset = preparedStatement.executeQuery();
			if(resultset.next()) {  // If ant data is exist it return true oterwise false
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			
		}
		return false;
	}
}