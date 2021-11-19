package se.project.interfaces.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import se.project.database.Context;
import se.project.interfaces.ITransaction;

public class TransactionDAO implements ITransaction {
 
	
  /* Save transaction info to DB */
  @Override
  public void saveTransaction(int orID, String msg, float money) {
    try {
      Connection con = Context.getConnection();
      //GET ID
      PreparedStatement ps = con.prepareStatement(
          "INSERT INTO transaction(orderId,message,money) VALUES (?,?,?); ");
      ps.setString(1, Integer.toString(orID));
      ps.setString(2, msg);
      ps.setString(3, Float.toString(money));
      ps.executeUpdate();
      con.close();
    } catch (Exception e) {
      System.out.println(e);

    }


  }

  //updateRent and bike status
  @Override
  public void updateReturn(int orId, int bikeId, float totMoney, String timeFinish) {
    try {
      Connection con = Context.getConnection();
      //GET ID
      PreparedStatement ps = con.prepareStatement("SELECT insertReturn(?,?,?,?)");
      ps.setString(1, Integer.toString(orId));
      ps.setString(2, Integer.toString(bikeId));
      ps.setString(3, Float.toString(totMoney));
      ps.setString(4, timeFinish);
      ps.execute();
      con.close();
    } catch (Exception e) {
      System.out.println(e);

    }
  }

}
