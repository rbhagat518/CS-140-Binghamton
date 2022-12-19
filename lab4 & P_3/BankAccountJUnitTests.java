import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankAccountJUnitTests {
	private static BankAccount b;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		b = new BankAccount("some last name", "some first name");
	}

	// add 100 vertices twice and verify that the number of vertices
	// is correct each time one is added
	@Test
	public void test1() {
		assertEquals(true, b.withdrawFromSavings(10.0));
	}

	// add 100 vertices twice and verify there are the correct number of vertices
	// each time one is added
	@Test
	public void test2() {
		b.depositToSavings(20.0);

	}

	@Test
	public void test3() {
		b.depositToSavings(20.0);
		b.transferFromSavingsToChecking(5.0);
		assertFalse(b.transferFromSavingsToChecking(5.0));

	}

	// test a 0 paramater for being an undirected graph
	@Test
	public void test4() {
		b.depositToSavings(20.0);
		b.transferFromSavingsToChecking(5.0);
		b.transferFromCheckingToSavings(5.0);
		assertEquals(15.0, b.getSavingsBalance(), 0.01);

	}

	// test a 1 paramater for being a directed graph
	@Test
	public void test5() 
	{
		b.depositToSavings(20.0);
		b.transferFromSavingsToChecking(5.0);	
		b.transferFromCheckingToSavings(5.0);
		assertEquals(true, b.withdrawFromSavings(10.0))	
	}

}
