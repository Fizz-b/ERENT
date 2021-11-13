package se.project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class StoreController {

    @FXML
    private ImageView storeImg;

    public void setStoreImg(ImageView storeImg) {
		this.storeImg = storeImg;
	}

	public ImageView getStoreImg() {
		return storeImg;
	}

	@FXML
    private Label storeLabel;
    
    public void setText(String text) {
    	storeLabel.setText(text);
    }

	
}
