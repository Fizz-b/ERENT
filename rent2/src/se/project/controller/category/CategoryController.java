package se.project.controller.category;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import se.project.controller.Control;
import se.project.controller.home.HomeController;
import se.project.dao.BikeDao;
import se.project.dao.StoreDao;
import se.project.interfaces.IBike;
import se.project.interfaces.IStore;
import se.project.model.bike.BikeType;
import se.project.model.order.Order;
import se.project.model.store.Store;

public class CategoryController {

	@FXML
	private FlowPane itemFlow;
	@FXML
	private Label storeText;
	@FXML
	private Pane pane11;

	private int custId;

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}
    public void setStoreName(String name) {
    	storeText.setText(name);
    }
	public void initBike(ObservableList<BikeType> bikeList) {

		for (BikeType s : bikeList) {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/se/project/gui/category/bikeCat.fxml"));
			try {
				File file = new File("src/se/project/image/" + s.getName() + ".jpeg");
				Image image = new Image(file.toURI().toString());
				// add bike info to pane
				Pane view = loader.load();
				BikeCatController storeP = loader.getController();
				storeP.setCustId(custId);
				storeP.setStatus(s.getStatus());
				storeP.getBikeImg().setImage(image);
				storeP.setBikeText(s.getName());
				itemFlow.getChildren().add(view);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@FXML
	public void back(MouseEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/se/project/gui/home/search.fxml"));
		try {
			Parent root = loader.load();
			HomeController home = new HomeController();
			home = loader.getController();
			home.setId(custId);
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
