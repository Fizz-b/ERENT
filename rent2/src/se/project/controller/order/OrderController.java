package se.project.controller.order;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
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
import se.project.dao.StoreDao;
import se.project.interfaces.IStore;
import se.project.model.bike.BikeType;
import se.project.model.order.Order;
import se.project.model.user.Customer;
import se.project.util.DateUtils;

public class OrderController implements Initializable {

	IStore iStore;
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
	@FXML
	private Label time;

	@FXML
	private Label totalTime;
	@FXML
	private Label total;
	private BikeType bike;
	private Order order;

	public void setOrder(Order o) {
		this.order = o;
	}

	@FXML
	private ChoiceBox<String> choice = new ChoiceBox<String>();

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

	public BikeType getBike() {
		return bike;
	}

	public void setBike(BikeType bike) {
		this.bike = bike;
	}

	public void setTotalTime(String totalT) {
		// TODO Auto-generated method stub
		totalTime.setText(totalT);
	}

	@FXML
	void returnBike(MouseEvent event) {
		// change detail
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/se/project/gui/pay/pay.fxml"));
		try {
			Parent root = loader.load();
			PayController pay = loader.getController();
			String value = (String) choice.getValue();
			iStore = new StoreDao();

			order.setReturnId(iStore.getStoreId(value)); // can bao neu chua chon store return
			LocalDateTime dateTime = LocalDateTime.now();
			order.getTotalTime(dateTime); // tinh tong thoi gian thanh toan
			order.setTimeFinish(dateTime.format(DateUtils.format)); // set String right format // time finish
			pay.setOrder(order);
			pay.initData(order.getBike(), order);

			Stage stage = (Stage) (Stage) returnBtn.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			home.setId(order.getCust().getId());
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
		LocalDateTime s = LocalDateTime.now();
		s.format(DateUtils.format);
		setTotalTime(order.getTotalTime(s));
		setTime(order.getTimeCreate());
		setDeposit(DateUtils.formatter.format(order.getBike().getDeposit()));
		setTotal(DateUtils.formatter.format(order.getTotal()));
		setBikeName(order.getBike().getName());
		setImage(order.getBike().getI()); // getImage
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		iStore = new StoreDao();
		// chi tra ve store co the nhan xe
		for (String s : iStore.getStoreAvai()) {
			choice.getItems().add(s);
		}
	}

}
