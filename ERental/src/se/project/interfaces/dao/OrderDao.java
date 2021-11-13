package se.project.interfaces.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import se.project.database.Context;
import se.project.interfaces.IBike;
import se.project.interfaces.IOrder;
import se.project.model.bike.BikeType;
import se.project.model.order.Order;
import se.project.model.user.Customer;

public class OrderDao implements IOrder {
    
	// login need bikeId custId deposit timeCreate
	@Override
	public Order getOrder(int userId) {
		 Order order = new Order();
		 IBike ibike = new BikeDao();
			try {
				
				Connection con = Context.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT bikeId,custId FROM rent  where custId = ? and timeFinish is null");
				ps.setString(1,Integer.toString(userId));
				ResultSet rs = ps.executeQuery();
			   
				// exist bike
			   if(rs.next()) {
			    
				BikeType bike = ibike.getBikeById(rs.getString(1));
				order.setBike(bike);
			    
	          
			   } else order.setBike(null);
			    
			    con.close(); 
				return order;

			} catch (Exception e) {
				System.out.println(e);

			}
		  return null;
	}
    
	@Override
	public int getOrderId(int custId) {
			try {
				Connection con = Context.getConnection();
				//GET ID
				PreparedStatement ps = con.prepareStatement("SELECT orderId FROM rent WHERE custId = ? ORDER BY orderId DESC LIMIT 1 " );
				ps.setString(1,Integer.toString(custId));
				ResultSet rs = ps.executeQuery();
			    rs.next();
			    int ans = Integer.valueOf(rs.getString(1));
			    con.close();
				return ans;
			} catch (Exception e) {
				System.out.println(e);

			}
		  return 0;
	}
	
	

}
