package com.Hospital_Management_System;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Patient {
	private Connection connection;
	private Scanner scanner;
	
	public Patient(Connection connection, Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;
	}
	
	public void addPatient() {
		System.out.print("Enter Paitent Name: ");
		String name = scanner.next();
		System.out.print("Enter Paitent Age: ");
		int age = scanner.nextInt();
		System.out.print("Enter Paitent Gender: ");
		String gender = scanner.next();
		
		try {
			String query = "INSERT INTO patients(name,age,gender) VALUES(?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);//We can pass sql argument to run this
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, age);
			preparedStatement.setString(3, gender);
			
			int affectdRows = preparedStatement.executeUpdate(); //--> this method return integer value. It said how many perticular rows affected in our database.
			if(affectdRows>0) { 
				System.out.println("Patient Added Successfully!!");
			}
			else {
				System.out.println("Failed to Add Patient!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void viewPatient() {
		String query = "select * from patients";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);//Preparestament is better for normal statement because it performnace is better than normal statement.
			ResultSet resultset = preparedStatement.executeQuery();//ResultSet :- It holds whichever data or table is coming from the database and set point "next" and print it.
			System.out.println("patients: ");
			System.out.println("+------------+-------------------------+------------+--------------+");
			System.out.println("| patient Id | Name                    | Age        | Gender       |");
			System.out.println("+------------+-------------------------+------------+--------------+");
			while(resultset.next()) {
				int id = resultset.getInt("id");
				String name = resultset.getString("name");
				int age = resultset.getInt("age");
				String gender = resultset.getString("gender");
				System.out.printf("|%13s|%-25s|%-12s|%-14s|\n",id,name,age,gender);
				System.out.println("+------------+-------------------------+------------+--------------+");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public boolean getPatientById(int id) {
		String query = "SELECT * FROM patients WHERE id = ?";
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
