package se.project.database.api.store;

import javafx.collections.ObservableList;
import se.project.model.store.Store;

public interface IStore {

  public ObservableList<Store> getListFromDB();
  public ObservableList<String> getStoreAvai();
  public ObservableList<Store> getStoreByName(String name);
  public String getStoreId(String bikeName);
  public void updateStoreReturn(int bikeId, int storeId);
}


