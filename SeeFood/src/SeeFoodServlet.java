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
		// this token is for the Zomato API
		String token = "e91d6840e9bdbf46f2a077c83e2f62ea";

		request.getRequestDispatcher("/WEB-INF/jsp/ResultsPage.jsp").forward(request, response);
		
		
	}
	
	public int getCuisineID(String cuisine) {
		return 73;
	}

}
