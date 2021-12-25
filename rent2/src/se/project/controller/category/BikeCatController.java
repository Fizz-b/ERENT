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
import se.project.dao.BikeDao;
import se.project.interfaces.IBike;
import se.project.model.bike.BikeType;
import se.project.model.order.Order;

public class BikeCatController {

	@FXML
	private ImageView bikeImg;

	@FXML
	private Text bikeText;

	private int custId;

    @FXML
    private Text status;
	

	public Text getStatus() {
		return status;
	}

	public void setStatus(String string) {
		status.setText(string);
	}

	public void setCustId(int custId) {
		this.custId = custId;
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
		IBike iBike = new BikeDao();
		BikeType bike = iBike.getBikeFromDB(bikeText.getText());  // get selected
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/se/project/gui/home/itemDetail.fxml"));
		try {

			Parent root = loader.load();
			ItemController controller = loader.getController();
			//controller.setBik(bike); 
			controller.setId(custId);
            controller.initItem(bike);
			// load
			Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}