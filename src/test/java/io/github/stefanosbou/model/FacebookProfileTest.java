package io.github.stefanosbou.model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.vertx.core.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;


/*
{
   "first_name": "John",
   "last_name": "Doe",
   "profile_pic": "https://www.example.com",
   "locale": "en_US",
   "timezone": 3,
   "gender": "male"
} 
 * */

public class FacebookProfileTest {
	String FIRST_NAME = "John";
	String LAST_NAME = "Doe";
	String PROFILE_PIC = "https://www.example.com";
	String LOCALE = "en_US";
	String TIMEZONE = "3";
	String GENDER = "male";
	
	String profile1;
	FacebookProfile profileObj1;
	
	JsonObject obj1;
	
	
	@Before
	public void setUp(){
		obj1 = new JsonObject()
   			.put("first_name", FIRST_NAME)
   			.put("last_name", LAST_NAME)
   			.put("profile_pic", PROFILE_PIC)
   			.put("locale", LOCALE)
   			.put("timezone", TIMEZONE)
   			.put( "gender", GENDER);
		
		profile1 = obj1.toString();
		
		try {
			profileObj1 = new ObjectMapper().readValue(profile1, FacebookProfile.class);
		} catch (IOException e) {
			   e.printStackTrace();
		}
	}
	
	@Test
	public void testFacebookProfileModel() {
		assertEquals(FIRST_NAME, profileObj1.getFirstName());
	    assertEquals(LAST_NAME, profileObj1.getLastName());
	    assertEquals(PROFILE_PIC, profileObj1.getProfilePic());
	    assertEquals(LOCALE, profileObj1.getLocale());
	    assertEquals(TIMEZONE, profileObj1.getTimezone());
	    assertEquals(GENDER, profileObj1.getGender());
	}
}
