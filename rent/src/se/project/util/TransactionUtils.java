package se.project.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import se.project.database.Context;
import se.project.model.order.Order;

public class TransactionUtils {

  public static void saveTransacToDB(Order order) {

    try {
      // update bike rent /save transact to  "order table"
      Connection con = Context.getConnection();
      PreparedStatement ps = con.prepareStatement(
          "SELECT insertRent(?,?,?)");   // function to insert and update bike Table

      ps.setString(1, order.getTimeCreate());
      ps.setString(2, Integer.toString(order.getBike().getId()));
      ps.setString(3, Integer.toString(order.getCust().getId()));
      ps.execute();

      // get store id as input update store dock when rent
      PreparedStatement ps1 = con.prepareStatement("SELECT updateStoreRent(?)");

      ps1.setString(1, Integer.toString(order.getBike().getId()));

      ps1.execute();

      con.close();

    } catch (Exception e) {
      System.out.println(e);

    }
  }

  // true - co gdich   false - k co
  public static boolean checkTransact(int orderId) {

    return true;
  }
	/*
	public static void saveRentToDB(Order order) {

		
		try {
			
			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO transaction(userId) VALUES (?)");
		   // ps.setString(1, order.getCust().getId());
		   // ps.setString(2, String.valueOf(order.getTime()));
		    ps.executeUpdate();
			
		
			con.close();

		} catch (Exception e) {
			System.out.println(e);
          
		}

	}*/
}

