package se.project.controller.category;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import se.project.controller.home.ItemController;
import se.project.interfaces.IBike;
import se.project.interfaces.dao.BikeDao;
import se.project.model.bike.BikeType;
import se.project.model.order.Order;

public class BikeCatController {

  @FXML
  private ImageView bikeImg;

  @FXML
  private Text bikeText;

  private Order order;

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order o) {
    this.order = o;
  }

  public void setBikeText(String t) {
    bikeText.setText(t);
  }

  public ImageView getBikeImg() {
    return bikeImg;
  }

  public void setBikeImg(ImageView storeImg) {
    this.bikeImg = storeImg;
  }

  @FXML
  void loadDetail(MouseEvent event) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/se/project/gui/home/itemDetail.fxml"));
    try {
      IBike ibike = new BikeDao();
      Parent root = loader.load();
      BikeType bike = ibike.getBikeFromDB(bikeText.getText());
      ItemController controller = loader.getController();
      // add bike info to pane
      if (order.getBike() == null) {
        controller.setBike(null);
      } else {
        controller.setBike(order.getBike());  // order chua co thi bikeRENT = BIKE
      }
      controller.initItem(bike);
      controller.setOrder(order);

      Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(new Scene(root));
      stage.show();

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
}