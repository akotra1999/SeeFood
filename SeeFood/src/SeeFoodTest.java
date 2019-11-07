import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SeeFoodTest {
	SeeFoodServlet server;
	
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
	

}
