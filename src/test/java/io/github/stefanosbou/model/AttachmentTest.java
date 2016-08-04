package io.github.stefanosbou.model;

import static org.junit.Assert.assertEquals;
import io.vertx.core.json.JsonObject;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;


/*
 * https://developers.facebook.com/docs/messenger-platform/webhook-reference/message-received#attachment
	{
        "type":"image",
        "payload":{
        	"url":"IMAGE_URL"
		}
	}
 * */
public class AttachmentTest {
	String TYPE = "image";
	Payload PAYLOAD;
	String URL = "IMAGE_URL";
	
	String attachment1;
	Attachment attachmentObj1;
	
	JsonObject obj1;
	
	@Before
	public void setUp(){
		JsonObject imageObj = new JsonObject().put("url", URL);
		try {
			PAYLOAD = new ObjectMapper().readValue(imageObj.toString(), Payload.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		obj1 = new JsonObject()
					.put("type", TYPE)
					.put("payload", new JsonObject(imageObj.toString()));
		
		attachment1 = obj1.toString();
		try {
			   attachmentObj1 = new ObjectMapper().readValue(attachment1, Attachment.class);
		} catch (IOException e) {
			   e.printStackTrace();
		}
	}
	
	@Test
	public void testAttachmentModel() {
		 assertEquals(TYPE, attachmentObj1.getType());
	     assertEquals(PAYLOAD, attachmentObj1.getPayload());
	}
}