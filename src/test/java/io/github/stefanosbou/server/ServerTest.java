package io.github.stefanosbou.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class ServerTest {
	private static Vertx vertx;
	static Properties properties;
	
	@BeforeClass
	public static void setUp(TestContext context) {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(
			        "resources/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    vertx = Vertx.vertx();
	    vertx.deployVerticle(Server.class.getName(),
	        context.asyncAssertSuccess());
	}
	
	@Test
	public void testServerSuccess(TestContext context) {
	    final Async async = context.async();
	    String ENDPOINT = properties.getProperty("endpoint");
	    String VERIFY_TOKEN = properties.getProperty("verify_token");
	    String CHALLENGE = "123456";
	    int port = Integer.parseInt(properties.getProperty("port"));
	    
	    String payload = "/?hub.mode=subscribe";
	    payload += "&hub.verify_token="+VERIFY_TOKEN;
	    payload += "&hub.challenge="+CHALLENGE;
	    
	    vertx.createHttpClient().get(port, "localhost", ENDPOINT+payload,
	     response -> {
	      response.handler(body -> {
	        context.assertTrue(body.toString().contains("12345"));
	        async.complete();
	      });
	    }).end();
	}
	@Test
	public void testServerWrongVerifyToken(TestContext context) {
	    final Async async = context.async();
	    String ENDPOINT = properties.getProperty("endpoint");
	    String VERIFY_TOKEN = properties.getProperty("verify_token");
	    String WRONG_TOKEN = VERIFY_TOKEN + "NOT";
	    String CHALLENGE = "123456";
	    int port = Integer.parseInt(properties.getProperty("port"));
	    
	    String payload = "/?hub.mode=subscribe";
	    payload += "&hub.verify_token="+WRONG_TOKEN;
	    payload += "&hub.challenge="+CHALLENGE;
	    
	    vertx.createHttpClient().get(port, "localhost", ENDPOINT+payload,
	     response -> {
	      response.handler(body -> {
	        context.assertTrue(body.toString().contains("WRONG_VERIFY_TOKEN_FOUND_IN_REQUEST"));
	        async.complete();
	      });
	    }).end();
	}
	
	@Test
	public void testServerEmptyRequest(TestContext context) {
	    final Async async = context.async();
	    String ENDPOINT = properties.getProperty("endpoint");
	    int port = Integer.parseInt(properties.getProperty("port"));
	    
	    vertx.createHttpClient().get(port, "localhost", ENDPOINT,
	     response -> {
	      response.handler(body -> {
	        context.assertTrue(body.toString().contains("EMPTY_OR_BAD_REQUEST"));
	        async.complete();
	      });
	    }).end();
	}
	
	@Test
	public void testServerBadRequestParams(TestContext context) {
	    final Async async = context.async();
	    String ENDPOINT = properties.getProperty("endpoint");
	    int port = Integer.parseInt(properties.getProperty("port"));
	    String payload = "/?hub.mode=WRONG_MODE";
	    
	    vertx.createHttpClient().get(port, "localhost", ENDPOINT+payload,
	     response -> {
	      response.handler(body -> {
	        context.assertTrue(body.toString().contains("EMPTY_OR_BAD_REQUEST"));
	        async.complete();
	      });
	    }).end();
	}
	
	@Test
	public void testServerStatusOK(TestContext context) {
		// Facebook requires, no matter what, to respond with status code 200
	    final Async async = context.async();
	    String ENDPOINT = properties.getProperty("endpoint");
	    int port = Integer.parseInt(properties.getProperty("port"));

	    vertx.createHttpClient().get(port, "localhost", ENDPOINT,
	     response -> {
	        context.assertTrue(response.statusCode() == 200);
	        async.complete();
	    }).end();
	}
	
	@AfterClass
	public static void tearDown(TestContext context) {
	    vertx.close(context.asyncAssertSuccess());
	}
	
}
