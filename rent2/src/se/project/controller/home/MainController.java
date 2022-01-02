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
import se.project.database.api.bike.BikeDao;
import se.project.database.api.bike.IBike;
import se.project.database.api.order.IOrder;
import se.project.database.api.order.OrderDao;
import se.project.database.api.transaction.ITransaction;
import se.project.database.api.transaction.IValidTransact;
import se.project.database.api.transaction.TransactionDAO;
import se.project.database.api.transaction.ValidTransact;
import se.project.database.api.user.IUser;
import se.project.database.api.user.UserDao;
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
		IValidTransact iCheck = new ValidTransact();
           
		if (iCheck.checkTransactFinish(custId)) {

			JOptionPane.showMessageDialog(null, "RENT FIRST");
		} else {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/se/project/gui/order/order.fxml"));
			try {
				Parent root = loader.load();
				OrderController or = loader.getController();
				IOrder iOrder = new OrderDao();
				Order order = iOrder.getOrder(custId);
				order.setCustId(custId);
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
		IValidTransact iCheck = new ValidTransact();
			if (iCheck.checkTransactFinish(custId)) {
			   btnReturn.setVisible(false);
			}else  	   btnReturn.setVisible(true);
	}
	
	@FXML
	void signOut(MouseEvent event) {	
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/se/project/gui/login/login.fxml"));
		try {
			Parent root = loader.load();
			Stage stage = (Stage) (Stage) btnSignOut.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
