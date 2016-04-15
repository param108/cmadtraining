package user;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

public class UserUpdate implements Handler<RoutingContext> {

	@Override
	public void handle(RoutingContext rc) {
		// TODO Auto-generated method stub
		String jsonstr = rc.getBodyAsString();
		
		ObjectMapper mapper = new ObjectMapper();
		HttpServerResponse response=rc.response();
		response.putHeader("content-type", "application/json");

		try {
			UserDetails ud = mapper.readValue(jsonstr, UserDetails.class);
			response.end(mapper.writeValueAsString(ud));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.end("{\"Error\" : \""+e.toString()+"\" }");
		}
		
		
	}

}
