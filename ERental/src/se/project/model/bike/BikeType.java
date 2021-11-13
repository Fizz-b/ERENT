package se.project.model.bike;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import se.project.database.Context;
import se.project.database.MySQLConnection;


public abstract class BikeType {
	protected String name;
	protected String type;
	protected String manufacture;
	protected String producer;
	protected int cost;
	protected int weight;
	protected String license;
	protected String status;
	protected int deposit;
	protected int id;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BikeType() {
		// TODO Auto-generated constructor stub
	}

	public BikeType(String name, String type, String manu, String producer, int cost) {
		this.name = name;
		this.type = type;
		this.manufacture = manu;
		this.producer = producer;
		this.cost = cost;
	}

	
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	

	
	
	public  int getDeposit() {
		return 0;
	}
		
	

}
