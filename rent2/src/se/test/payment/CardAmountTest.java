package se.test.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import se.project.model.payment.CreditCard;

import se.project.model.payment.PayByCard;
import se.project.model.payment.PayService;


public class CardAmountTest {
	private CreditCard card;
	private PayService payService;
  //equivalance partitioning
	@BeforeEach
	void setUp() {
		card = new CreditCard();
		card.setMoney(100000);
		payService = new PayService(new PayByCard(card));
	}

	// Equivalence Partitioning
	@Test
	@DisplayName("Test function consume amout")
	void testCardAmount() {
		payService.pay(90000);
		assertEquals(10000, card.getMoney(), "Not equal amount");
	}

	@Test
	@DisplayName("Check enough money return 1")
	void testReturnAmount1() {
		assertEquals(true, payService.pay(200000, 100000), "Not enough money");
	}

	@Test
	@DisplayName("Check enough money return 2")
	void testReturnAmount2() {
		assertEquals(false, payService.pay(1000, 150000), "Not enough money");
	}
    
	// nen chia test ra
	@Test
	@DisplayName("Check enough money rent 1")
	void testRentAmount1() {
		assertEquals(true, payService.pay(90000), "Not enough money");
	}
	
	@Test
	@DisplayName("Check not enough money rent 2")
	void testRentAmount2() {
		assertEquals(false, payService.pay(110000), "Not enough money");
	}
	/*
	// test using parameter
	@ParameterizedTest
	@ValueSource(ints = { 90000, 110000 })
	@DisplayName("Check enough money")
	void testEnoughAmount(int amount) {
		assertEquals(true, pay.payRent(amount), "Not enough money");
	}*/

}
