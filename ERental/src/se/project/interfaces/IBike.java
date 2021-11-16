package se.project.interfaces;

import javafx.collections.ObservableList;
import se.project.model.bike.BikeType;

public interface IBike {

  public ObservableList<BikeType> getListFromDB(String store);

  public BikeType getBikeFromDB(String bikeName);

  public BikeType getBikeById(String id);

  public ObservableList<BikeType> getAllBike();
}
