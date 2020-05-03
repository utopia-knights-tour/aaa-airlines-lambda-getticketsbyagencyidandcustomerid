
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

import entity.TicketQuery;
import proxy.ApiGatewayProxyResponse;
import proxy.ApiGatewayRequest;
import service.AgentService;

public class GetTicketsByAgencyIdAndCustomerId implements RequestHandler<ApiGatewayRequest, ApiGatewayProxyResponse> {

	private AgentService agentService = new AgentService();

	public ApiGatewayProxyResponse handleRequest(ApiGatewayRequest request, Context context) {
		LambdaLogger logger = context.getLogger();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Access-Control-Allow-Origin", "*");
		try {
			if (request.getPathParameters() == null || request.getPathParameters().get("agencyId") == null
					|| request.getPathParameters().get("customerId") == null) {
				return new ApiGatewayProxyResponse(400, headers, null);
			}
			if (request.getQueryStringParameters() == null || request.getQueryStringParameters().get("page") == null
					|| request.getQueryStringParameters().get("pagesize") == null) {
				return new ApiGatewayProxyResponse(400, headers, null);
			}
			TicketQuery ticketQuery = agentService.getTicketsByAgencyIdAndCustomerId(
					Long.parseLong(request.getPathParameters().get("agencyId")),
					Long.parseLong(request.getPathParameters().get("customerId")),
					Long.parseLong(request.getQueryStringParameters().get("page")),
					Long.parseLong(request.getQueryStringParameters().get("pagesize")));
			return new ApiGatewayProxyResponse(200, headers, new Gson().toJson(ticketQuery));
		} catch (NumberFormatException | SQLException e) {
			logger.log(e.getMessage());
			return new ApiGatewayProxyResponse(400, headers, new Gson().toJson(e));
		} catch (ClassNotFoundException e) {
			logger.log(e.getMessage());
			return new ApiGatewayProxyResponse(500, headers, null);
		}
	}
}