package io.github.stefanosbou.model;

import static org.junit.Assert.*;
import io.vertx.core.json.JsonObject;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CoordinatesTest {
	private static final double DELTA = 1e-15;
	
	double LONGITUDE1 = 34.6;
	double LATITUDE1 = 45.7;
	
	String LONGITUDE2 = "54.8";
	String LATITUDE2 = "67.9";
	
	String location1, location2;
	Coordinates locationObj1, locationObj2;
	
	JsonObject loc1, loc2;
	
	@Before
	public void setUp(){
		   
		   loc1 = new JsonObject()
		   		.put("long", LONGITUDE1)
		   		.put("lat", LATITUDE1);
		   
		   location1 = loc1.toString();
		   try {
			   locationObj1 = new ObjectMapper().readValue(location1, Coordinates.class);
		   } catch (IOException e) {
			   e.printStackTrace();
		   }
		   
		   loc2 = new JsonObject()
		   		.put("long", LONGITUDE2)
		   		.put("lat", LATITUDE2);
		   		
		   location2 = loc2.toString();
		   try {
			   locationObj2 = new ObjectMapper().readValue(location2, Coordinates.class);
		   } catch (IOException e) {
			   e.printStackTrace();
		   }
	}
	
	@Test
	public void testCoordinatesModel() {
	      assertEquals(LONGITUDE1, locationObj1.getLongitude(), DELTA);
	      assertEquals(LATITUDE1, locationObj1.getLatitude(), DELTA);
	      
	      assertEquals(Double.parseDouble(LONGITUDE2), locationObj2.getLongitude(), DELTA);
	      assertEquals(Double.parseDouble(LATITUDE2), locationObj2.getLatitude(), DELTA);
	      
	      
	}
}
