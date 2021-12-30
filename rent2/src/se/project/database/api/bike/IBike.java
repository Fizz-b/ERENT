package se.project.database.api.bike;

import javafx.collections.ObservableList;
import se.project.model.bike.BikeType;

public interface IBike {

  public ObservableList<BikeType> getListFromDB(String store);
  public ObservableList<BikeType> getAllBike();
  public BikeType getBikeFromDB(String bikeName);
  public BikeType getBikeById(String id);
  public String getBikeType(String id);
  public Integer getIdByName(String name);
  public boolean checkBikeRent(String bikeId);
}
