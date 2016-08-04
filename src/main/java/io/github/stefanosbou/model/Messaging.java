package io.github.stefanosbou.model;

import io.github.stefanosbou.enums.SenderAction;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Messaging {

	@JsonProperty("sender")
	private Sender sender;
	
	@JsonProperty("recipient")
	private Recipient recipient;
	
	@JsonProperty("timestamp")
	private long timestamp;
	
	@JsonProperty("message")
	private Message message;
	
	@JsonProperty("postback")
	private Postback postback;
	
	@JsonProperty("delivery")
	private Delivery delivery;
	
	@JsonProperty("read")
	private Read read;
	
	@JsonProperty("sender_action")
	private String sender_action;
	
	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}
	
	public String getSenderAction() {
		return sender_action;
	}

	public void setSenderAction(SenderAction senderAction) {
		this.sender_action = senderAction.name();
	}

	public Postback getPostback() {
		return postback;
	}

	public void setPostback(Postback postback) {
		this.postback = postback;
	}

	/**
	 * 
	 * @return The sender
	 */
	public Sender getSender() {
		return sender;
	}

	/**
	 * 
	 * @param sender
	 *            The sender
	 */
	public void setSender(Sender sender) {
		this.sender = sender;
	}

	/**
	 * 
	 * @return The recipient
	 */
	public Recipient getRecipient() {
		return recipient;
	}

	/**
	 * 
	 * @param recipient
	 *            The recipient
	 */
	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	/**
	 * 
	 * @return The timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * 
	 * @param timestamp
	 *            The timestamp
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * 
	 * @return The message
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * 
	 * @param message
	 *            The message
	 */
	public void setMessage(Message message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(sender).append(recipient).append(timestamp).append(message).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Messaging) == false) {
			return false;
		}
		Messaging rhs = ((Messaging) other);
		return new EqualsBuilder().append(sender, rhs.sender).append(recipient, rhs.recipient)
				.append(timestamp, rhs.timestamp).append(message, rhs.message).isEquals();
	}

}


