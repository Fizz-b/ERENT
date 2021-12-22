package se.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import se.project.database.Context;
import se.project.interfaces.ITransaction;

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

@Override
public boolean checkTransactFinish(int custId) {
	// TODO Auto-generated method stub\
	boolean flag = true;
	 try {
	      Connection con = Context.getConnection();
	      //GET ID
	      PreparedStatement ps = con.prepareStatement(
	          "SELECT orderId FROM rent WHERE custId = ? and timeFinish is NULL ");
	      ps.setString(1, Integer.toString(custId));
	      ResultSet rs = ps.executeQuery();
	      if(rs.next()) {
	    	  flag = false;
	      } 
	      con.close();
	    } catch (Exception e) {
	      System.out.println(e);

	    }
	return flag;
}

}
