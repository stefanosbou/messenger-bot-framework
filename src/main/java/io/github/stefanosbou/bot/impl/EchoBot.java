package io.github.stefanosbou.bot.impl;

import java.util.ArrayList;
import java.util.List;

import io.github.stefanosbou.bot.MessengerBot;
import io.github.stefanosbou.model.Message;
import io.github.stefanosbou.model.Messaging;
import io.github.stefanosbou.model.Payload;
import io.github.stefanosbou.util.Utils;


public class EchoBot extends MessengerBot{

	@Override
	protected List<String> actOnText(String senderId, String text) {
		List<String> replies = new ArrayList<String>();
		
//		Message replyMessage = Message.builder()
//				  				.addTemplate()
//				  				.genericTemplate()
//				  				.addElement()
//				  					.title("Welcome to Peter's Hats")
//				  					.imageUrl("http://petersapparel.parseapp.com/img/item100-thumb.png")
//				  					.subtitle("We've got the right hat for everyone.")
//				  					.addButton()
//				  						.buttonTitle("View Website")
//				  						.webUrl("https://petersapparel.parseapp.com/view_item?item_id=100")
//				  					.addButton()
//				  						.buttonTitle("Start Chatting")
//				  						.postback("USER_DEFINED_PAYLOAD")
//				  				.build();
		Message replyMessage = Message.builder()
								.addTemplate()
								.buttonTemplate()
									.mainBodyText("Blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah")
									.addButton()
										.buttonTitle("stefanos")
										.postback("me")
									.addButton()
										.buttonTitle("other")
										.postback("other")
									.addButton()
										.buttonTitle("other")
										.postback("other")	
									.build();
		replies.add(Utils.objectToJson(replyMessage));
		return replies;
	}

	@Override
	protected List<String> actOnLocation(String senderId, Payload payload) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<String> actOnImage(String senderId, Payload payload) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<String> actOnVideo(String senderId, Payload payload) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<String> actOnAudio(String senderId, Payload payload) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<String> actOnFile(String senderId, Payload payload) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<String> actOnTemplate(String senderId, Payload payload) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> actOnPostback(Messaging event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> actOnDelivery(Messaging event) {
		// TODO Auto-generated method stub
		return null;
	}

}
