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
import se.project.interfaces.IOrder;
import se.project.interfaces.IStore;
import se.project.interfaces.ITransaction;
import se.project.interfaces.dao.OrderDao;
import se.project.interfaces.dao.StoreDao;
import se.project.interfaces.dao.TransactionDAO;
import se.project.model.order.Order;
import se.project.model.payment.CreditCard;
import se.project.model.payment.PayByCard;
import se.project.model.payment.Payment;
import se.project.util.DateUtils;
import se.project.util.TransactionUtils;

public class bankGateController<PayStrategy> implements Initializable {

  String formatDateTime;
  CreditCard card = new CreditCard();
  Payment pay = new Payment();
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
    // can check xem the da su dung cho tk nao chua
    if (name.getText() == "" || cardNum.getText() == "" || bank.getText() == ""
        || card.validateDate(date.getValue())) {
      System.out.print("Not input name");
    } else {

      // get input
      card.setName(name.getText());
      card.setDate(date.getValue());
      card.setBank(bank.getText());
      card.setMoney(1000000);
      card.setCardNum(cardNum.getText());
      LocalDateTime now = LocalDateTime.now();

      formatDateTime = now.format(Order.format);

      //
             /*
			// another thread simultaneous to send email to user
			Thread t1 = new Thread(new Runnable() {

				@Override
				public void run() {
					SendEmail.sendEmailToUser(order.getCust().getEmail());
				}

			});
			t1.start(); // end thread 1
             */

      if (order.getId() == 0) {
        // thread save transaction - function // to insert and update bike Table
        order.setTimeCreate(formatDateTime);
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
        pay.selectPayment(new PayByCard(card));
        pay.payRent(order.getBike().getDeposit());
      }

      if (order.getId() != 0) {
        Thread t2 = new Thread(new Runnable() {
          @Override
          public void run() {
            iTransaction.saveTransaction(order.getId(), messageA.getText(),
                order.getBike().getDeposit());

            /* TO DO        IMPLEMENT ORDER.GETTOTAL        */
            iTransaction.updateReturn(order.getId(), order.getBike().getId(), order.getTotal(),
                order.getTimeFinish());  // order.get total thay cho 0
            /* TO DO        IMPLEMENT pick store return o ORDER CONTROLLER       */
            iStore.updateStoreReturn(order.getBike().getId(),
                1);  // store id from return return ve store nao
            order.setTimeFinish(formatDateTime);
          }
        });
        t2.start(); // end thread 2
        
        // tinh tien phan return TODO
        pay.selectPayment(new PayByCard(card));
        //pay.payRent(order.getTotal()); float
      }

      //System.out.print(card.getMoney());

      // save to transaction table

      // end thread 3

      // load pay result
      load();
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
      result.setMoney(DateUtils.formatter.format(order.getBike().getDeposit())); /// format
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
}
