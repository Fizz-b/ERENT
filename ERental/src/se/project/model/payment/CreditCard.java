package se.project.model.payment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class CreditCard {

  private String bank;

  private String cardNum;

  private LocalDate date;

  private String name;

  private float money = 1000;

  public float getMoney() {
    return money;
  }

  public void setMoney(float money) {
    this.money = money;
  }

  public String getBank() {
    return bank;
  }

  public void setBank(String bank) {
    this.bank = bank;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  /*
       @date : date user enter
       
     return check valid date
  */
  public boolean validateEmpty(String bank, String cardNum, String name) {
    if (bank == "" || cardNum == "" || name == "") {
      return true;
    }
    return false;
  }

  // check expire
  public boolean validateDate(LocalDate date) {

    LocalDate today = LocalDate.now();
    // check right format

    // check before today expired card
    if (date.isBefore(today)) {
      return true;
    }

    return false;
  }


  /*
       @format : date format (exp: yyyy-MM-dd)
     @value  :  date string
     return check right format
  */
  public boolean isValidFormat(String format, String value) {
    Date date = null;
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      date = sdf.parse(value);
      if (!value.equals(sdf.format(date))) {
        date = null;
      }
    } catch (ParseException ex) {
      ex.printStackTrace();
    }
    return date != null;
  }


}
