package se.project.controller.history;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import se.project.dao.HistoryDao;
import se.project.interfaces.IHistory;
import se.project.model.history.ItemHistory;
import se.project.util.DateUtils;

public class HistoryController{

	@FXML
	private FlowPane rentFlow;

	@FXML
	private FlowPane returnFlow;
	private int id;
	private IHistory r = new HistoryDao();

	public void setId(int i) {
		this.id = i;
	}

	public void init(FlowPane pane, ArrayList<ItemHistory> list) {
		for (ItemHistory i : list) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/se/project/gui/history/DepositHistory.fxml"));
			try {
				File file = new File("src/se/project/image/" + i.getBikeName() + ".jpeg");
				Image image = new Image(file.toURI().toString());
				// add bike info to pane
				Pane view = loader.load();
				RentHistoryController storeP = loader.getController();
				storeP.initItem(i.getBikeName(), i.getTime(), DateUtils.formatter.format(i.getMoney()), image);
				pane.getChildren().add(view);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
    
	public void initHistory() {
		init(rentFlow, r.getRentHistory(id));       // init rent his
		init(returnFlow, r.getReturnHistory(id));   // init return his
	}
	

}
