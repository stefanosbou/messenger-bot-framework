package io.github.stefanosbou;

import io.github.stefanosbou.server.Server;
import io.vertx.core.Vertx;

public class App {
    public static void main(String[] args){
    	
    	Vertx vertx = Vertx.vertx();
    	vertx.deployVerticle(new Server(), res -> {
    		  if (res.succeeded()) {
    		    System.out.println("Deployment id is: " + res.result());
    		  } else {
    		    System.out.println("Deployment failed!");
    		  }
    	});
    }
}
