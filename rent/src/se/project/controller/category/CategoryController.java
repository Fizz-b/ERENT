package se.project.controller.category;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import se.project.dao.BikeDao;
import se.project.dao.StoreDao;
import se.project.interfaces.IBike;
import se.project.interfaces.IStore;
import se.project.model.bike.BikeType;
import se.project.model.order.Order;
import se.project.model.store.Store;

public class CategoryController  {

  @FXML
  private FlowPane itemFlow;

  @FXML
  private Pane pane11;

  @FXML
  private FlowPane storeFlow;
  private Order order;
  private IBike iBike = new BikeDao();
  private IStore iStore = new StoreDao();

  public void setOrder(Order o) {
    this.order = o;
  }

  public void initStore() {
    ObservableList<Store> store = iStore.getListFromDB();
    for (Store s : store) {

      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("/se/project/gui/category/storeCat.fxml"));
      try {
        
        // add bike info to pane
        Pane view = loader.load();
        StoreController storeP = loader.getController();
        storeP.getStoreImg().setImage(s.getImage());
        storeP.setText(s.getName());
        storeFlow.getChildren().add(view);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }
  }

  public void initBike() {
    ObservableList<BikeType> bikeList = iBike.getAllBike();
    for (BikeType s : bikeList) {

      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("/se/project/gui/category/bikeCat.fxml"));
      try {
        File file = new File("src/se/project/image/"
            + s.getName() + ".jpeg");
        Image image = new Image(file.toURI().toString());
        // add bike info to pane
        Pane view = loader.load();
        BikeCatController storeP = loader.getController();
        storeP.setOrder(order);
        storeP.getBikeImg().setImage(image);
        storeP.setBikeText(s.getName());
        itemFlow.getChildren().add(view);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }
  }
    
  public void initPane() {
	  initBike();
	  initStore();
  }

 

}
