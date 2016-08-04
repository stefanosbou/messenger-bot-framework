package io.github.stefanosbou.model;

import static org.junit.Assert.assertEquals;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * 
 * {
            "title":"Classic White T-Shirt",
            "subtitle":"100% Soft and Luxurious Cotton",
            "image_url":"http://petersapparel.parseapp.com/img/whiteshirt.png",
            "buttons":[
		          {
		            "type":"web_url",
		            "url":"https://petersapparel.parseapp.com",
		            "title":"Show Website"
		          }
		     ]
          }
 * 
 * */

public class ElementTest {
	String TITLE1 = "Classic White T-Shirt";
	String SUBTITLE1 = "100% Soft and Luxurious Cotton";
	String IMAGEURL1 = "http://petersapparel.parseapp.com/img/whiteshirt.png";
	String BUTTON1 = " {\"type\":\"web_url\", \"url\":\"https://petersapparel.parseapp.com\", \"title\":\"Show Website\"}";
	
	String elem1;
	Element elemObj1;
	Button buttonObj1;
	
	JsonObject obj1;
	
	@Before
	public void setUp(){
		try {
			   buttonObj1 = new ObjectMapper().readValue(BUTTON1, Button.class);
		} catch (IOException e) {
			   e.printStackTrace();
		}
				
		obj1 = new JsonObject()
	   		.put("title", TITLE1)
	   		.put("subtitle", SUBTITLE1)
	   		.put( "image_url", IMAGEURL1)
	   		.put("buttons", new JsonArray().add(new JsonObject(BUTTON1)) );
		
		elem1 = obj1.toString();
		try {
			   elemObj1 = new ObjectMapper().readValue(elem1, Element.class);
		} catch (IOException e) {
			   e.printStackTrace();
		}
		   
	}
	
	@Test
	public void testButtonModel() {
		assertEquals(TITLE1, elemObj1.getTitle());
	    assertEquals(SUBTITLE1, elemObj1.getSubtitle());
	    assertEquals(IMAGEURL1, elemObj1.getImageUrl());
		assertEquals(buttonObj1, elemObj1.getButtons().get(0));
	    
	}
}
