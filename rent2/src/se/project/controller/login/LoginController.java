package se.project.controller.login;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import se.project.controller.Util;
import se.project.controller.home.MainController;
import se.project.database.api.order.IOrder;
import se.project.database.api.order.OrderDao;
import se.project.database.api.user.IUser;
import se.project.database.api.user.UserDao;
import se.project.model.order.Order;
import se.project.model.user.Customer;
import se.project.util.Login;

public class LoginController {

	@FXML private Button signInButton;
	@FXML private Button signUpButton;
	@FXML private TextField user;
	@FXML private TextField pass;
	@FXML
	public void signIn() {
		// Login session
		if (Login.validate(user.getText(), pass.getText())) {
			IUser  cust = new UserDao();
	
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/se/project/gui/home/home.fxml"));
			try {
				Parent root = loader.load();
			
				MainController shop = new MainController();
				shop = loader.getController();
				Customer customer = cust.getUser(user.getText()); // get user detail
		        shop.setId(customer.getId());
		        shop.initButton();
				 // load pane
                 
				Stage stage = (Stage) (Stage) signInButton.getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JOptionPane.showMessageDialog(null, "You are logged in");

		} else {
			JOptionPane.showMessageDialog(null, "Wrong password or username");
		}

	}

	@FXML
	public void signUp() {
		Login.signUp(user.getText(), pass.getText());
	}
	
    @FXML
    void adminLog(MouseEvent event) {
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/se/project/gui/admin/admin.fxml"));
		try {
			Parent root = loader.load();   
			Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
