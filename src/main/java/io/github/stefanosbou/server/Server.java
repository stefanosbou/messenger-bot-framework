package io.github.stefanosbou.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.stefanosbou.bot.MessengerBot;
import io.github.stefanosbou.model.Entry;
import io.github.stefanosbou.model.FacebookPayload;
import io.github.stefanosbou.model.Messaging;
import io.github.stefanosbou.util.Utils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class Server extends AbstractVerticle{
	Properties properties;
	MessengerBot messengerBot;
	
	@Override
	public void start() {
		loadProperties(Utils.CONFIG_FILE);
		
	    Router router = Router.router(vertx);
	    
	    String ENDPOINT = properties.getProperty("endpoint");
	    
	    router.route().handler(BodyHandler.create());
	    router.get(ENDPOINT).handler(this::handleWebhookVerification);
	    router.post(ENDPOINT).handler(this::processRequest);

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
	
	private void processRequest(RoutingContext ctx){
		String body = ctx.getBodyAsString();
		FacebookPayload facebookPayload;
		try {
			facebookPayload = new ObjectMapper().readValue(body, FacebookPayload.class);
			if(facebookPayload == null) {
				System.out.println("facebookPayload was null");
			}else{
				if(facebookPayload.getObject().equals("page")){
					for(Entry entry : facebookPayload.getEntry()){
					    
					    for(Messaging event : entry.getMessaging()){
					    	if(event.getMessage() != null){
					    		receivedMessage(event);
					    	}else if(event.getDelivery() != null){
					    		receivedDeliveryConfirmation(event);
					    	}else if(event.getPostback() != null){
					    		receivedPostback(event);
					    	}else {
					            System.out.println("Webhook received unknown messagingEvent: " + event.toString());
					        }
					    }
					}
				}
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ctx.response().end();
	}

	private void receivedMessage(Messaging event) {
//		String senderId = event.getSender().getId();
//		messengerBot.markAsRead(senderId);
//		messengerBot.typingReply(senderId, true);
//		List<String> replies = messengerBot.actOnMessage(event);
//		replies.forEach(reply -> messengerBot.sendReply(reply));
//		messengerBot.typingReply(senderId, false);
		
		messengerBot.actOnMessage(event);
	}

	private void receivedDeliveryConfirmation(Messaging event) {
		// TODO Auto-generated method stub
		
	}

	private void receivedPostback(Messaging event) {
		// TODO Auto-generated method stub
		
	}
	
}
