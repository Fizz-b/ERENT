package se.project.controller.history;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import se.project.model.history.ItemHistory;

public class RentHistoryController {

  @FXML
  private ImageView img;
  @FXML
  private Label bikeName;

  @FXML
  private Label money;

  @FXML
  private Label time;
  private ItemHistory item;

  public void initItem(String n, String t, String m, Image im) {
    bikeName.setText(n);
    time.setText(t);
    money.setText(m);
    img.setImage(im);
  }

  public ItemHistory getItem() {
    return item;
  }

  public void setItem(ItemHistory item) {
    this.item = item;
  }
}


