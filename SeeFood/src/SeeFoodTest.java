import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * SeeFoodTest has all of our test cases
 *
 */
class SeeFoodTest {
	SeeFoodServlet server;

	// this method is called before every test case
	@BeforeEach
	void beforeEachTestMethod() {
		server = new SeeFoodServlet();
	}

	// testing getCuisineID
	@Test
	void testCuisineID() {
		assertEquals(73, server.getCuisineID("Mexican"));
	}

	@Test
	void testCuisineID1() {
		assertEquals(60, server.getCuisineID("Japanese"));
	}

	// testing convertMilestoMeters
	@Test
	void testMeters() {
		assertEquals(8046.72, server.convertMilesToMeters("5 mi"), 0.05);
	}

	@Test
	void testMeters1() {
		assertEquals(16093.4, server.convertMilesToMeters("10 mi"), 0.05);
	}

	// testing getRestaurant
	// we cannot check for a particular restaurant as the getRestaurant involves
	// randomization
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
