package se.project.controller.pay;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import se.project.dao.OrderDao;
import se.project.dao.StoreDao;
import se.project.dao.TransactionDAO;
import se.project.interfaces.IMessageService;
import se.project.interfaces.IOrder;
import se.project.interfaces.IStore;
import se.project.interfaces.ITransaction;
import se.project.model.order.Order;
import se.project.model.payment.CreditCard;
import se.project.model.payment.PayByCard;
import se.project.model.payment.Payment;
import se.project.util.DateUtils;
import se.project.util.EmailService;

import se.project.util.TransactionUtils;

public class bankGateController implements Initializable {

 
  @FXML
  private Button payBtn;
  @FXML
  private Button backButton;
  @FXML
  private TextArea messageA;
  @FXML
  private TextField bank;
  @FXML
  private TextField cardNum;
  @FXML
  private DatePicker date;
  @FXML
  private TextField name;
  private IOrder iOrder = new OrderDao();
  private ITransaction iTransaction = new TransactionDAO();
  private IStore iStore = new StoreDao();
  private Order order; // need to pass order from
  String formatDateTime;
  CreditCard card = new CreditCard();
  Payment pay = new Payment();
  IMessageService service;
   
  // return to pay scene
  @FXML
  public void backPay(MouseEvent event) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/se/project/gui/pay/pay.fxml"));
    try {
      Parent root = loader.load();
      PayController controller = loader.getController();
      controller.setOrder(order);
      controller.initData(order.getBike(), order);
      Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(new Scene(root));
      stage.show();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  // handle pay update balance
  @FXML
  public void pay() {
	  boolean isSuccess = false;
    // can check xem the da su dung cho tk nao chua
    if (name.getText() == "" || cardNum.getText() == "" || bank.getText() == ""
        || card.validateDate(date.getValue())) {
      System.out.print("Not input name");
    } else {

      // get input
      card = new CreditCard(bank.getText(),cardNum.getText(),date.getValue(),name.getText());
      pay.selectPayment(new PayByCard(card));   // can luu the vao bang transaction
      
      
  
      formatDateTime = LocalDateTime.now().format(DateUtils.format);

     

		if (order.getId() == 0) {
			// thread save transaction - function // to insert and update bike Table
			isSuccess = pay.payRent(order.getBike().getDeposit());
			if (isSuccess) {
				order.setTimeCreate(formatDateTime);
				threadRent();
			    
			} 
		} else if (order.getId() != 0) {
			isSuccess = pay.payReturn(order.getBike().getDeposit(), order.getTotal());
			if (isSuccess) {
				threadReturn();
			} 
		}
        if(isSuccess) {
        	System.out.print(card.getMoney());
        	 
    		// another thread simultaneous to send email to user
    		Thread t1 = new Thread(new Runnable() {

    			@Override
    			public void run() {
    				// need to implement overall
    				service = new EmailService();
    				service.sendEmail(order.getCust().getEmail(),order.getBike().getName(),order.getTimeCreate(),order.getBike().getDeposit());
    				
    			} 

    		});
    		t1.start(); // end thread 
            load();   
        }else System.out.print(isSuccess);
      //

      // save to transaction table

      // end thread 3
      //
     
    }

  }

  public void load() {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/se/project/gui/pay/payResult.fxml"));
    try {
      Parent root = loader.load();
      ResultController result = loader.getController();
      result.setOrder(order);
      result.setMsg(messageA.getText());
      if(order.getId()==0) {
      result.setMoney(DateUtils.formatter.format(order.getBike().getDeposit()));}
      else{
    	  result.setMoney(DateUtils.formatter.format(order.getTotal()));/// format
      }
      result.setTime(formatDateTime);
      Stage stage = (Stage) payBtn.getScene().getWindow();
      stage.setScene(new Scene(root));
      stage.show();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub
    date.setEditable(false);
  }

  public void setOrder(Order order2) {
    this.order = order2;

  }
  public void threadRent() {
	  Thread t2 = new Thread(new Runnable() {
          @Override
          public void run() {
            TransactionUtils.saveTransacToDB(order);
            int orderId = iOrder.getOrderId(order.getCust().getId());
            order.setId(orderId);
            iTransaction.saveTransaction(orderId, messageA.getText(), order.getBike().getDeposit());
          }

        });
        t2.start(); // end thread 2
        // tinh tien phan rent
  }
  public void threadReturn() {
	  Thread t2 = new Thread(new Runnable() {
          @Override
          public void run() {
            iTransaction.saveTransaction(order.getId(), messageA.getText(),
            		order.getTotal());

            /* TO DO        IMPLEMENT ORDER.GETTOTAL        */
            iTransaction.updateReturn(order.getId(), order.getBike().getId(), order.getTotal(),
                order.getTimeFinish());  // order.get total thay cho 0
            /* TO DO        IMPLEMENT pick store return o ORDER CONTROLLER       */
            iStore.updateStoreReturn(order.getBike().getId(),
                1);  // store id from return return ve store nao
           // order.setTimeFinish(formatDateTime);  // chi tinh khi hoan thanh thanh toan
          }
        });
        t2.start(); // end thread 2
        
  }
}
