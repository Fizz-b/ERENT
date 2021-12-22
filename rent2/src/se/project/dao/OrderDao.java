package se.project.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.scene.image.Image;
import se.project.database.Context;
import se.project.interfaces.IBike;
import se.project.interfaces.IOrder;
import se.project.model.bike.BikeType;
import se.project.model.order.Order;

public class OrderDao implements IOrder {

	// login need bikeId custId deposit timeCreate
	/* Get order by userid  */
	@Override
	public Order getOrder(int userId) {
		Order order = new Order();
		IBike ibike = new BikeDao();
		try {

			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"SELECT orderId,timeCreate,bikeId FROM rent  where custId = ? and timeFinish is null");
			ps.setString(1, Integer.toString(userId));
			ResultSet rs = ps.executeQuery();

			// exist bike
			if (rs.next()) {

				order.setId(Integer.valueOf(rs.getString(1)));
				order.setTimeCreate(rs.getString(2));
				BikeType bike = ibike.getBikeById(rs.getString(3));
				
				File file = new File("src/se/project/image/" + bike.getName() + ".jpeg");
				Image image = new Image(file.toURI().toString());
				bike.setI(image);
				bike.setId(Integer.valueOf(rs.getString(3)));
				order.setBike(bike);
				

			} else {
				order.setBike(null);
			}

			con.close();

		} catch (Exception e) {
			System.out.println(e);

		}
		return order;
	}

	/* Get order id by userId  */
	@Override
	public int getOrderId(int custId) {
		try {
			Connection con = Context.getConnection();
			// GET ID
			PreparedStatement ps = con
					.prepareStatement("SELECT orderId FROM rent WHERE custId = ? ORDER BY orderId DESC LIMIT 1 ");
			ps.setString(1, Integer.toString(custId));
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
