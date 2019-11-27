import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
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
	void testRestaurantName() {
		HashMap<String, String> restaurantInfo = server.getRestaurant(1, 1609.34);
		assertNotNull(restaurantInfo.get("name"));
	}

	@Test
	void testRestaurantName2() {
		HashMap<String, String> restaurantInfo = server.getRestaurant(25, 1609.34);
		assertNotNull(restaurantInfo.get("name"));
	}

	@Test
	void testRestaurantName3() {
		HashMap<String, String> restaurantInfo = server.getRestaurant(148, 1609.34);
		assertNotNull(restaurantInfo.get("name"));
	}

	@Test
	void testRestaurantAddress() {
		HashMap<String, String> restaurantInfo = server.getRestaurant(60, 1609.34);
		assertNotNull(restaurantInfo.get("address"));
	}

	@Test
	void testRestaurantAddress2() {
		HashMap<String, String> restaurantInfo = server.getRestaurant(70, 1609.34);
		assertNotNull(restaurantInfo.get("address"));
	}

	@Test
	void testRestaurantAddress3() {
		HashMap<String, String> restaurantInfo = server.getRestaurant(73, 1609.34);
		assertNotNull(restaurantInfo.get("address"));
	}

	@Test
	void testRestaurantUrl() {
		HashMap<String, String> restaurantInfo = server.getRestaurant(1, 8046.72);
		assertNotNull(restaurantInfo.get("url"));
	}

	@Test
	void testRestaurantUrl2() {
		HashMap<String, String> restaurantInfo = server.getRestaurant(25, 8046.72);
		assertNotNull(restaurantInfo.get("url"));
	}

	@Test
	void testRestaurantUrl3() {
		HashMap<String, String> restaurantInfo = server.getRestaurant(148, 8046.72);
		assertNotNull(restaurantInfo.get("url"));
	}

	@Test
	void testRestaurantPhoneNumbers() {
		HashMap<String, String> restaurantInfo = server.getRestaurant(60, 8046.72);
		assertNotNull(restaurantInfo.get("phoneNumbers"));
	}

	@Test
	void testRestaurantPhoneNumbers2() {
		HashMap<String, String> restaurantInfo = server.getRestaurant(70, 8046.72);
		assertNotNull(restaurantInfo.get("phoneNumbers"));
	}

	@Test
	void testRestaurantPhoneNumbers3() {
		HashMap<String, String> restaurantInfo = server.getRestaurant(73, 8046.72);
		assertNotNull(restaurantInfo.get("phoneNumbers"));
	}

}
