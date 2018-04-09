package test.java;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.Method;
import io.restassured.response.Response;
import test.resources.ApiBaseTestFrame;
import test.resources.MovieDbConnectionHelper;
import test.resources.RequestDetails;
import test.resources.RestUtil;

public class MovieDbConnectionTest extends ApiBaseTestFrame {
	RestUtil rester = new RestUtil();

	@Test
	public void checkConnectionTest() {
		RequestDetails details = new RequestDetails("/3/movie/76341?api_key=" + apiKey);
		Response response = rester.getResponse(details, Method.GET);

		String title = response.getBody().jsonPath().getString("title");
		Assert.assertEquals(response.getStatusCode(), 200, "Request Failed: Correct Status Code received");
		Assert.assertEquals(title, "Mad Max: Fury Road", "Request Failed: Correct title received");
	}

	@Test
	public void getNewTokenTest() {
		new MovieDbConnectionHelper().getToken(apiKey);
	}

	// TODO: Create test to validate SQL injection attack errors out
	// /3/authentication/token/new
	// public void getNewTokenSqlAttack(){}

	// TODO: Create test to validate the creation of a session with the Movie DB
	// endpoint: /authentication/session/new
	// public void createNewSessionTest() {}

	// TODO: Create test to validate the creation of a session using login
	// credentials
	// endpoint: /authentication/token/validate_with_login
	// public void createNewSessionLoginTest() {}

	// TODO: Create a test to validate the creation of a guest session
	// endpoint: /authentication/guest_session/new
	// public void createNewGuestSessionTest() {}
}
