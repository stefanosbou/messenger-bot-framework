package io.github.stefanosbou.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.stefanosbou.enums.SenderAction;
import io.github.stefanosbou.model.*;



public class Utils {
	public static final String CONFIG_FILE = "resources/config.properties";
	private static final String BASE_URL = "https://graph.facebook.com/v2.7/me/messages?access_token=";
	private static String PROFILE_LINK = "https://graph.facebook.com/v2.7/SENDER_ID?access_token=";
	private static String ACCESS_TOKEN;
	/*
	 * @param senderId
	 * @param message
	 * @return
	 */
	public static String generateReply(String senderId, Message message) {
		Recipient recipient = new Recipient();
		recipient.setId(senderId);
		Messaging reply = new Messaging();
		reply.setRecipient(recipient);
		reply.setMessage(message);
		
		return objectToJson(reply);
	}
	
	public static String generateSenderAction(String senderId, SenderAction action) {
		Recipient recipient = new Recipient();
		recipient.setId(senderId);
		Messaging reply = new Messaging();
		reply.setRecipient(recipient);
		reply.setSenderAction(action);
		
		return objectToJson(reply);
	}
	
	/**
	 * Returns object of type clazz from an json api link
	 * 
	 * @param link
	 * @param clazz
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws Exception
	 */
	public static <T> T getObjectFromUrl(String link, Class<T> clazz){
		T t = null;
		URL url;
		String jsonString = "";
		try {
			url = new URL(link);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				jsonString = jsonString + inputLine;
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!StringUtils.isEmpty(jsonString)) {
			try {
				t = new ObjectMapper().readValue(jsonString, clazz);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return t;
	}
	
	public static List<String> convertNullToEmpty(List<String> c){
		if(c!= null)
			return c;
		else
			return new ArrayList<String>();
					
	}
	
	public static <T> T jsonToObject(String jsonString, Class<T> clazz){
		// Jackson lib implementation
		T t = null;
		if (!StringUtils.isEmpty(jsonString)) {
			try {
				t = new ObjectMapper().readValue(jsonString, clazz);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return t;
		
		/* Gson lib implementation
		 * 
		 * T t = gson.fromJson(jsonInString, T.class);
		 * return t;
		 * */
	}
	
	public static String objectToJson(Object obj){
		// Jackson lib implementation
		String str = null;
		try {
			str = new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return str;
		
		/* Gson lib implementation
		 * 
		 * return new Gson().toJson(reply)
		 * 
		 * */
	}

	public static String getAccessToken() {
		if(ACCESS_TOKEN.isEmpty()){
			Properties properties = new Properties();
			try {
				properties.load(new FileInputStream(CONFIG_FILE));
			} catch (IOException e) {
				e.printStackTrace();
			}
			ACCESS_TOKEN = properties.getProperty("access_token");
		}
		return ACCESS_TOKEN;
	}
	
	public static String getSendAPI(){
		return BASE_URL + getAccessToken();
	}
	
	public static String getProfileLink(String id){
		String str = StringUtils.replace(PROFILE_LINK, "SENDER_ID", id);
		return str + getAccessToken();
	}
}
