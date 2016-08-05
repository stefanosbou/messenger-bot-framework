package io.github.stefanosbou.bot.impl;

import java.util.List;

import io.github.stefanosbou.bot.MessengerBot;
import io.github.stefanosbou.model.Messaging;
import io.github.stefanosbou.model.Payload;


public class EchoBot extends MessengerBot{

	@Override
	protected List<String> actOnText(String senderId, String text) {
		// TODO Auto-generated method stub
		return null;
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
