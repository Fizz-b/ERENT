package se.project.interfaces.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.project.database.Context;
import se.project.interfaces.IStore;
import se.project.model.store.Store;
import se.project.model.store.StoreStatus;

public class StoreDao implements IStore {

	@Override
	public ObservableList<Store> getListFromDB() {
		// TODO Auto-generated method stub

		ObservableList<Store> storeList = FXCollections.observableArrayList();

		try {

			Connection con = Context.getConnection();
			// can liet ke so xe dang thue 
			PreparedStatement ps = con.prepareStatement("SELECT s.name,s.address,s.emptyDock,d.rent,s.status,s.storeid FROM store s,"+
					"(SELECT b.storeid as id,count(*) as rent from biketype b where status = 'Rent' group by storeid) d "+
                    "WHERE s.storeid = d.id");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Store store = new Store();
				//System.out.print(rs.getString(1));
				store.setName(rs.getString(1));
				store.setAddress(rs.getString(2));
				store.setAvailable(Integer.parseInt(rs.getString(3)));
				store.setRent(Integer.valueOf(rs.getString(4)));
				store.setStatus(StoreStatus.valueOf(rs.getString(5)));
				store.setId(Integer.valueOf(rs.getString(6)));
				storeList.add(store);
			}
           
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return storeList;
	}
	@Override
	public ObservableList<String> getStoreAvai() {
		// TODO Auto-generated method stub

		ObservableList<String> storeList = FXCollections.observableArrayList();

		try {

			Connection con = Context.getConnection();
			// can liet ke so xe dang thue 
			PreparedStatement ps = con.prepareStatement("SELECT name FROM store where status = 'Available'");

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
	
	public void updateStoreReturn( int bikeId,int storeId) {
		try {
			Connection con = Context.getConnection();
			//GET ID
			PreparedStatement ps = con.prepareStatement("SELECT updateReturn(?,?)" );
			ps.setString(1,Integer.toString(bikeId));
			ps.setString(2,Integer.toString(storeId));
		    ps.execute();
		    con.close();
		} catch (Exception e) {
			System.out.println(e);
		
	}
	}
}
