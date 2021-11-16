package se.project.controller.home;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import se.project.database.Context;
import se.project.interfaces.IBike;
import se.project.interfaces.IStore;
import se.project.interfaces.dao.BikeDao;
import se.project.interfaces.dao.StoreDao;
import se.project.model.bike.BikeType;
import se.project.model.order.Order;
import se.project.model.store.Store;
import se.project.model.user.Customer;

public class HomeController implements Initializable {

	Context context = new Context();
	@FXML
	private TableColumn<Store, String> storeNameCol;
	@FXML
	private TableColumn<Store, String> storeAddressCol;
	@FXML
	private TableColumn<Store, Integer> storeAvaiCol;
	@FXML
	private TableColumn<Store, Integer> storeRentCol;
	@FXML
	private TableColumn<Store, String> storeStatusCol;
	@FXML
	private TableView<Store> staffTableView;
	@FXML
	private TableColumn<BikeType, String> bikeName;
	@FXML
	private TableColumn<BikeType, String> bikeType;
	@FXML
	private TableColumn<BikeType, Integer> bikeManu; // manufacture
	@FXML
	private TableColumn<BikeType, String> bikeProducer;
	@FXML
	private TableColumn<BikeType, Integer> bikeCost;
	@FXML
	private TableView<BikeType> staffBike;
	@FXML
	private Pane pnlOverview1;
	private BikeType bike;
	private BikeType bikeRent;
	private Order order;

	private IBike iBike = new BikeDao();
	private IStore iStore;

	public BikeType getBikeRent() {
		return bikeRent;
	}

	public void setBikeRent(BikeType b) {
		this.bikeRent = b;
	}

	public BikeType getBikeData() {
		return bike;
	}

	public void setBikeData(BikeType b) {
		this.bike = b;
	}

	public void setOrder(Order order2) {
		this.order = order2;
	}

	// click to a store load bike of that store
	@FXML
	void chooseRow() {

		staffBike.setItems(iBike.getListFromDB(staffTableView.getSelectionModel().getSelectedItem().getName()));
		staffBike.toFront();
	}

	// click bike load detail of that bike
	@FXML
	void chooseBike(MouseEvent event) {

		bike = iBike.getBikeFromDB(staffBike.getSelectionModel().getSelectedItem().getName());

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/se/project/gui/home/itemDetail.fxml"));
		try {

			Parent root = loader.load();
			ItemController controller = loader.getController();
			
			controller.setBik(bike); // bike thuong
			if (order.getBike() == null) {
				bikeRent = null;
			} else {
				bikeRent = order.getBike(); // order chua co thi bikeRENT = BIKE (load xe da thue)
			}
			controller.setBike(bikeRent); // load bike rent
			controller.initItem(bike); // load anh bike
			controller.setOrder(order);

			// load
			Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void initialize(URL location, ResourceBundle resources) {

		storeNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		storeAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
		storeAvaiCol.setCellValueFactory(new PropertyValueFactory<>("available"));
		storeRentCol.setCellValueFactory(new PropertyValueFactory<>("rent"));
		storeStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
		iStore = new StoreDao();
		staffTableView.setItems(iStore.getListFromDB());

		bikeName.setCellValueFactory(new PropertyValueFactory<>("name")); // map with type in class BikeType
		bikeType.setCellValueFactory(new PropertyValueFactory<>("type"));
		bikeManu.setCellValueFactory(new PropertyValueFactory<>("manufacture"));
		bikeProducer.setCellValueFactory(new PropertyValueFactory<>("producer"));
		bikeCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
		pnlOverview1.toFront();
		staffTableView.toFront();

	}

}
