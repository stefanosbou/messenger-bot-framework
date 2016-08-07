package io.github.stefanosbou.model.threadSettings;

import static org.junit.Assert.assertEquals;
import io.vertx.core.json.JsonObject;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ThreadSettingsTest {
	String str1, str2, str3;
	ThreadSettings set1, set2, set3;
	
	@Before
	public void setUp(){
		str1 = "{\"setting_type\":\"greeting\",\"greeting\":{\"text\":\"Welcome to My Company\"}}";
		str2 = "{\"setting_type\":\"call_to_actions\", \"thread_state\":\"new_thread\", \"call_to_actions\":[{\"payload\":\"USER_DEFINED_PAYLOAD\"} ] }";
		str3 = "{\"setting_type\" : \"call_to_actions\", \"thread_state\" : \"existing_thread\", \"call_to_actions\":[{\"type\":\"postback\", \"title\":\"Help\", \"payload\":\"DEVELOPER_DEFINED_PAYLOAD_FOR_HELP\"}, {\"type\":\"postback\", \"title\":\"Start a New Order\", \"payload\":\"DEVELOPER_DEFINED_PAYLOAD_FOR_START_ORDER\"}, {\"type\":\"web_url\", \"title\":\"View Website\", \"url\":\"URL\"} ] }";
		
		JsonObject obj1 = new JsonObject()
							.put("setting_type", "greeting")
							.put("greeting", new JsonObject().put("text", "Welcome to My Company"));
				
		
		try {
			set1 = new ObjectMapper().readValue(str1, ThreadSettings.class);
			set2 = new ObjectMapper().readValue(str2, ThreadSettings.class);
			set3 = new ObjectMapper().readValue(str3, ThreadSettings.class);
			
			System.out.println(new ObjectMapper().writeValueAsString(set1));
			System.out.println(obj1.toString());
			System.out.println(str1);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void testButtonModel() {
		try {
			assertEquals(str1, new ObjectMapper().writeValueAsString(set1));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		assertEquals("greeting", set1.getSettingType());
		assertEquals("call_to_actions", set2.getSettingType());
		assertEquals("call_to_actions", set3.getSettingType());
	}
}
