package io.github.stefanosbou.bot;

import io.github.stefanosbou.model.*;
import io.github.stefanosbou.util.FacebookRestClient;
import io.github.stefanosbou.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class MessengerBot {
	protected static Map<String, FacebookProfile> users;
	
	protected abstract List<String> actOnText(String senderId, String text);
	protected abstract List<String> actOnLocation(String senderId, Payload payload);
	protected abstract List<String> actOnImage(String senderId, Payload payload);
	protected abstract List<String> actOnVideo(String senderId, Payload payload);
	protected abstract List<String> actOnAudio(String senderId, Payload payload);
	protected abstract List<String> actOnFile(String senderId, Payload payload);
	protected abstract List<String> actOnTemplate(String senderId, Payload payload);
	
	public abstract List<String> actOnPostback(Messaging event);
	public abstract List<String> actOnDelivery(Messaging event);
	
	public MessengerBot(){
		users = new HashMap<>();
	}
	
	public void actOnMessage(Messaging event){
		String senderId = event.getSender().getId();
		getUser(senderId);
		markAsRead(senderId);
		typingReply(senderId, true);
		List<String> res = event.getMessage().getText() != null ? actOnText(senderId, event.getMessage().getText()) : actOnAttachments(senderId, event.getMessage().getAttachments());
		res.forEach(reply ->{
			Message msg = Utils.jsonToObject(reply, Message.class);
			String fbReply = Utils.generateReply(senderId, msg);
			FacebookRestClient.sendReply(fbReply);
		});
		typingReply(senderId, false);
	}

	private List<String> actOnAttachments(String senderId,List<Attachment> attachments) {
		List<String> replies = new ArrayList<String>();
		attachments.forEach(attachment ->{
			switch(attachment.getType()){
				case "location":
					replies.addAll(Utils.convertNullToEmpty(actOnLocation(senderId, attachment.getPayload())));
					break;
				case "image":
					replies.addAll(Utils.convertNullToEmpty(actOnImage(senderId, attachment.getPayload())));
					break;
				case "video":
					replies.addAll(Utils.convertNullToEmpty(actOnVideo(senderId, attachment.getPayload())));
					break;	
				case "audio":
					replies.addAll(Utils.convertNullToEmpty(actOnAudio(senderId, attachment.getPayload())));
					break;	
				case "file":
					replies.addAll(Utils.convertNullToEmpty(actOnFile(senderId, attachment.getPayload())));
					break;	
			}
		});
		return replies;
	}
	
	private void getUser(String senderId) {
		if(users.get(senderId) == null){
			String link = Utils.getProfileLink(senderId);
			FacebookProfile profile = Utils.getObjectFromUrl(link, FacebookProfile.class);
			users.put(senderId, profile);
		}
	}
	public void markAsRead(String senderId) {
		FacebookRestClient.markAsRead(senderId);
	}
	public void typingReply(String senderId, boolean b) {
		FacebookRestClient.typingReply(senderId, b);
	}
	
}