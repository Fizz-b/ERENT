package se.project.interfaces.dao;

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
import se.project.model.bike.BikeType;
import se.project.model.bike.EBike;
import se.project.model.bike.TwinBike;

public class BikeDao implements IBike {

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
				BikeType bike = getObject(rs.getString(3));
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

	@Override
	public BikeType getBikeFromDB(String bikeName,String type) {
		BikeType bike = getObject(type);
		try {
			
			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM biketype  where biketype.name = ?");
			ps.setString(1, bikeName);
			ResultSet rs = ps.executeQuery();
			rs.next();
			bike.setId(Integer.valueOf(rs.getString(1)));
			bike.setName(rs.getString(3));
			bike.setType(rs.getString(4));
			bike.setWeight(Integer.parseInt(rs.getString(5)));
			bike.setLicense(rs.getString(6));
			bike.setManufacture(rs.getString(7));
			bike.setProducer(rs.getString(8));
			bike.setCost(Integer.valueOf(rs.getString(9)));
			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}
		return bike;
	}
	
	public BikeType getBikeById(String id) {
		BikeType  bike ;
		try {
			
			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT  * FROM biketype  where id = ?"); // nen select col thay vi select het
			ps.setString(1,id);
			ResultSet rs = ps.executeQuery();
		    
			rs.next();
		     
		    bike = getObject(rs.getString(4));  // type
			bike.setName(rs.getString(3));
			bike.setType(rs.getString(4));
			bike.setWeight(Integer.parseInt(rs.getString(5)));
			bike.setLicense(rs.getString(6));
			bike.setManufacture(rs.getString(7));
			bike.setProducer(rs.getString(8));
		    
			con.close();
			return bike;
		} catch (Exception e) {
			System.out.println(e);

		}
	     return null;
	}
	
	public  BikeType getObject(String type) {
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
