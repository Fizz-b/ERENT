package se.project.controller.login;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import se.project.controller.ShopController;
import se.project.controller.Util;
import se.project.database.Context;
import se.project.database.MySQLConnection;
import se.project.interfaces.IOrder;
import se.project.interfaces.IUser;
import se.project.interfaces.dao.OrderDao;
import se.project.interfaces.dao.UserDao;
import se.project.model.order.Order;
import se.project.model.user.Customer;
import se.project.util.Login;

public class LoginController {
    
    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField user;

    @FXML
    private TextField pass;

    Util util = new Util();
     
    private Order order ;
    private Customer customer;
    
    private IUser cust;
    private IOrder iOrder = new OrderDao();
    @FXML
    public void signIn() {
        // Login session
    	if(Login.validate(user.getText(),pass.getText())) {
    		cust = new UserDao();
    		
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("/se/project/gui/shop.fxml"));
    		try {
				Parent root = loader.load();
				// access the controller and call a method
				// access the controller and call a method
				ShopController shop = new ShopController();
				shop = loader.getController();
				customer = cust.getUser(user.getText()); 
				order = iOrder.getOrder(customer.getId()); // check order
				//if(order.getBike()==null) {System.out.print("hello");}
				//else System.out.print(order.getBike().getManufacture());	
				order.setCust(customer);
				
				shop.setOrder(order); // set bike info
				if(order ==null) {
					shop.init(order, customer, null);
				}else
				{shop.init(order, customer, order.getBike());}
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
      Login.signUp(user.getText(),pass.getText());
    }

	

}
