package se.project.database.api.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import se.project.database.Context;
import se.project.model.order.Order;

public class TransactionDAO implements ITransaction {
 
	
  /* Save transaction info to DB */
  @Override
  public void saveTransaction(int orID, String msg, double money,String card) {
    try {
      Connection con = Context.getConnection();
      //GET ID
      PreparedStatement ps = con.prepareStatement(
          "INSERT INTO transaction(orderId,message,money,card) VALUES (?,?,?,?); ");
      ps.setString(1, Integer.toString(orID));
      ps.setString(2, msg);
      ps.setString(3, Double.toString(money));
      ps.setString(4,card);
      ps.executeUpdate();
      con.close();
    } catch (Exception e) {
      System.out.println(e);

    }


  }

  //updateRent and bike status
  @Override
  public void updateReturn(int orId, int bikeId, double totMoney, String timeFinish,String returnI) {
    try {
      Connection con = Context.getConnection();
      //GET ID
      PreparedStatement ps = con.prepareStatement("SELECT insertReturn(?,?,?,?,?)");
      ps.setString(1, Integer.toString(orId));
      ps.setString(2, Integer.toString(bikeId));
      ps.setString(3, Double.toString(totMoney));
      ps.setString(4, timeFinish);
      ps.setString(5, returnI);
      ps.execute();
      con.close();
    } catch (Exception e) {
      System.out.println(e);

    }
  }



public void saveTransacToDB(Order order) {

	try {
		// update bike rent /save transact to "order table"
		Connection con = Context.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT insertRent(?,?,?)"); // function to insert and update
																					// bike Table

		ps.setString(1, order.getTimeCreate());
		ps.setString(2, Integer.toString(order.getBikeId()));
		ps.setString(3, Integer.toString(order.getCustId()));
		ps.execute();

		// get store id as input update store dock when rent
		PreparedStatement ps1 = con.prepareStatement("SELECT updateStoreRent(?)");
		ps1.setString(1, Integer.toString(order.getBikeId()));
		ps1.execute();
		con.close();

	} catch (Exception e) {
		System.out.println(e);

	}
}
}
