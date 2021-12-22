package se.project.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import se.project.database.Context;
import se.project.interfaces.IBike;
import se.project.model.bike.Bike;
import se.project.model.bike.BikeFactory;
import se.project.model.bike.BikeType;
import se.project.model.bike.EBike;
import se.project.model.bike.TwinBike;

public class BikeDao implements IBike {
	private BikeFactory bikeFactory = new BikeFactory();

	/* get all bike by Store name */
	@Override
	public ObservableList<BikeType> getListFromDB(String store) {
		ObservableList<BikeType> bikeList = FXCollections.observableArrayList();

		try {
			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"SELECT biketype.id,biketype.name,type,manuafactur,producer,cost FROM biketype INNER JOIN store ON biketype.storeid = store.storeid where store.name = ?");
			ps.setString(1, store);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// BikeType bike = getObject(rs.getString(3));
				BikeType bike = bikeFactory.getBike(rs.getString(3));
				bike.setId(Integer.valueOf(rs.getString(1)));
				bike.setName(rs.getString(2));
				bike.setType(rs.getString(3));
				bike.setManufacture(rs.getString(4));
				bike.setProducer(rs.getString(5));
				bike.setCost(Integer.parseInt(rs.getString(6)));
				bikeList.add(bike);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return bikeList;
	}

	/* get bike by name */
	@Override
	public BikeType getBikeFromDB(String bikeName) {

		try {

			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM biketype where biketype.name = ?");
			ps.setString(1, bikeName);
			ResultSet rs = ps.executeQuery();
			rs.next();
			BikeType bike = bikeFactory.getBike(rs.getString(4));
			bike.setId(Integer.valueOf(rs.getString(1)));
			bike.setName(rs.getString(3));
			bike.setType(rs.getString(4));
			bike.setWeight(Integer.parseInt(rs.getString(5)));
			bike.setLicense(rs.getString(6));
			bike.setManufacture(rs.getString(7));
			bike.setProducer(rs.getString(8));
			bike.setCost(Integer.valueOf(rs.getString(9)));
			File file = new File("src/se/project/image/" + bike.getName() + ".jpeg");
			Image image = new Image(file.toURI().toString());
			bike.setI(image);
			con.close();
			return bike;
		} catch (Exception e) {
			System.out.println(e);

		}
		return null;
	}

	/* get bike by Id */
	public BikeType getBikeById(String id) {
		BikeType bike;
		try {

			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT  * FROM biketype  where id = ?"); // nen select col thay
																									// vi select het
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			bike = bikeFactory.getBike(rs.getString(4)); // type
			bike.setName(rs.getString(3));
			bike.setType(rs.getString(4));
			bike.setWeight(Integer.parseInt(rs.getString(5)));
			bike.setLicense(rs.getString(6));
			bike.setManufacture(rs.getString(7));
			bike.setProducer(rs.getString(8));
			bike.setCost(Integer.valueOf(rs.getString(9)));
			con.close();
			return bike;
		} catch (Exception e) {
			System.out.println(e);

		}
		return null;
	}

	@Override
	public ObservableList<BikeType> getAllBike() {
		ObservableList<BikeType> bikeList = FXCollections.observableArrayList();

		try {
			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT  * FROM biketype"); // nen select col thay vi select het

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				BikeType bike = bikeFactory.getBike(rs.getString(4)); // type
				bike.setName(rs.getString(3));
				bike.setType(rs.getString(4));
				bike.setWeight(Integer.parseInt(rs.getString(5)));
				bike.setLicense(rs.getString(6));
				bike.setManufacture(rs.getString(7));
				bike.setProducer(rs.getString(8));
				bike.setCost(Integer.valueOf(rs.getString(9)));
				bikeList.add(bike);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return bikeList;
	}

	public boolean checkBikeRent(String bikeId) {

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
}
