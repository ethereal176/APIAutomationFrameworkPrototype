package test.resources;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.testng.Assert;
import org.testng.log4testng.Logger;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class MovieDbConnectionHelper {
	private RestUtil rester = new RestUtil();
	private final Logger LOGGER = org.testng.log4testng.Logger.getLogger(MovieDbConnectionHelper.class);

	/**
	 * Creates and validates a token with the authentication end point.
	 * 
	 * @param apiKey
	 *            - APIKEY for the movie db site
	 * @return token as a String
	 */
	public String getToken(String apiKey) {
		RequestDetails details = new RequestDetails("/3/authentication/token/new?api_key=" + apiKey);
		Response response = rester.getResponse(details, Method.GET);

		JsonPath json = response.getBody().jsonPath();
		String actualDate = json.getString("expires_at");
		String token = json.getString("request_token");
		String successmsg = json.getString("success");
		LOGGER.info("Response Body: " + response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 200, "Request Failed: Correct Status Code received");
		Assert.assertEquals(successmsg, "true", "Request Failed: Success Message received");
		validateExpirationDate(actualDate);

		return token;
	}

	/**
	 * Validates that the expiration date for the token is 30 minutes after the
	 * current time within acceptable variations to account for running time.
	 * 
	 * @param dateString
	 *            Date as a string received as part of the JSON object
	 */
	private void validateExpirationDate(String dateString) {
		// 2018-04-09 00:01:04 UTC
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

		// Current time +30 minutes in MS
		long expectedDate = System.currentTimeMillis() + 1800000;

		long actualDate = 0;
		try {
			actualDate = formatter.parse(dateString).getTime();
		} catch (ParseException e) {
			LOGGER.error(e.getMessage() + "/n" + e.getStackTrace());
		}
		long difference = expectedDate - actualDate;
		LOGGER.info("Difference between Expiration Date and Expected Expiration was " + difference);
		Assert.assertTrue(30 > difference && difference < -30, "Expected date was not within acceptable variation");
	}
}
