package io.github.stefanosbou.model;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * 
text must be UTF-8 and has a 320 character limit
You cannot send a text and an attachment together, please read the Send API Reference for more details
 * 
 * 
 * */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
	@JsonProperty("is_echo")
	private Boolean isEcho;
	
	@JsonProperty("app_id")
	private Long appId;
	
	@JsonProperty("mid")
	private String mid;
	
	@JsonProperty("seq")
	private Long seq;
	
	@JsonProperty("text")
	private String text;
	
	@JsonProperty("sticker_id")
	private Long stickerId;
	
	@JsonProperty("attachments")
	private List<Attachment> attachments;

	@JsonProperty("attachment")
	private Attachment attachment;
	public Message(){
		
	}
	public Boolean isEcho(){
		return this.isEcho;
	}
	public void setEcho(Boolean echo){
		this.isEcho = echo;
	}
	public Message(String text){
		this.text = text;
	}
	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 
	 * @return The mid
	 */
	public String getMid() {
		return mid;
	}

	/**
	 * 
	 * @param mid
	 *            The mid
	 */
	public void setMid(String mid) {
		this.mid = mid;
	}

	/**
	 * 
	 * @return The seq
	 */
	public Long getSeq() {
		return seq;
	}

	/**
	 * 
	 * @param seq
	 *            The seq
	 */
	public void setSeq(Long seq) {
		this.seq = seq;
	}

	/**
	 * 
	 * @return The stickerId
	 */
	public Long getStickerId() {
		return stickerId;
	}

	/**
	 * 
	 * @param stickerId
	 *            The sticker_id
	 */
	public void setStickerId(Long stickerId) {
		this.stickerId = stickerId;
	}

	/**
	 * 
	 * @return The attachments
	 */
	public List<Attachment> getAttachments() {
		return attachments;
	}

	/**
	 * 
	 * @param attachments
	 *            The attachments
	 */
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(mid).append(seq).append(stickerId).append(attachments).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Message) == false) {
			return false;
		}
		Message rhs = ((Message) other);
		return new EqualsBuilder().append(mid, rhs.mid).append(seq, rhs.seq).append(stickerId, rhs.stickerId)
				.append(attachments, rhs.attachments).isEquals();
	}

}
