package se.project.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import se.project.controller.pay.PayController;
import se.project.model.bike.BikeType;
import se.project.model.order.Order;
import se.project.model.user.Customer;
import se.project.util.BikeUtils;

public class ItemController implements Initializable {

	@FXML
	private ImageView img;

	@FXML
	private Label license;

	@FXML
	private Label manufacture;

	@FXML
	private Button backBtn;
	
	@FXML
	private Label name;
	
	@FXML
	private AnchorPane paneItem;

	@FXML
	private Label producer;

	@FXML
	private Button rent;

	@FXML
	private Label type;

	@FXML
	private Label weight;

	private Order order;
	private PayController pay;
	private BikeType bike;
	private BikeType bikeRent;
	private ShopController shop;
    private Customer cust;
    
    public void setCustomer(Customer c) {
    	this.cust = c;
    }
	public void setBike(BikeType bike) {
		this.bikeRent = bike;

	}

	public BikeType getBikeRent() {
		return bikeRent;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public ImageView getImg() {
		return img;
	}

	public void setImg(ImageView image) {
		this.img = image;
	}

	public Label getLicense() {
		return license;
	}

	public void setLicense(Label license) {
		this.license = license;
	}

	public Label getManufacture() {
		return manufacture;
	}

	public void setManufacture(Label manufacture) {
		this.manufacture = manufacture;
	}

	public Label getName() {
		return name;
	}

	public void setName(Label name) {
		this.name = name;
	}

	public AnchorPane getPaneItem() {
		return paneItem;
	}

	public void setPaneItem(AnchorPane paneItem) {
		this.paneItem = paneItem;
	}

	public Label getProducer() {
		return producer;
	}

	public void setProducer(Label producer) {
		this.producer = producer;
	}

	public Label getType() {
		return type;
	}

	public void setType(Label type) {
		this.type = type;
	}

	public Label getWeight() {
		return weight;
	}

	public void setWeight(Label weight) {
		this.weight = weight;
	}

	public BikeType getBike() {
		return bike;
	}
    
	//set BikeType
	public void setBik(BikeType bike2) {
		// TODO Auto-generated method stub
		this.bike = bike2;
	}

	@FXML
	public void rentItem(MouseEvent event) {
		 
			
		if (bikeRent == null ) {
			// check bike rent or not befrore add
			if( BikeUtils.checkBikeRent(bike.getName())) {
				JOptionPane.showMessageDialog(null, "On rent");
			}else {
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/se/project/gui/pay.fxml"));
                            
				try {
					setBike(bike);
					order.setBike(bike);
					//setBik(bike)
					JOptionPane.showMessageDialog(null, "Add to rent");
					System.out.print(order.getBike().getId()); 
					Parent root = loader.load();
					
					// access the controller and call a method
					// access the controller and call a method
					pay = loader.getController();
					pay.initData(order.getBike(),order);
					pay.setOrder(order);
                     
					// System.out.print(order.getBike().getManufacture());
					Stage stage = (Stage) (Stage) rent.getScene().getWindow();
					stage.setScene(new Scene(root));
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "Finish transact first");
		}
	}

	// lan sau vao lai k con luu bike rent
	@FXML
	public void back(MouseEvent event) {
		
		//setBike(bikeRent);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/se/project/gui/shop.fxml"));
		try {
			Parent root = loader.load();
			shop = loader.getController();

			shop.setOrder(order);
			shop.init(order,cust,order.getBike());
			System.out.print(order.getCust().getName()); 
			
			// load
			Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setOrder(Order order2) {
		// TODO Auto-generated method stub
		this.order = order2;
	}
}
