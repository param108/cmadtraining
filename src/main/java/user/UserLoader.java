package user;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

public class UserLoader implements Handler<RoutingContext> {

	@Override
	public void handle(RoutingContext ctxt) {
		// TODO Auto-generated method stub
		
		Datastore dataStore = ServicesFactory.getMongoDB();
		
		String id = ctxt.request().getParam("id");
		
		HttpServerResponse response=ctxt.response();
		response.putHeader("content-type", "application/json");

		ObjectId oid = null;
		try {
			oid = new ObjectId(id);
		} catch (Exception e) {			
		}
		
        List<User> users = dataStore.createQuery(User.class).field("id").equal(oid).asList();
        if (users.size() != 0) {
                UserDTO dto = new UserDTO().fillFromModel(users.get(0));
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.valueToTree(dto);
                response.end(node.toString());
        } else {
                response.setStatusCode(404).end("not found");
        }
	
	}

}
