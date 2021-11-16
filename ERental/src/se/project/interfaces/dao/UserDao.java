package se.project.interfaces.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import se.project.database.Context;
import se.project.interfaces.IUser;
import se.project.model.user.Customer;

public class UserDao implements IUser {

  @Override
  public Customer getUser(String user) {
    Customer customer = new Customer();
    try {

      Connection con = Context.getConnection();
      PreparedStatement ps = con.prepareStatement(
          "SELECT name,address,email,id FROM useraccount  where user = ?");
      ps.setString(1, user);
      ResultSet rs = ps.executeQuery();
      rs.next();
      customer.setName(rs.getString(1));
      customer.setAddress(rs.getString(2));
      customer.setEmail(rs.getString(3));
      customer.setId(Integer.valueOf(rs.getString(4)));
      con.close();

    } catch (Exception e) {
      System.out.println(e);

    }
    return customer;
  }
	
	/*
	public static Customer getUserFromDB(String user) {

	    Customer customer = new Customer();
		try {
			
			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT name,address,email FROM useraccount  where user = ?");
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			rs.next();
		    customer.setName(rs.getString(1));
		    customer.setAddress(rs.getString(2));
		    customer.setEmail(rs.getString(3));
		    
			con.close();

		} catch (Exception e) {
			System.out.println(e);

		}
		return customer;
	}

	public static String getIdDB(String user) {

	    String id = null;
		try {
			
			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT id FROM useraccount  where user = ?");
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			rs.next();
		    id = rs.getString(1);
			con.close();
	        
		} catch (Exception e) {
			System.out.println(e);

		}
		return id;
	}*/
}
