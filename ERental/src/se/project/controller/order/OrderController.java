package se.project.controller.order;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.project.controller.Util;

import se.project.controller.pay.PayController;
import se.project.interfaces.IStore;
import se.project.interfaces.dao.StoreDao;
import se.project.model.bike.BikeType;
import se.project.model.order.Order;
import se.project.model.user.Customer;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class OrderController implements Initializable {
	
	@FXML
    private Button returnBtn;
	@FXML
    private ImageView bikeImg;

    @FXML
    private Text bikeName;

    @FXML
    private Label deposit;

    @FXML
    private Label deposit1;

    
    IStore iStore;

    @FXML
    private Label time;

    @FXML
    private Label totalTime;
    @FXML
    private Label total;
    
    public void setTime(String t) {
    	time.setText(t);
    }
    
    public void setDeposit(String t) {
    	deposit.setText(t);    
    }
    public void setTotal(String t) {
    	
    	total.setText(t);
    }
    public void setBikeName(String name) {
    	bikeName.setText(name);
    }
    public void setImage(Image m) {
    	bikeImg.setImage(m);
    }
    
 
    
    
    
    
    @FXML
    void returnBike(MouseEvent event) {
    	// change detail
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/se/project/gui/pay.fxml"));   
		try {
			Parent root = loader.load();
			PayController pay = loader.getController();
			pay.initData(order.getBike(),order);
			 
			
			order.setTimeFinish(LocalDateTime.now().format(Order.format));  // set String right format // time finish
			pay.setOrder(order);
			Stage stage = (Stage) (Stage) returnBtn.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private BikeType bike;
	private Order order;
	private Customer cust;
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public BikeType getBike() {
		return bike;
	}
	public void setBike(BikeType bike) {
		this.bike = bike;
	}
	public Customer getCust() {
		return cust;
	}
	public void setCustomer(Customer cust) {
		this.cust = cust;
	}
	
	
	
	
    @FXML
    private ChoiceBox<String> choice = new ChoiceBox<String>();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		iStore = new StoreDao();
	
		for(String s:iStore.getStoreAvai()) {
		    choice.getItems().add(s);
		}
		
	}

	public void setTotalTime(String totalT) {
		// TODO Auto-generated method stub
		totalTime.setText(totalT);
	}
		
	

}
