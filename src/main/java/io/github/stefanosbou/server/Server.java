package io.github.stefanosbou.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class Server extends AbstractVerticle{
	private static final String CONFIG_FILE = "resources/config.properties";
	Properties properties;
	
	@Override
	public void start() {
		loadProperties(CONFIG_FILE);
		
	    Router router = Router.router(vertx);
	    
	    String ENDPOINT = properties.getProperty("endpoint");
	    
	    router.route().handler(BodyHandler.create());
	    router.get(ENDPOINT).handler(this::handleWebhookVerification);
//	    router.post(ENDPOINT).handler(this::processRequest);

	    int port = Integer.parseInt(properties.getProperty("port"));
	    vertx.createHttpServer().requestHandler(router::accept).listen(port);
	}
	
	private void loadProperties(String file) {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handleWebhookVerification(RoutingContext ctx){
		String VERIFY_TOKEN = properties.getProperty("verify_token");
		String res;
		MultiMap params = ctx.request().params();
		if(!params.isEmpty() && ctx.request().getParam("hub.mode").equals("subscribe")){
			String verifyToken = ctx.request().getParam("hub.verify_token");
			String challenge = ctx.request().getParam("hub.challenge");
			if (verifyToken.equals(VERIFY_TOKEN) && !challenge.isEmpty()) {
			      res = challenge;
			} else {
				res = "WRONG_VERIFY_TOKEN_FOUND_IN_REQUEST";
				System.out.println("Wrong verify token found in request");
			}
		}else{
			res = "EMPTY_OR_BAD_REQUEST";
			System.out.println("Exception no verify token found in request");
		}
		ctx.response().end(res);
	}
}
