package se.project.controller.home;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import se.project.controller.Util;
import se.project.controller.history.HistoryController;
import se.project.controller.order.OrderController;
import se.project.dao.BikeDao;
import se.project.dao.OrderDao;
import se.project.dao.TransactionDAO;
import se.project.dao.UserDao;
import se.project.interfaces.IBike;
import se.project.interfaces.IOrder;
import se.project.interfaces.ITransaction;
import se.project.interfaces.IUser;
import se.project.model.order.Order;
import se.project.model.user.Customer;
import se.project.util.DateUtils;

public class MainController {
	@FXML
	private Button btnHistory;
	@FXML
	private Button btnSignOut;
	@FXML
	private Button btnReturn;
	@FXML
	private Button btnSearch;

	private int custId;

	public void setId(int i) {
		this.custId = i;
	}

	@FXML
	void loadHistory(MouseEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/se/project/gui/history/history.fxml"));
		try {
			Parent root = loader.load();
			HistoryController his = loader.getController();
			his.setId(custId);
			his.initHistory();
			Stage stage = (Stage) (Stage) btnHistory.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void loadSearch(MouseEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/se/project/gui/home/search.fxml"));
		try {
			Parent root = loader.load();
			HomeController home = new HomeController();
			home = loader.getController();
			home.setId(custId);
			Stage stage = (Stage) (Stage) btnHistory.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void loadReturn(MouseEvent event) {
		ITransaction iTransact = new TransactionDAO();

		if (iTransact.checkTransactFinish(custId)) {

			JOptionPane.showMessageDialog(null, "RENT FIRST");
		} else {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/se/project/gui/order/order.fxml"));
			try {
				Parent root = loader.load();
				OrderController or = loader.getController();

				IOrder iOrder = new OrderDao();
				Order order = iOrder.getOrder(custId);
				IUser cust = new UserDao();
				Customer customer = cust.getUserById(Integer.toString(custId));
				order.setCust(customer);
				or.setOrder(order);
				or.initPane(order);
				Stage stage = (Stage) (Stage) btnReturn.getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
    
	public void initButton() {
		   ITransaction iTransact = new TransactionDAO();
			if (iTransact.checkTransactFinish(custId)) {
			   btnReturn.setVisible(false);
			}else  	   btnReturn.setVisible(true);
	}
	
	@FXML
	void signOut(MouseEvent event) {
		Util util = new Util();
		util.change("/se/project/gui/login/login.fxml", btnSignOut);
	}

}
