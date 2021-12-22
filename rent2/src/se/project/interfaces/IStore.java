package se.project.interfaces;

import javafx.collections.ObservableList;
import se.project.model.store.Store;

public interface IStore {

  public ObservableList<Store> getListFromDB();

  public ObservableList<String> getStoreAvai();

  public void updateStoreReturn(int bikeId, int storeId);
  
  public String getStoreId(String bikeName);
  
  public ObservableList<Store> getStoreByName(String name);
}


