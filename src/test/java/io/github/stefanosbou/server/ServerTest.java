package io.github.stefanosbou.server;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class ServerTest {
	private Vertx vertx;
	
	@Before
	public void setUp(TestContext context) {
	    vertx = Vertx.vertx();
	    vertx.deployVerticle(Server.class.getName(),
	        context.asyncAssertSuccess());
	}
	
	@After
	public void tearDown(TestContext context) {
	    vertx.close(context.asyncAssertSuccess());
	}
	
	@Test
	public void testMyApplication(TestContext context) {
	    final Async async = context.async();
	    String payload = "hub.mode=subscribe&hub.verify_token=VERIFY_TOKEN&hub.challenge=12345";
	    vertx.createHttpClient().get(8080, "localhost", "/?"+payload,
	     response -> {
	      response.handler(body -> {
	        context.assertTrue(body.toString().contains("12345"));
	        async.complete();
	      });
	    }).end();
	}
}
