package io.github.stefanosbou.model;

import static org.junit.Assert.assertEquals;
import io.vertx.core.json.JsonObject;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * 
 * {
            "type":"web_url",
            "url":"https://petersapparel.parseapp.com",
            "title":"Show Website"
          },
          {
            "type":"postback",
            "title":"Start Chatting",
            "payload":"USER_DEFINED_PAYLOAD"
          }
 * 
 * */

public class ButtonTest {
	String TYPE1 = "web_url";
	String URL1 = "https://petersapparel.parseapp.com";
	String TITLE1 = "Show Website";
	
	String TYPE2 = "postback";
	String PAYLOAD2 = "USER_DEFINED_PAYLOAD";
	String TITLE2 = "Start Chatting";
	
	String button1, button2;
	Button buttonObj1, buttonObj2;
	
	JsonObject obj1, obj2;
	
	   @Before
	   public void setUp(){
		   
		   obj1 = new JsonObject()
		   		.put("type", TYPE1)
		   		.put("url", URL1)
		   		.put( "title", TITLE1);
		   		
		   button1 = obj1.toString();
		   try {
			   buttonObj1 = new ObjectMapper().readValue(button1, Button.class);
		   } catch (IOException e) {
			   e.printStackTrace();
		   }
		   
		   obj2 = new JsonObject()
		   		.put("type", TYPE2)
		   		.put("payload", PAYLOAD2)
		   		.put( "title", TITLE2);
		   		
		   button2 = obj2.toString();
		   try {
			   buttonObj2 = new ObjectMapper().readValue(button2, Button.class);
		   } catch (IOException e) {
			   e.printStackTrace();
		   }
	   }
	   
	   @Test
	   public void testButtonModel() {
	      assertEquals(TYPE1, buttonObj1.getType());
	      assertEquals(URL1, buttonObj1.getUrl());
	      assertEquals(TITLE1, buttonObj1.getTitle());
	      assertEquals(null, buttonObj1.getPayload());
	      
	      assertEquals(TYPE2, buttonObj2.getType());
	      assertEquals(PAYLOAD2, buttonObj2.getPayload());
	      assertEquals(TITLE2, buttonObj2.getTitle());
	      assertEquals(null, buttonObj2.getUrl());
	      
	   }
}
