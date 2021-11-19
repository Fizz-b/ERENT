package se.project.controller.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import se.project.controller.pay.PayController;
import se.project.model.bike.BikeType;
import se.project.model.order.Order;
import se.project.model.user.Customer;
import se.project.util.BikeUtils;

public class ItemController {

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
	private BikeType bike;
	private PayController pay;
	private ShopController shop;

	public BikeType getBike() {
		return bike;
	}

	public void setOrder(Order order2) {
		// TODO Auto-generated method stub
		this.order = order2;
	}

	// set BikeType
	public void setBike(BikeType bike2) {
		// TODO Auto-generated method stub
		this.bike = bike2;
	}

	@FXML
	public void rentItem(MouseEvent event) {

		if (order.getBike() == null) {
			// check bike rent or not befrore add
			if (BikeUtils.checkBikeRent(bike.getName())) {
				JOptionPane.showMessageDialog(null, "On rent");
			} else {

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/se/project/gui/pay/pay.fxml"));

				try {
					// setBike(bike);
					order.setBike(bike);
					// setBik(bike)
					JOptionPane.showMessageDialog(null, "Add to rent");

					Parent root = loader.load();

					// access the controller and call a method
					// access the controller and call a method
					pay = loader.getController();
					pay.initData(order.getBike(), order);
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
		} else {
			JOptionPane.showMessageDialog(null, "Finish transact first");
		}
	}

	// lan sau vao lai k con luu bike rent
	@FXML
	public void back(MouseEvent event) {

		// setBike(bikeRent);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/se/project/gui/home/shop.fxml"));
		try {
			Parent root = loader.load();
			shop = loader.getController();
			shop.setOrder(order);
			shop.loadHome(order);
    
			// load
			Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initItem(BikeType bike) {
		img.setImage(bike.getI());
		setBike(bike);
		name.setText(bike.getName());
		type.setText(bike.getType());
		weight.setText(Integer.toString(bike.getWeight()));
		license.setText(bike.getLicense());
		manufacture.setText(bike.getManufacture());
		producer.setText(bike.getProducer());
	}

}
