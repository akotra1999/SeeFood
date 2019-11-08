import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class SeeFoodServlet extends HttpServlet {
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
		String price = request.getParameter("price");
		String distance = request.getParameter("distance");

		request.getRequestDispatcher("/WEB-INF/jsp/ResultsPage.jsp").forward(request, response);

	}

	public int getCuisineID(String cuisine) {
		WebTarget target = client.target("https://developers.zomato.com/api/v2.1/cuisines");
		Response response = target.queryParam("lat", lat).queryParam("lon", lon)
				.request(MediaType.APPLICATION_JSON).header("user-key", token).get();
		String reply = response.readEntity(String.class);
		JsonReader reader = Json.createReader(new StringReader(reply));
		JsonObject cuisinesObject = reader.readObject();
		
		for(JsonValue c : cuisinesObject.getJsonArray("cuisines")) {
			JsonObject cui = c.asJsonObject().getJsonObject("cuisine");
			
			if(cui.getString("cuisine_name").equals(cuisine)) {
				return cui.getInt("cuisine_id");
			}
		}
		return -1;
	}
	
	// Convert the distance selection into meters (miles to meters)
	public float convertMilesToMeters(String miles) {
		float x;
		if (miles == "1") {
			x= (float)1609.34;
			return x;	
		}
		else if (miles == "5") {
			x =  (float)8046.72;
			return x;
		}
		else if (miles == "10") {
			x = (float) 16093.4;
			return x;
		}
		else if (miles == "20") {
			
		x = (float) 32186.9;
		return x;
		}
		else 
		{
			x = 0;
			return x;
		}
	}

}
