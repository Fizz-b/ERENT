package se.project.model.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import se.project.database.Context;
import se.project.database.MySQLConnection;
import se.project.model.bike.BikeType;
import se.project.model.payment.CreditCard;

public class Customer {
  private int  id;
  public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
private String name;
  private String address;
  private String email;
  private CreditCard card;



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
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}


}
