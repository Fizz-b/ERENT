package se.project.controller.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import se.project.dao.BikeDao;
import se.project.dao.StoreDao;
import se.project.database.Context;
import se.project.interfaces.IBike;
import se.project.interfaces.IStore;
import se.project.model.bike.BikeType;
import se.project.model.order.Order;
import se.project.model.store.Store;

public class HomeController implements Initializable {
	private int custId;
	public void setId(int i) {
		this.custId = i;
	}
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
	private TableView<Store> storeTable;
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
	private TableView<BikeType> bikeTable;
	@FXML
	private Pane pnlOverview1;
	@FXML
	private TextField searchBar;
	@FXML
	private Pane searchPane;
	@FXML
	private Button backBtn;
	private BikeType bike;
	
	private IBike iBike = new BikeDao();
	private IStore iStore;
    

	

	// click to a store load bike of that store
	@FXML
	void chooseRow() {
		ObservableList<BikeType> dataList = iBike
				.getListFromDB(storeTable.getSelectionModel().getSelectedItem().getName());
		bikeTable.setItems(dataList);

		// Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<BikeType> filteredData = new FilteredList<>(dataList, b -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(biketype -> {
				// If filter text is empty, display all persons.

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (biketype.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				}

				else
					return false; // Does not match.
			});
		});

		// 3. Wrap the FilteredList in a SortedList.
		SortedList<BikeType> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		// Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(bikeTable.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		bikeTable.setItems(sortedData);
		bikeTable.toFront();
	}

	// click bike load detail of that bike
	@FXML
	void chooseBike(MouseEvent event) {

		bike = iBike.getBikeFromDB(bikeTable.getSelectionModel().getSelectedItem().getName());
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/se/project/gui/home/itemDetail.fxml"));
		try {

			Parent root = loader.load();
			ItemController controller = loader.getController();
			controller.setBik(bike); // bike thuong
        
        
			controller.setId(custId);
            controller.initItem(bike);
			// load
			Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
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
			home.setId(custId);
			home.initButton();
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
		storeAvaiCol.setCellValueFactory(new PropertyValueFactory<>("available")); // empty dock
		storeRentCol.setCellValueFactory(new PropertyValueFactory<>("rent"));
		storeStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
		iStore = new StoreDao();


		bikeName.setCellValueFactory(new PropertyValueFactory<>("name")); // map with type in class BikeType
		bikeType.setCellValueFactory(new PropertyValueFactory<>("type"));
		bikeManu.setCellValueFactory(new PropertyValueFactory<>("manufacture"));
		bikeProducer.setCellValueFactory(new PropertyValueFactory<>("producer"));
		bikeCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
		pnlOverview1.toFront();
		storeTable.toFront();

		ObservableList<Store> dataList = iStore.getListFromDB();
		// Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Store> filteredData = new FilteredList<>(dataList, b -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(store -> {
				// If filter text is empty, display all persons.

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (store.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				} else if (store.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}

				else
					return false; // Does not match.
			});
		});
          
		// 3. Wrap the FilteredList in a SortedList.
		SortedList<Store> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		// Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(storeTable.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		storeTable.setItems(sortedData);
		storeTable.toFront();

	}

}
