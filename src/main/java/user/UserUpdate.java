package user;
import java.io.IOException;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

public class UserUpdate implements Handler<RoutingContext> {

	@Override
	public void handle(RoutingContext rc) {
		// TODO Auto-generated method stub
		String jsonstr = rc.getBodyAsString();
		
		Datastore dataStore = ServicesFactory.getMongoDB();
		ObjectMapper mapper = new ObjectMapper();
		HttpServerResponse response=rc.response();
		response.putHeader("content-type", "application/json");
		
		UserDTO ud;
		try {
			ud = mapper.readValue(jsonstr, UserDTO.class);
			User u = ud.toModel();
			Key<User> k = dataStore.save(u);
			
			u.setId(new ObjectId(k.getId().toString()));
            UserDTO dto = new UserDTO().fillFromModel(u);
            ObjectMapper omapper = new ObjectMapper();
            JsonNode node = omapper.valueToTree(dto);
            response.end(node.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.end("{\"Error\" : \""+e.toString()+"\" }");
		}
		
		
                

	}

}
