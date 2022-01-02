package se.project.controller.order;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import se.project.controller.home.MainController;
import se.project.controller.pay.PayController;
import se.project.database.api.bike.BikeDao;
import se.project.database.api.bike.IBike;
import se.project.database.api.store.IStore;
import se.project.database.api.store.StoreDao;
import se.project.model.bike.BikeType;
import se.project.model.order.Order;
import se.project.model.user.Customer;
import se.project.util.DateUtils;

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
	private Label orderCode;
	@FXML
	private Label deposit1;
	@FXML
	private Label time;

	@FXML
	private Label totalTime;
	@FXML
	private Label total;
	private Order order;
	@FXML
	private ChoiceBox<String> choice = new ChoiceBox<String>();
	
	public void setOrder(Order o) {
		this.order = o;
	}





	@FXML
	void returnBike(MouseEvent event) {
		// change detail
		String storeReturn = (String) choice.getValue();
		if(storeReturn == null) {
			JOptionPane.showMessageDialog(null, "Choose store to return");
		}else {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/se/project/gui/pay/pay.fxml"));
		try {
			Parent root = loader.load();
			PayController pay = loader.getController();
		
			IStore iStore = new StoreDao();

			order.setReturnId(iStore.getStoreId(storeReturn)); 
			LocalDateTime dateTime = LocalDateTime.now();
			order.getTotalTime(dateTime); // tinh tong thoi gian thanh toan
			order.setTimeFinish(dateTime.format(DateUtils.format)); // set String right format // time finish
			pay.setOrder(order);
			
			IBike iBike = new BikeDao();
			BikeType bike = iBike.getBikeById(Integer.toString(order.getBikeId()));
			pay.initData(order);

			Stage stage = (Stage) (Stage) returnBtn.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	@FXML
	void back(MouseEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/se/project/gui/home/home.fxml"));
		try {
			Parent root = loader.load();
			MainController home = new MainController();
			home = loader.getController();
			home.setId(order.getCustId());
			home.initButton();
			Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initPane(Order order) {
		IBike iBike = new BikeDao();
		BikeType bike = iBike.getBikeById(Integer.toString(order.getBikeId()));
		LocalDateTime s = LocalDateTime.now();
		s.format(DateUtils.format);
		
		orderCode.setText(Integer.toString(order.getId()));
		totalTime.setText(order.getTotalTime(s));
		time.setText(order.getTimeCreate());
		deposit.setText(DateUtils.formatter.format(bike.getDeposit()));
		total.setText(DateUtils.formatter.format(order.getTotal()));
		bikeName.setText(bike.getName());
		bikeImg.setImage(bike.getI()); // getImage
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		IStore iStore = new StoreDao();
		// chi tra ve store co the nhan xe
		for (String s : iStore.getStoreAvai()) {
			choice.getItems().add(s);
		}
	}
	

    @FXML
    void refresh(MouseEvent event) {
    	LocalDateTime s = LocalDateTime.now();
		s.format(DateUtils.format);
		totalTime.setText(order.getTotalTime(s));
		total.setText(DateUtils.formatter.format(order.getTotal()));
    }

}
