package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class NotMyVerticle extends AbstractVerticle {

	/* (non-Javadoc)
	 * @see io.vertx.core.AbstractVerticle#start(io.vertx.core.Future)
	 */
	@Override
	public void start(Future<Void> startFuture) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Not My Verticle started");
		vertx.eventBus().publish("Channel1", "Hello message");

	}

	/* (non-Javadoc)
	 * @see io.vertx.core.AbstractVerticle#stop(io.vertx.core.Future)
	 */
	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
		// TODO Auto-generated method stub
	}

}
