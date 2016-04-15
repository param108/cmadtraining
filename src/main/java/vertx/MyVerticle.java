package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MyVerticle extends AbstractVerticle {
	/* (non-Javadoc)
	 * @see io.vertx.core.AbstractVerticle#start(io.vertx.core.Future)
	 */
	@Override
	public void start(Future<Void> startFuture) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("My verticle started");
		vertx.eventBus().consumer("Channel1",message -> {
			System.out.println("message.body() = "+message.body());
		});
		startFuture.complete();
	}

	/* (non-Javadoc)
	 * @see io.vertx.core.AbstractVerticle#stop(io.vertx.core.Future)
	 */
	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("My Verticle stopped");
	}
	
}


