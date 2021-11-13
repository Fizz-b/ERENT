package se.project.controller;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import se.project.interfaces.IBike;
import se.project.interfaces.IStore;
import se.project.interfaces.dao.BikeDao;
import se.project.interfaces.dao.StoreDao;
import se.project.model.store.Store;

public class CategoryController implements Initializable{

    @FXML
    private FlowPane itemFlow;

    @FXML
    private Pane pane11;

    @FXML
    private FlowPane storeFlow;
   
    private IBike iBike = new BikeDao();
	private IStore iStore = new StoreDao();
	public void init() {
		ObservableList<Store> store = iStore.getListFromDB();
		for(Store s:store) {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/se/project/gui/storeCat.fxml"));
			try {
				File file = new File("src/se/project/image/"
						+ s.getId() + ".jpeg");
				Image image = new Image(file.toURI().toString());
				// add bike info to pane
				Pane view = loader.load();
				StoreController storeP = loader.getController();
				storeP.getStoreImg().setImage(image);
				storeP.setText(s.getName());
				storeFlow.getChildren().add(view);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		init();
	}

}
