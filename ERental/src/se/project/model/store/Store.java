package se.project.model.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.project.database.Context;
import se.project.database.MySQLConnection;


public class Store {
	private String name;
	private String address;
	private int available;
	private int rent;
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}




	private StoreStatus status;
    
	public void setStatus(StoreStatus status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	
	
    
	public StoreStatus getStatus() {
		return status;
	}

	public void setId(Integer valueOf) {
		// TODO Auto-generated method stub
		this.id = valueOf;
	}
    
	/*
	public String getStatusFormat() {
		if(this.getStatus() == StoreStatus.FULL) {
			return "Full";
		}else if(this.getStatus() == StoreStatus.available) {
			return "Available";
		}else if(this.getStatus() ==StoreStatus.EMPTY) {
			return "Empty";
		}else return "Invalid Status";
	}*/

}
