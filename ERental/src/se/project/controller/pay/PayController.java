package se.project.controller.pay;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import se.project.controller.ShopController;
import se.project.controller.StoreController;
import se.project.controller.Util;
import se.project.model.bike.BikeType;
import se.project.model.order.Order;
import se.project.model.user.Customer;

public class PayController {
	   @FXML
	    private ImageView img;
	@FXML
	private Button rent;

	@FXML
	private Pane payPane;

	@FXML
	private Label remove;

	@FXML
	private Button backBtn;

	@FXML
	private Label total;

	@FXML
	private Label deposit;

	@FXML
	private Text bikeName;

	@FXML
	private Label time;

	@FXML
	private Label rentFee;

	@FXML
	private Label cost;
	@FXML
	private CheckBox cash;

	@FXML
	private CheckBox credit;

	@FXML
	private Label name;

	@FXML
	private Label total1;

	private Customer customer;
	private BikeType bike;
	private Order order;
	private ShopController shop;

	public void setRemove(Label remove) {
		this.remove = remove;
	}

	public void setTotal(Label total) {
		this.total = total;
	}

	public void setDeposit(Label deposit) {
		this.deposit = deposit;
	}

	public void setBikeName(Text bikeName) {
		this.bikeName = bikeName;
	}

	public void setTime(Label time) {
		this.time = time;
	}

	public void setRentFee(Label rentFee) {
		this.rentFee = rentFee;
	}

	public void setCost(Label cost) {
		this.cost = cost;
	}

	public void setName(Label name) {
		this.name = name;
	}

	public void setTotal1(Label total1) {
		this.total1 = total1;
	}

	public void setBike(BikeType bike) {
		this.bike = bike;
	}

	public void setShop(ShopController shop) {
		this.shop = shop;
	}

	public void setCustomer(Customer c) {
		this.customer = c;
	}

	public void setBikeData(BikeType b) {
		this.bike = b;
	}

	public BikeType getBikeData() {
		return bike;
	}

	public void setOrder(Order order2) {
		this.order = order2;

	}

	public void setLabelText(String depo) {
		deposit.setText(depo);
		total.setText(depo);
	}

	@FXML
	public void back(MouseEvent event) {
		// back to shop
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/se/project/gui/shop.fxml"));
		
		try {
			Parent root = loader.load();
			shop = loader.getController();
			order.setBike(null);
			shop.setOrder(order);
			shop.init(order, customer, order.getBike());
			Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void rentItem(MouseEvent event) {
		// check select pay method
		if (credit.isSelected() || cash.isSelected()) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/se/project/gui/payment.fxml"));
			
			try {
				Parent root = loader.load();
				bankGateController controller = loader.getController();
				
				order.setBike(getBikeData());
				controller.setOrder(order);
				System.out.print(order.getBike().getId());
				
				// load
				Stage stage = (Stage) rent.getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Pick payment method first");
		}
	}

	public void initData(BikeType bike, Order order) {

		// init data
		setBikeData(bike);
		bikeName.setText(bike.getName());
		deposit.setText(Integer.toString(bike.getDeposit()));
		total.setText(Integer.toString(bike.getDeposit())); // tinh tong
		total1.setText(Integer.toString(bike.getDeposit())); // tinh tong
		name.setText(order.getCust().getName());
		cost.setText(Integer.toString(bike.getCost()));
		File file = new File("src/se/project/image/"
				+ bike.getName() + ".jpeg");
		Image image = new Image(file.toURI().toString());
		img.setImage(image);
		// load pane
		payPane.toFront();

	}

}
