package se.project.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import se.project.controller.order.OrderController;
import se.project.controller.pay.PayController;
import se.project.database.Context;
import se.project.model.bike.BikeType;
import se.project.model.order.Order;
import se.project.model.store.Store;
import se.project.model.user.Customer;

public class ShopController {

	@FXML
	BorderPane mainPane;

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

	private BikeType bike;

	public BorderPane getMain() {
		return mainPane;
	}

	public void setBikeData(BikeType b) {
		this.bike = b;

	}

	public BikeType getBikeData() {
		return bike;
	}

	public void setOrder(Order o) {
		this.order = o;
	}

	Util util = new Util();
	Pane view = new Pane();
	private HomeController home;
	private PayController pay;
	private Order order;
	private ItemController item;
	private Customer cust;

	public void setCustomer(Customer cust) {
		this.cust = cust;
	}

	@FXML
	public void handle(ActionEvent actionEvent) {
		if (actionEvent.getSource() == btnReturn) {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/se/project/gui/order.fxml"));
			try {
				view = loader.load();
				OrderController or = loader.getController();
				LocalDateTime s =  LocalDateTime.now();
				 s.format(Order.format);
			    //LocalDateTime dateTime = LocalDateTime.parse(timeCreate, format);
				or.setTotalTime(order.getTotalTime(s));
			    // can update tinh total lien tuc //or.setTotal
				or.setOrder(order);
				or.setTime(order.getTimeCreate());
				or.setDeposit(Integer.toString(order.getBike().getDeposit()));
				// setTotal(order.getTotal()) can ham tinh tong tien
				
				or.setBikeName(order.getBike().getName());
				File file = new File("src/se/project/image/" + order.getBike().getName() + ".jpeg");
				Image image = new Image(file.toURI().toString());
				or.setImage(image);

				mainPane.setCenter(view);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (actionEvent.getSource() == btnHistory) {
			view = ((Util) util).getPage("/se/project/gui/history.fxml");
			mainPane.setCenter(view);

		} else if (actionEvent.getSource() == btnSignOut) {
			util.change("/se/project/gui/login.fxml", btnSignOut);
		} else if (actionEvent.getSource() == btnHome) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/se/project/gui/home.fxml"));
			try {
				view = loader.load();
				home = loader.getController();
				home.setOrder(order);
				mainPane.setCenter(view);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (actionEvent.getSource() == btnCategory) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/se/project/gui/catPane.fxml"));
			try {
				view = loader.load();
				CategoryController or = loader.getController();
				mainPane.setCenter(view);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void init(Order order, Customer cust, BikeType bike) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/se/project/gui/home.fxml"));
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
