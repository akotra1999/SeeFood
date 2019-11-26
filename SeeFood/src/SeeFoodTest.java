import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SeeFoodTest {
	SeeFoodServlet server;

	// this function is run before every test case
	@BeforeEach
	public void beforeEachTestMethod() {
		server = new SeeFoodServlet();
	}

	@Test
	void testCuisineID() {
		assertEquals(73, server.getCuisineID("Mexican"));
	}

	@Test
	void testCuisineID1() {
		assertEquals(60, server.getCuisineID("Japanese"));
	}

	@Test
	void testMeters() {
		assertEquals(8046.72, server.convertMilesToMeters("5 mi"), 0.05);
	}

	@Test
	void testMeters1() {
		assertEquals(16093.4, server.convertMilesToMeters("10 mi"), 0.05);
	}

	@Test
	void testRestaurant() {
		assertEquals(server.getRestaurant(60, 32186.9).get(0), "Abacus");
		assertEquals(server.getRestaurant(60, 32186.9).get(1), "4511 McKinney Avenue, Knox Henderson 75205");
	}

	@Test
	void testRestaurant2() {
		assertEquals(server.getRestaurant(73, 32186.9).get(0), "Javier's Gourmet Mexicano");
		assertEquals(server.getRestaurant(73, 32186.9).get(1), "4912 Cole Avenue 75205");
	}

}
