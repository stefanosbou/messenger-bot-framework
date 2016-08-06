package io.github.stefanosbou.util;

import io.github.stefanosbou.enums.SenderAction;

import java.util.concurrent.Future;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;


public class FacebookRestClient {
	  	  
	  public static void sendReply(String reply) {
		  Future<HttpResponse<JsonNode>> future = 
				  Unirest.post(Utils.getSendAPI())
				  	.header("content-type", "application/json")
				  	.body(reply)
				  	.asJsonAsync(new Callback<JsonNode>() {
				  		
				  		@Override
					    public void failed(UnirestException e) {
					        System.out.println("The request has failed");
					    }
	
					    public void completed(HttpResponse<JsonNode> response) {
					         int code = response.getStatus();
					         System.out.println("The request has been completed " + response.getStatus());
//					         Map<String, String> headers = response.getHeaders();
//					         JsonNode body = response.getBody();
					         System.out.println(response.getBody());
//					         InputStream rawBody = response.getRawBody();
					    }
	
					    public void cancelled() {
					        System.out.println("The request has been cancelled");
					    }
					});
	  }

	public static void markAsRead(String senderId) {
		sendReply(Utils.generateSenderAction(senderId, SenderAction.mark_seen));
	}

	public static void typingReply(String senderId, boolean b) {
		if(b)
			sendReply(Utils.generateSenderAction(senderId, SenderAction.typing_on));
		else
			sendReply(Utils.generateSenderAction(senderId, SenderAction.typing_off));
	}	
}
