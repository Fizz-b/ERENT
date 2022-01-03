package se.test.usecase.pay;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import se.project.database.Context;
import se.project.database.MySQLConnection;
import se.project.database.api.transaction.IValidTransact;
import se.project.database.api.transaction.ValidTransact;
import se.project.model.payment.CreditCard;
import se.project.model.payment.PayByCard;
import se.project.model.payment.PayService;
import se.project.util.DateUtils;

public class PaymentTest {
	private CreditCard card;
	private PayService payService;
	private IValidTransact iCheck = new ValidTransact();
	@BeforeEach
	void setUp() {
		 Context.selectDataBase(new MySQLConnection());
		card = new CreditCard();
		card.setMoney(100000);
		payService = new PayService(new PayByCard(card));
	}
	
	@Test                                               
    @DisplayName("Test case 1:Valid card")   
    void testValidInput() {
        assertEquals(false,DateUtils.validateDate(LocalDate.of(2024, Month.JANUARY, 8)),"ValidDate");
        assertEquals(false,iCheck.checkCardUsed("1234567891111111", "2") , "Card have not been used"); 
        assertEquals(true, payService.pay(90000), "Enough money");
    }
	
	@Test                                               
    @DisplayName("Test case 2:Card in use")   
    void testCard() {
		// cardNum + ordeId
		assertEquals(true,iCheck.checkCardUsed("1234567891111112", "2") , "Card have been used"); 
    }
	
	@Test                                               
    @DisplayName("Test case 3:Expired card")   
    void testInvalidDate() {
        assertEquals(true,DateUtils.validateDate(LocalDate.of(2020, Month.JANUARY, 8)),     
                "InvalidDate");  
    }
	
	@Test
	@DisplayName("Test case 4:Not enough money")
	void testEnoughMoney() {
		assertEquals(false, payService.pay(190000), "Not enough money");
	}
	
}
