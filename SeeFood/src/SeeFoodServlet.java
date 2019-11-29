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

public class SeeFoodServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Client client = ClientBuilder.newClient();

	// this token is for the Zomato API
	String token = "e91d6840e9bdbf46f2a077c83e2f62ea";

	double lat = 32.78306;
	double lon = -96.80667;

	// this function handles GET requests
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// sends user to homepage
		request.getRequestDispatcher("/WEB-INF/jsp/Homepage.jsp").forward(request, response);
	}

	// this function handles POST requests
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String cuisine = request.getParameter("cuisine");
		String distance = request.getParameter("distance");
		
		if(cuisine.equals("Cuisine")) {
			String[] cuisines = new String[]{"American", "Mexican","Japanese", "Indian", "Chinese", "Mediterranean"};
			Random rand = new Random();
			int index = rand.nextInt(cuisines.length);
			cuisine = cuisines[index];
		}
	
		int cuisineID = getCuisineID(cuisine);
		if(distance.equals("Distance")) {
			distance = "20 mi";
		}
		
		double meters= convertMilesToMeters(distance);
		HashMap<String, String> restaurantInfo = getRestaurant(cuisineID, meters);
		
		if(restaurantInfo == null) {
			request.getRequestDispatcher("/WEB-INF/jsp/NoRestaurant.jsp").forward(request, response);
			return;
		}
		request.setAttribute("name", restaurantInfo.get("name"));
		request.setAttribute("address", restaurantInfo.get("address"));
		request.setAttribute("photo", restaurantInfo.get("photoUrl"));
		request.setAttribute("url", restaurantInfo.get("url"));
		request.setAttribute("phoneNumbers", restaurantInfo.get("phoneNumbers"));
		request.setAttribute("cuisine", cuisine);
		request.getRequestDispatcher("/WEB-INF/jsp/ResultsPage.jsp").forward(request, response);

	}

	// finds the ID for the cuisine the user has chosen
	public int getCuisineID(String cuisine) {
		WebTarget target = client.target("https://developers.zomato.com/api/v2.1/cuisines");
		Response response = target.queryParam("lat", lat).queryParam("lon", lon).request(MediaType.APPLICATION_JSON)
				.header("user-key", token).get();
		String reply = response.readEntity(String.class);
		JsonReader reader = Json.createReader(new StringReader(reply));
		JsonObject cuisinesObject = reader.readObject();

		for (JsonValue c : cuisinesObject.getJsonArray("cuisines")) {
			JsonObject cui = c.asJsonObject().getJsonObject("cuisine");

			if (cui.getString("cuisine_name").equals(cuisine)) {
				return cui.getInt("cuisine_id");
			}
		}
		return 25;
	}

	// chooses a restaurant for the user
	public HashMap<String, String> getRestaurant(int cuisineID, double distance) {
		
		WebTarget target = client.target("https://developers.zomato.com/api/v2.1/search");
		Response response = target.queryParam("lat", lat).queryParam("lon", lon)
				.queryParam("cuisines", String.valueOf(cuisineID)).queryParam("radius", distance)
				.queryParam("sort", "rating").request(MediaType.APPLICATION_JSON).header("user-key", token).get();
		String reply = response.readEntity(String.class);
		JsonReader reader = Json.createReader(new StringReader(reply));
		JsonObject responseObject = reader.readObject();
		HashMap<String, String> res = new HashMap<String, String>();
		Random rand = new Random();
		int index = rand.nextInt(responseObject.getJsonArray("restaurants").size());
		int counter = 0;
		
		for (JsonValue r : responseObject.getJsonArray("restaurants")) {
			
			if(counter == index) {
				
				if(!r.asJsonObject().containsKey("restaurant")) {
					break;
				}
				JsonObject restaurant = r.asJsonObject().getJsonObject("restaurant");
				String name = "Not Found";
				String address = "Not Found";
				String photoUrl = "Not Found";
				String url = "Not Found";
				String phoneNumbers = "Not Found";
				
				if(restaurant.containsKey("name")) {
					name = restaurant.getString("name");
				}
			
				if(restaurant.containsKey("location")) {
					
					if(restaurant.getJsonObject("location").containsKey("address")) {
						address = restaurant.getJsonObject("location").getString("address");
					}
				}
				
				if(restaurant.containsKey("featured_image")) {
					photoUrl = restaurant.getString("featured_image");
				}
				
				if(restaurant.containsKey("url")) {
					url = restaurant.getString("url");
				}
				
				if(restaurant.containsKey("phone_numbers")) {
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

	// Convert the distance selection into meters (miles to meters)
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
