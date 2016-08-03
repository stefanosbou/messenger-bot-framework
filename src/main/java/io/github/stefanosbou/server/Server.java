package io.github.stefanosbou.server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class Server extends AbstractVerticle{
	
	@Override
	public void start() {
		
	    Router router = Router.router(vertx);

	    router.route().handler(BodyHandler.create());
	    router.get("/").handler(this::handleWebhookVerification);
//	    router.post(ENDPOINT).handler(this::processRequest);

	    vertx.createHttpServer().requestHandler(router::accept).listen(8080);
	}
	
	private void handleWebhookVerification(RoutingContext ctx){
		String res;
		MultiMap params = ctx.request().params();
		if(params != null && ctx.request().getParam("hub.mode").equals("subscribe")){
			String verifyToken = ctx.request().getParam("hub.verify_token");
			String challenge = ctx.request().getParam("hub.challenge");
			if (verifyToken.equals("VERIFY_TOKEN") && !challenge.isEmpty()) {
			      res = challenge;
			} else {
				res = null;
				System.out.println("Wrong verify token found in request");
			}
		}else{
			res = null;
			System.out.println("Exception no verify token found in request");
		}
		ctx.response().end(res);
	}
}
