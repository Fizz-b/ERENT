package se.project.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import se.project.database.Context;
import se.project.model.order.Order;

public class TransactionUtils {

	public static void saveTransacToDB(Order order) {

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

	// true - co gdich false - k co
	public static boolean checkTransact(String cardNum, String orderId) {
		boolean flag = false;
		try {

			Connection con = Context.getConnection();
			// check card have used
			PreparedStatement ps = con.prepareStatement("SELECT distinct(card) from transaction where orderid = ?");
			ps.setString(1, orderId);
			ResultSet rs = ps.executeQuery();
			if (rs.next() == true) {
				if (rs.getString(1).equals(cardNum)) {
					flag = true;
				} else
					flag = false;
			} else
				flag = true;
			con.close();

		} catch (Exception e) {
			System.out.println(e);

		}
		return flag;
	}

	public static boolean checkCard(String cardNum, String orderId) {
		boolean flag = false;
		try {
			// update bike rent /save transact to "order table"
			Connection con = Context.getConnection();

			// check card have used
			PreparedStatement ps = con
					.prepareStatement("SELECT count(id) from transaction where card = ? and orderId != ?;");
			ps.setString(1, cardNum);
			ps.setString(2, orderId);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int num = Integer.valueOf(rs.getString(1));
				if (num % 2 == 0) {
					flag = false;
				} else
					flag = true;
			} else
				flag = false;
			con.close();

		} catch (Exception e) {
			System.out.println(e);

		}
		return flag;

	}

}
