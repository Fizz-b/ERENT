package se.project.database.api.store;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import se.project.database.Context;
import se.project.model.store.Store;

public class StoreDao implements IStore {

	/* get all store details */
	@Override
	public ObservableList<Store> getListFromDB() {
		// TODO Auto-generated method stub

		ObservableList<Store> storeList = FXCollections.observableArrayList();

		try {

			Connection con = Context.getConnection();
			// can liet ke so xe dang thue
			PreparedStatement ps = con
					.prepareStatement("select s.name,s.address,SUM(CASE WHEN b.type = 'Bike' THEN 1 ELSE 0 END) AS Bike, SUM(CASE WHEN b.type = 'ElectricBike' THEN 1 ELSE 0 END) AS ElectricBike,SUM(CASE WHEN b.type = 'TwinBike' THEN 1 ELSE 0 END) AS TwinBike" 
							+",s.emptyDock,s.storeid from biketype b inner join store s on b.storeid = s.storeid  group by b.storeid");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Store store = new Store();
			
				store.setName(rs.getString(1));
				store.setAddress(rs.getString(2));
				store.setBike(Integer.valueOf(rs.getString(3)));
				store.setElectricBike(Integer.valueOf(rs.getString(4)));
				store.setTwinBike(Integer.valueOf(rs.getString(5)));
				store.setAvailable(Integer.valueOf(rs.getString(6)));
			  
				store.setId(Integer.valueOf(rs.getString(7)));
				File file = new File("src/se/project/image/" + store.getId() + ".jpeg");
				Image image = new Image(file.toURI().toString());
				store.setImage(image);
				storeList.add(store);
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return storeList;
	}

	/*
	public ObservableList<Store> getStoreByName(String name) {
		// TODO Auto-generated method stub

		ObservableList<Store> storeList = FXCollections.observableArrayList();

		try {

			Connection con = Context.getConnection();
			// can liet ke so xe dang thue
			PreparedStatement ps = con
					.prepareStatement("SELECT s.name,s.address,s.emptyDock,coalesce(d.rent,0),s.status,s.storeid "
							+ "FROM store s LEFT JOIN (SELECT storeid,count(*) as rent  FROM biketype "
							+ "WHERE status = 'Rent' GROUP BY storeid ) as d ON s.storeid = d.storeid WHERE s.name = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Store store = new Store();
				// System.out.print(rs.getString(1));
				store.setName(rs.getString(1));
				store.setAddress(rs.getString(2));
				store.setAvailable(Integer.valueOf(rs.getString(3)));
				store.setRent(Integer.valueOf(rs.getString(4)));
				store.setStatus(rs.getString(5));
				store.setId(Integer.valueOf(rs.getString(6)));
				File file = new File("src/se/project/image/" + store.getId() + ".jpeg");
				Image image = new Image(file.toURI().toString());
				store.setImage(image);
				storeList.add(store);
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return storeList;
	}
*/
	/* get all store available to return bike */
	@Override
	public ObservableList<String> getStoreAvai() {
		// TODO Auto-generated method stub

		ObservableList<String> storeList = FXCollections.observableArrayList();

		try {

			Connection con = Context.getConnection();
			// can liet ke so xe dang thue
			PreparedStatement ps = con.prepareStatement("SELECT name FROM store where emptyDock != 0");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				storeList.add(rs.getString(1));
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return storeList;
	}

	/* update store data when return */
	public void updateStoreReturn(int bikeId, int storeId) {
		try {
			Connection con = Context.getConnection();
			// GET ID
			PreparedStatement ps = con.prepareStatement("SELECT updateReturn(?,?)");
			ps.setString(1, Integer.toString(bikeId));
			ps.setString(2, Integer.toString(storeId));
			ps.execute();
			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}
	}

	public String getStoreId(String bikeName) {
		String id = null;
		try {
			Connection con = Context.getConnection();
			// GET ID
			PreparedStatement ps = con.prepareStatement("SELECT storeid FROM ebike.store where name = ?");
			ps.setString(1, bikeName);
			ResultSet rs = ps.executeQuery();

			rs.next();
			id = rs.getString(1);
			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}
		return id;
	}

}
