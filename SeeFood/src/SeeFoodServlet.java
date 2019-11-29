import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Random;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Servlet handles GET and POST requests. User chooses a distance and cuisine,
 * and then gets a restaurant recommendation.
 *
 */
public class SeeFoodServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// this client is used for all API calls
	Client client = ClientBuilder.newClient();

	// this token is for the Zomato API
	String token = "e91d6840e9bdbf46f2a077c83e2f62ea";

	// Dallas location
	double lat = 32.78306;
	double lon = -96.80667;

	/**
	 * This method handles GET requests from the user.
	 * 
	 * @param request  Servlet receives request
	 * @param response Servlet sends response.
	 * @return Nothing.
	 * @exception IOException      On input error.
	 * @exception ServletException On servlet error.
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// sends user to homepage
		request.getRequestDispatcher("/WEB-INF/jsp/Homepage.jsp").forward(request, response);
	}

	/**
	 * This method handles POST requests from the user. It suggests a restaurnt to
	 * the user.
	 * 
	 * @param request  Servlet receives request.
	 * @param response Servlet sends response.
	 * @return Nothing.
	 * @exception IOException      On input error.
	 * @exception ServletException On servlet error.
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// gets users selections
		String cuisine = request.getParameter("cuisine");
		String distance = request.getParameter("distance");

		// if user has not selected a cuisine, chooses one randomly
		if (cuisine.equals("Cuisine")) {
			String[] cuisines = new String[] { "American", "Mexican", "Japanese", "Indian", "Chinese",
					"Mediterranean" };
			Random rand = new Random();
			int index = rand.nextInt(cuisines.length);
			cuisine = cuisines[index];
		}

		// gets cuisine ID based on selected cuisine
		int cuisineID = getCuisineID(cuisine);

		// if user has not selected distance, make it 20 miles
		if (distance.equals("Distance")) {
			distance = "20 mi";
		}

		// convert miles to meters
		double meters = convertMilesToMeters(distance);

		// get a restaurant recommendation
		HashMap<String, String> restaurantInfo = getRestaurant(cuisineID, meters);

		// if no restaurant can be found
		if (restaurantInfo == null) {
			request.getRequestDispatcher("/WEB-INF/jsp/NoRestaurant.jsp").forward(request, response);
			return;
		}

		// the following info will be displayed
		request.setAttribute("name", restaurantInfo.get("name"));
		request.setAttribute("address", restaurantInfo.get("address"));
		request.setAttribute("photo", restaurantInfo.get("photoUrl"));
		request.setAttribute("url", restaurantInfo.get("url"));
		request.setAttribute("phoneNumbers", restaurantInfo.get("phoneNumbers"));
		request.setAttribute("cuisine", cuisine);
		request.getRequestDispatcher("/WEB-INF/jsp/ResultsPage.jsp").forward(request, response);

	}

	/**
	 * This method handles finds the ID for the cuisine the user has selected to
	 * make other API calls. It suggests a restaurant to the user.
	 * 
	 * @param cuisine User choose cuisine.
	 * @return Cuisine ID.
	 */
	public int getCuisineID(String cuisine) {

		// endpoint for retrieving cuisine IDs
		WebTarget target = client.target("https://developers.zomato.com/api/v2.1/cuisines");
		Response response = target.queryParam("lat", lat).queryParam("lon", lon).request(MediaType.APPLICATION_JSON)
				.header("user-key", token).get();
		String reply = response.readEntity(String.class);
		JsonReader reader = Json.createReader(new StringReader(reply));
		JsonObject cuisinesObject = reader.readObject();

		// finding the cusiine ID that matches cuisine
		for (JsonValue c : cuisinesObject.getJsonArray("cuisines")) {
			JsonObject cui = c.asJsonObject().getJsonObject("cuisine");

			if (cui.getString("cuisine_name").equals(cuisine)) {
				return cui.getInt("cuisine_id");
			}
		}
		// default value
		return 25;
	}

	/**
	 * This method chooses a restaurant for the user.
	 * 
	 * @param cuisineID ID for the cuisine the user wants to eat.
	 * @param distance  Distance in meters the user wants to drive.
	 * @return Info regarding chosen restaurant.
	 */
	public HashMap<String, String> getRestaurant(int cuisineID, double distance) {

		// endpoint for seraching for restaurants
		WebTarget target = client.target("https://developers.zomato.com/api/v2.1/search");

		// the distance, location, cuisine and API key are provided in the API call
		Response response = target.queryParam("lat", lat).queryParam("lon", lon)
				.queryParam("cuisines", String.valueOf(cuisineID)).queryParam("radius", distance)
				.queryParam("sort", "rating").request(MediaType.APPLICATION_JSON).header("user-key", token).get();
		String reply = response.readEntity(String.class);
		JsonReader reader = Json.createReader(new StringReader(reply));
		JsonObject responseObject = reader.readObject();
		HashMap<String, String> res = new HashMap<String, String>();

		// randomly picking a restaurant out of the ones provided
		Random rand = new Random();
		int index = rand.nextInt(responseObject.getJsonArray("restaurants").size());
		int counter = 0;

		// parsing the JSON to get restaurant info
		for (JsonValue r : responseObject.getJsonArray("restaurants")) {

			if (counter == index) {

				if (!r.asJsonObject().containsKey("restaurant")) {
					break;
				}
				JsonObject restaurant = r.asJsonObject().getJsonObject("restaurant");
				String name = "Not Found";
				String address = "Not Found";
				String photoUrl = "Not Found";
				String url = "Not Found";
				String phoneNumbers = "Not Found";

				if (restaurant.containsKey("name")) {
					name = restaurant.getString("name");
				}

				if (restaurant.containsKey("location")) {

					if (restaurant.getJsonObject("location").containsKey("address")) {
						address = restaurant.getJsonObject("location").getString("address");
					}
				}

				if (restaurant.containsKey("featured_image")) {
					photoUrl = restaurant.getString("featured_image");
				}

				if (restaurant.containsKey("url")) {
					url = restaurant.getString("url");
				}

				if (restaurant.containsKey("phone_numbers")) {
					phoneNumbers = restaurant.getString("phone_numbers");
				}
				res.put("name", name);
				res.put("address", address);
				res.put("photoUrl", photoUrl);
				res.put("url", url);
				res.put("phoneNumbers", phoneNumbers);
				return res;
			}
			counter++;
		}
		return null;

	}

	/**
	 * This method converts the distance selection into meters (miles to meters).
	 * 
	 * @param miles Miles the user wants to drive.
	 * @return Meters the user wants to drive.
	 */
	public float convertMilesToMeters(String miles) {
		float x;
		if (miles.equals("1 mi")) {
			x = (float) 1609.34;
			return x;
		} else if (miles.equals("5 mi")) {
			x = (float) 8046.72;
			return x;
		} else if (miles.equals("10 mi")) {
			x = (float) 16093.4;
			return x;
		} else if (miles.equals("20 mi")) {
			x = (float) 32186.9;
			return x;
		} else {
			x = 0;
			return x;
		}
	}

}
