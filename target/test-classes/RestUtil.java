package test.resources;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestUtil {

	/**
	 * Generates the Request Specification for the network request
	 * 
	 * @param details
	 *            - Request Details for the request specification to be made
	 *            Includes base URI, parameters/extensions, and request body
	 * @return RequestSpecification to be used in sending the network request.
	 */
	private RequestSpecification generateRequest(RequestDetails details) {
		RestAssured.baseURI = details.getBaseUrl();
		
		return RestAssured.given();
	}

	/**
	 * Method to submit a request against an REST end point.
	 * 
	 * @param details
	 *            Request Details for the request specification to be made Includes
	 *            base URI, parameters/extensions, and request body
	 * @param type
	 *            - Method - Type of network request(e.g. GET, POST, PUT, etc...)
	 * @return Response - The response to the request being made against the end
	 *         point.
	 */
	public Response getResponse(RequestDetails details, Method type) {
		RequestSpecification httpRequest = generateRequest(details);
		System.out.println(details.getBaseUrl() + details.getRequestArguments());
		return httpRequest.request(type, details.getRequestArguments());
	}
}
