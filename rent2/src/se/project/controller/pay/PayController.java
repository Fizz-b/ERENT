package se.project.controller.pay;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import se.project.controller.home.HomeController;
import se.project.dao.BikeDao;
import se.project.dao.UserDao;
import se.project.interfaces.IBike;
import se.project.interfaces.IUser;
import se.project.model.bike.BikeType;
import se.project.model.order.Order;
import se.project.model.user.Customer;
import se.project.util.DateUtils;

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
  private Label excessCash;
  
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

 
  private Order order;
  private HomeController shop;
  private int custId;
  public void setId(int id) {
	  this.custId = id;
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
    loader.setLocation(getClass().getResource("/se/project/gui/home/search.fxml"));
    try {
      Parent root = loader.load();
      shop = loader.getController();
      shop.setId(order.getCustId());
      
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
      loader.setLocation(getClass().getResource("/se/project/gui/pay/payment.fxml"));

      try {
        Parent root = loader.load();
        
        BankGateController controller = loader.getController();
        controller.setOrder(order);
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

  public void initData(Order order) {
	  IBike iBike = new BikeDao();
	  BikeType bike = iBike.getBikeById(Integer.toString(order.getBikeId()));
    // init data
    //setBikeData(bike);
    bikeName.setText(bike.getName());
    deposit.setText(DateUtils.formatter.format(bike.getDeposit()));
    rentFee.setText(DateUtils.formatter.format(order.getTotal()));
     if(order.getId()!=0) {
    	 // not work
    	 excessCash.setText(DateUtils.formatter.format(bike.getDeposit()));
    	
			// LocalDateTime dateTime = LocalDateTime.parse(timeCreate, format);
		 
    	 time.setText(DateUtils.date(order.getTime()));
    	 double a = order.getTotal();
    	 total.setText(DateUtils.formatter.format(a)); 
    	 total1.setText(DateUtils.formatter.format(a));
    	 
     }else {
    total.setText(DateUtils.formatter.format(bike.getDeposit())); // tinh tong
    total1.setText(DateUtils.formatter.format(bike.getDeposit()));} // tinh tong
    IUser iUser = new  UserDao();
    String userName =  iUser.getNameById(Integer.toString(order.getCustId()));
    name.setText(userName);
    cost.setText(Integer.toString(bike.getCost()));
    img.setImage(bike.getI());
    // load pane
    payPane.toFront();

  }

}
