package user;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

public class UserLoader implements Handler<RoutingContext> {

	@Override
	public void handle(RoutingContext ctxt) {
		// TODO Auto-generated method stub
		UserDetails ud = new UserDetails();
		
		String id = ctxt.request().getParam("id");
		
		ud.setId(Integer.parseInt(id));
		
		ud.setName("Paramananda");

		HttpServerResponse response=ctxt.response();

		response.putHeader("content-type", "application/json");
		
		ObjectMapper mapper = new ObjectMapper();

		try {
			response.end(mapper.writeValueAsString(ud));
			return;
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.end("{\"Error\" : \""+e.toString()+"\" }");
			return;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.end("{\"Error\" : \""+e.toString()+"\" }");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.end("{\"Error\" : \""+e.toString()+"\" }");
			return;
		}
	}

}
