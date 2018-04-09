package test.resources;

import org.testng.Assert;

import io.restassured.http.Method;
import io.restassured.response.Response;

public class MovieDbSearchHelper {
	private RestUtil rester = new RestUtil();
	
	/**
	 * Submits a search request against the available end points 
	 * 
	 * @param type - Type of search(e.g. movie, tv, etc...)
	 * @param term - Search Term
	 * @param apiKey - API Key to authenticate access
	 * @return - Response object
	 */
	public Response getSearchResponse(String type, String term, String apiKey) {
		RequestDetails details = new RequestDetails(
				"/3/search/"+type+"?api_key=" + apiKey + "&query="+term+"&page=1");
		Response response = rester.getResponse(details, Method.GET);
		
		return response;
	}
}
