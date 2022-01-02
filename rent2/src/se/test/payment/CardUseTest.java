package se.test.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import se.project.database.Context;
import se.project.database.MySQLConnection;
import se.project.database.api.transaction.IValidTransact;
import se.project.database.api.transaction.ValidTransact;


public class CardUseTest {
	private IValidTransact iCheck = new ValidTransact();
	@BeforeEach
	void setUp() {
		 Context.selectDataBase(new MySQLConnection());
	}
	// test using parameter
	@ParameterizedTest
	@ValueSource(strings = { "1234567891111111", "1234567891111112" })
	@DisplayName("Check card used")
	void testCard(String card) {
		assertEquals(true,iCheck.checkSameCard(card, "277") , "Card have been used");
	}
}
