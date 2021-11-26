package se.project.controller.category;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class StoreController {

  @FXML
  private ImageView storeImg;
  @FXML
  private Label storeLabel;

  public ImageView getStoreImg() {
    return storeImg;
  }
  
  public void setStoreImg(ImageView storeImg) {
    this.storeImg = storeImg;
  }

  public void setText(String text) {
    storeLabel.setText(text);
  }


}
