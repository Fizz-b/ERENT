package se.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import se.project.database.Context;
import se.project.interfaces.IHistory;
import se.project.model.history.ItemHistory;

public class HistoryDao implements IHistory {

	private ArrayList<ItemHistory> history;

	/* Get rent history by id */
	public ArrayList<ItemHistory> getRentHistory(int custId) {

		try {
			history = new ArrayList<>();
			Connection con = Context.getConnection();
			// GET ID
			PreparedStatement ps = con.prepareStatement("	SELECT b.name,r.timeCreate,t.money FROM transaction t "
					+ "	INNER JOIN rent r \r\n" + "	ON t.orderid = r.orderId\r\n" + "	INNER JOIN biketype b\r\n"
					+ "	ON r.bikeId = b.id\r\n" + "	where r.custId = ? and mod(t.id,2) = 0;  "); // lay cai dat coc
			ps.setString(1, Integer.toString(custId));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ItemHistory or = new ItemHistory();
				or.setBikeName(rs.getString(1));
				or.setTime(rs.getString(2));
				or.setMoney(Float.valueOf(rs.getString(3)));
				history.add(or);
			}

		} catch (Exception e) {
			System.out.println(e);

		}

		return history;
	}

	/* Get return history by id */
	public ArrayList<ItemHistory> getReturnHistory(int custId) {
		// lay so tien return
		try {
			history = new ArrayList<>();
			Connection con = Context.getConnection();
			// GET ID
			PreparedStatement ps = con.prepareStatement("	SELECT b.name,r.timeFinish,t.money FROM transaction t " // lay
																													// cai
																													// return
																													// cot
																													// le
					+ "	INNER JOIN rent r \r\n" + "	ON t.orderid = r.orderId\r\n" + "	INNER JOIN biketype b\r\n"
					+ "	ON r.bikeId = b.id\r\n" + "	where r.custId = ? and mod(t.id,2) = 1;");
			ps.setString(1, Integer.toString(custId));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ItemHistory or = new ItemHistory();
				or.setBikeName(rs.getString(1));
				or.setTime(rs.getString(2));
				or.setMoney(Float.valueOf(rs.getString(3)));
				history.add(or);
			}

		} catch (Exception e) {
			System.out.println(e);

		}
		return history;

	}

}
