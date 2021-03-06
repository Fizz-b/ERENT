package se.project.database.api.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import se.project.database.Context;
import se.project.model.user.Customer;

public class UserDao implements IUser {
  
	
	/* Get user details  */
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
  
  @Override
  public String getNameById(String id) {
    String userName = null;
    try {

      Connection con = Context.getConnection();
      PreparedStatement ps = con.prepareStatement(
          "SELECT name FROM useraccount  where id = ?");
      ps.setString(1, id);
      ResultSet rs = ps.executeQuery();
     if( rs.next()) {
    	 userName = rs.getString(1);
     }
     
      con.close();

    } catch (Exception e) {
      System.out.println(e);

    }
    return userName;
  }
	
  
  
  @Override
  public Customer getUserById(String id) {
    Customer customer = new Customer();
    try {

      Connection con = Context.getConnection();
      PreparedStatement ps = con.prepareStatement(
          "SELECT name,address,email,id FROM useraccount  where id = ?");
      ps.setString(1, id);
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
	
	
}
