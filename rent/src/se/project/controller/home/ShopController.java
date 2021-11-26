package se.project.controller.home;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import javax.swing.JOptionPane;

import se.project.controller.Util;
import se.project.controller.category.CategoryController;
import se.project.controller.history.HistoryController;
import se.project.controller.order.OrderController;
import se.project.model.order.Order;
import se.project.util.DateUtils;

public class ShopController {
       
	@FXML
	BorderPane mainPane;
	Util util = new Util();
	Pane view = new Pane();
	@FXML
	private Button btnCategory;
	@FXML
	private Button btnHistory;
	@FXML
	private Button btnHome;
	@FXML
	private Button btnHome1;
	@FXML
	private Button btnProfile;
	@FXML
	private Button btnReturn;
	@FXML
	private Button btnSignOut;
	private HomeController home;
	private Order order;

	public BorderPane getMain() {
		return mainPane;
	}

	public void setOrder(Order o) {
		this.order = o;
	}

	@FXML
	public void handle(ActionEvent actionEvent) {
		

		if (actionEvent.getSource() == btnReturn) {
			if (order.getBike() == null) {
				JOptionPane.showMessageDialog(null, "RENT FIRST");
			} else {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/se/project/gui/order/order.fxml"));
				try {
					view = loader.load();
					OrderController or = loader.getController();
					LocalDateTime s = LocalDateTime.now();
					s.format(DateUtils.format);
					// LocalDateTime dateTime = LocalDateTime.parse(timeCreate, format);
					or.setTotalTime(order.getTotalTime(s));
					// can update tinh total lien tuc //or.setTotal
					or.setOrder(order);
					or.setTime(order.getTimeCreate());
					or.setDeposit(DateUtils.formatter.format(order.getBike().getDeposit()));
					or.setTotal(DateUtils.formatter.format(order.getTotal())); // thay vi tinh total lien tuc setattribute
					// setTotal(order.getTotal()) can ham tinh tong tien
					or.setBikeName(order.getBike().getName());
					or.setImage(order.getBike().getI()); // getImage
					mainPane.setCenter(view);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} else if (actionEvent.getSource() == btnHistory) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/se/project/gui/history/history.fxml"));
			try {
				view = loader.load();
				HistoryController his = loader.getController();
				his.setId(order.getCust().getId());
				his.initHistory();
				mainPane.setCenter(view);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (actionEvent.getSource() == btnSignOut) {
			util.change("/se/project/gui/login/login.fxml", btnSignOut);
		} else if (actionEvent.getSource() == btnHome) {
			loadHome(order);
		} else if (actionEvent.getSource() == btnCategory) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/se/project/gui/category/catPane.fxml"));
			try {
				view = loader.load();
				CategoryController or = loader.getController();
				or.setOrder(order);
				or.initPane();
				System.out.print(order.getCust().getName());
				mainPane.setCenter(view);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void loadHome(Order order) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/se/project/gui/home/home.fxml"));
		try {
			view = loader.load();
			home = loader.getController();
			home.setOrder(order);
			mainPane.setCenter(view);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
