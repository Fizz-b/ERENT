package se.project.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.project.database.Context;
import se.project.model.store.Store;
import se.project.model.store.StoreStatus;

public interface IStore {
  public ObservableList<Store> getListFromDB();

  public ObservableList<String> getStoreAvai();
  
  public void updateStoreReturn( int bikeId,int storeId) ;
}


