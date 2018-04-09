package test.resources;

public class RequestDetails {
	private String baseUrl;
	private String requestArgs;
	private Object requestBody;

	/**
	 * Provides a string for the base URL end point(e.g. http://www.amazon.com)
	 *  based on the system env variable provided
	 * 
	 * @return - String for the base URL end point 
	 */
	private String getBaseEndpoint() {
		String environment = System.getProperty("env");
		if (environment != null) {
			switch (environment) {
			case "dev":
				return "";
			case "test":
				return "";
			case "stage":
				return "";
			default:
				return "https://api.themoviedb.org";
			}
		} else {
			return "https://api.themoviedb.org";
		}
	}

	public RequestDetails() {
	}

	public RequestDetails(String argument) {
		baseUrl = getBaseEndpoint();
		requestArgs = argument;
	}

	public RequestDetails(String url, String argument) {
		baseUrl = url;
		requestArgs = argument;
	}

	public RequestDetails(String argument, Object body) {
		baseUrl = getBaseEndpoint();
		requestArgs = argument;
		requestBody = body;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String url) {
		if (url != null) {
			baseUrl = url;
		} else {
			baseUrl = getBaseEndpoint();
		}
	}

	public String getRequestArguments() {
		return requestArgs;
	}

	public void setRequestArguments(String arg) {
		requestArgs = arg;
	}

	public Object getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(Object body) {
		requestBody = body;
	}

	public void updateRequestInfo(String arg, Object body) {
		requestArgs = arg;
		requestBody = body;
	}

}
