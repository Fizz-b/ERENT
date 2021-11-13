package se.project.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.project.database.Context;
import se.project.database.MySQLConnection;
import se.project.model.bike.Bike;
import se.project.model.bike.BikeType;
import se.project.model.bike.EBike;
import se.project.model.bike.TwinBike;

public class BikeUtils {

	/*
	 * @store: store name
	 * 
	 * return checkBikeRent by name
	 */
	public static boolean checkBikeRent(String bikeId) {

		try {

			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT status FROM biketype where name = ?");
			ps.setString(1, bikeId);

			ResultSet rs = ps.executeQuery();

			rs.next();
			if (rs.getString(1).equals("Rent")) {
				con.close();
				return true;
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return false;
	}

	/*
	 * @bike name : name of bike
	 * 
	 * @type : bike type return details of bike
	 */
	public static BikeType getBikeFromDB(String bikeName, String type) {

		BikeType bike = getObject(type);
		try {

			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM biketype  where biketype.name = ?");
			ps.setString(1, bikeName);
			ResultSet rs = ps.executeQuery();
			rs.next();
			bike.setName(rs.getString(3));
			bike.setType(rs.getString(4));
			bike.setWeight(Integer.parseInt(rs.getString(5)));
			bike.setLicense(rs.getString(6));
			bike.setManufacture(rs.getString(7));
			bike.setProducer(rs.getString(8));

			con.close();

		} catch (Exception e) {
			System.out.println(e);

		}
		return bike;
	}

	/*
	 * @type : type of bike return object
	 */
	public static BikeType getObject(String type) {
		switch (type) {
		case "Ebike":
			return new EBike();

		case "Bike":
			return new Bike();

		case "TwinBike":
			return new TwinBike();

		}
		return null;
	}

}
