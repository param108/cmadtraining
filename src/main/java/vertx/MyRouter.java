package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import user.UserLoader;
import user.UserUpdate;

public class MyRouter extends AbstractVerticle {

	/* (non-Javadoc)
	 * @see io.vertx.core.AbstractVerticle#start(io.vertx.core.Future)
	 */
	@Override
	public void start(Future<Void> startFuture) throws Exception {
		// TODO Auto-generated method stub
		HttpServer server = vertx.createHttpServer();
		Router router = Router.router(vertx);
		router.get("/services/users/:id").handler(new UserLoader());
		router.route().handler(BodyHandler.create());
		router.post("/services/user").handler(new UserUpdate());
		router.route().handler(StaticHandler.create()::handle);
		server.requestHandler(router::accept).listen(8080);
	}

	/* (non-Javadoc)
	 * @see io.vertx.core.AbstractVerticle#stop(io.vertx.core.Future)
	 */
	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VertxOptions options = new VertxOptions().setWorkerPoolSize(10);
		Vertx vertx = Vertx.vertx(options);
		vertx.deployVerticle("vertx.MyRouter");
	}

}
