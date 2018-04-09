package test.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import test.resources.ApiBaseTestFrame;
import test.resources.MovieDbSearchHelper;
import test.resources.RequestDetails;
import test.resources.RestUtil;

public class MovieDbSearchTest extends ApiBaseTestFrame {
	RestUtil rester = new RestUtil();
	MovieDbSearchHelper helper = new MovieDbSearchHelper();

	// TODO: Create utility to pull down updated daily file as a Data Provider for
	// testing Search dynamically

	@Test
	public void searchForMovieTest() {
		Response response = helper.getSearchResponse("movie", "Deadpool", apiKey);
		String body = response.getBody().asString();
		Assert.assertEquals(response.getStatusCode(), 200, "Request Failed: Correct Status Code received");
		Assert.assertTrue(body.contains("\"Deadpool\""), "Search Results do not contain 'Deadpool'");
	}

	@Test
	public void searchForMovieNoTerm() {
		Response response = helper.getSearchResponse("movie", "", apiKey);
		Assert.assertEquals(response.getStatusCode(), 422, "Request Failed: Correct Status Code received");
	}

	@Test
	public void searchForMovieBadTerm() {
		Response response = helper.getSearchResponse("movie", "@^#^@!*@@!*!(", apiKey);
		Assert.assertEquals(response.getStatusCode(), 200, "Request Failed: Correct Status Code received");
		Assert.assertEquals(Integer.parseInt(response.getBody().jsonPath().getString("total_results")), 0,
				"Request failed: Received a result set of");
	}

	// TODO: Create test to validate SQL injection attack errors out
	// /3/search/movie
	// public void searchForTvSqlAttack(){}

	@Test
	public void searchForTvTest() {
		Response response = helper.getSearchResponse("tv", "Scorpion", apiKey);
		String body = response.getBody().asString();
		Assert.assertEquals(response.getStatusCode(), 200, "Request Failed: Correct Status Code received");
		Assert.assertTrue(body.contains("Scorpion"), "Search Results do not contain 'Scorpion'");
	}

	@Test
	public void searchForTvNoTerm() {
		Response response = helper.getSearchResponse("tv", "", apiKey);
		Assert.assertEquals(response.getStatusCode(), 422, "Request Failed: Correct Status Code received");
	}

	@Test
	public void searchForTvBadTerm() {
		Response response = helper.getSearchResponse("tv", "@^#^@!*@@!*!(", apiKey);
		Assert.assertEquals(response.getStatusCode(), 200, "Request Failed: Correct Status Code received");
		Assert.assertEquals(Integer.parseInt(response.getBody().jsonPath().getString("total_results")), 0,
				"Request failed: Received a result set of");
	}

	// TODO: Create test to validate SQL injection attack errors out
	// /3/search/tv
	// public void searchForTvSqlAttack(){}

	// TODO: Create test to validate searching for Film Company
	// endpoint: /search/company
	// public void searchForCompanyTest() {}

	// TODO: Create test to validate searching for a collection
	// endpoint: /search/collection
	// public void searchForCollectionTest() {}

	// TODO: Create a test to validate searching for Actors/Actresses
	// endpoint: /search/person
	// public void searchForPersonTest() {}
}
