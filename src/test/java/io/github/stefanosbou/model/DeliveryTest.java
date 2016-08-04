package io.github.stefanosbou.model;

import static org.junit.Assert.assertEquals;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * 
 * "delivery":{
      "mids":[
         "mid.1458668856218:ed81099e15d3f4f233"
      ],
      "watermark":1458668856253,
      "seq":37
   }
 * 
 * */

public class DeliveryTest {
	String MID1 = "mid.1458668856218:ed81099e15d3f4f233";
	Long WATERMARK1 = 1458668856253L;
	Long SEQ1 =37L;
	
	String delivery1;
	Delivery deliveryObj1;
	
	JsonObject obj1;
	
	@Before
	public void setUp(){
		obj1 = new JsonObject()
	   		.put("mids", new JsonArray().add(MID1))
	   		.put("watermark", WATERMARK1)
	   		.put( "seq", SEQ1);
   		
			delivery1 = obj1.toString();
		   	try {
		   		deliveryObj1 = new ObjectMapper().readValue(delivery1, Delivery.class);
		   	} catch (IOException e) {
			   e.printStackTrace();
		   	}
	}
	 
	@Test
	public void testDeliveryModel() {
		assertEquals(MID1, deliveryObj1.getMids().get(0));
	    assertEquals(WATERMARK1, deliveryObj1.getWatermark());
	    assertEquals(SEQ1, deliveryObj1.getSeq());
	}
}
