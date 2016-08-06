package io.github.stefanosbou.model;

import io.github.stefanosbou.enums.MessageType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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
	
	public static MessageTypeStep builder() {
		return new Builder();
	}

	public interface MessageTypeStep {
		Build addText(String text);
		AttachmentType addAttachment();
		TemplateType addTemplate();
	}
	
	public interface AttachmentType {
        RichMediaPayload image();
        RichMediaPayload video();
        RichMediaPayload audio();
        RichMediaPayload file();
    }
	public interface RichMediaPayload {
        Build url(String url);
    }
	
	public interface TemplateType {
		GenericTemplatePayload genericTemplate();
//		ButtonTemplatePayload buttonTemplate();
//		ReceiptTemplatePayload receiptTemplate();
	}
	public interface GenericTemplatePayload {
		AddElementTitle addElement();
	}
	public interface AddElementTitle {
		AddNonMandatory title(String title);
	}
	public interface AddNonMandatory {
		AddNonMandatory subtitle(String title);
		AddNonMandatory imageUrl(String image_url);
		AddNonMandatory itemUrl(String image_url);
		Message build();
		AddElementTitle addElement();
		AddButton addButton();
	}
	public interface AddButton{
		AddButtonType buttonTitle(String title);
	}
	public interface AddButtonType{
		AddButtonMore webUrl(String url);
		AddButtonMore postback(String payload);
	}
	public interface AddButtonMore{
		AddButton addButton();
		Message build(); 
	}
	public interface Build {
		Message build();
    
	}

	public static class Builder implements MessageTypeStep, AttachmentType, 
			RichMediaPayload, TemplateType, 
			GenericTemplatePayload, AddElementTitle, 
			Build, AddNonMandatory, AddButton, 
			AddButtonType, AddButtonMore {
		
    	private String text;
    	private MessageType type;
		private String url;
		private List<Element> elements = null;
		private List<Button> buttons = null;

        @Override
        public Message build() {
            return new Message(this);
        }
        @Override
        public Build addText(String text) {
        	this.type = MessageType.text;
            text = Objects.requireNonNull(text);
            int maxLength = 320;
            text = text.length() > maxLength ? text.substring(0, maxLength) : text;
            this.text = text;
            return this;
        }
		@Override
		public Build url(String url) {
			Objects.requireNonNull(url);
            this.url = url;
            return this;
		}
		@Override
		public RichMediaPayload image() {
			this.type = MessageType.image;
			return this;
		}
		@Override
		public AttachmentType addAttachment() {
			return this;
		}
		@Override
		public RichMediaPayload video() {
			this.type = MessageType.video;
			return this;
		}
		@Override
		public RichMediaPayload audio() {
			this.type = MessageType.audio;
			return this;
		}
		@Override
		public RichMediaPayload file() {
			this.type = MessageType.file;
			return this;
		}
		@Override
		public TemplateType addTemplate() {
			return this;
		}
		@Override
		public GenericTemplatePayload genericTemplate() {
			this.type = MessageType.genericTemplate;
			return this;
		}
		@Override
		public AddElementTitle addElement() {
			if(elements == null)
				elements = new ArrayList<Element>();
			if(elements.size() < 10) {
				elements.add(new Element());
				elements.get(elements.size()-1).setButtons(new ArrayList<Button>());
			} else {
				   // do nothing or remove first and append the new
			}
			return this;
		}
		@Override
		public AddNonMandatory title(String title) {
//			title max 80chars
			int maxLength = 80;
			title = title.length() > maxLength ? title.substring(0, maxLength) : title;
			
			if (elements != null && !elements.isEmpty()) {
				elements.get(elements.size()-1).setTitle(title);
			}
			return this;
		}
		@Override
		public AddNonMandatory subtitle(String subtitle) {
//			subtitle max 80chars
			int maxLength = 80;
			subtitle = subtitle.length() > maxLength ? subtitle.substring(0, maxLength) : subtitle;
			if (elements != null && !elements.isEmpty()) {
				elements.get(elements.size()-1).setSubtitle(subtitle);
			}
			return this;
		}
		@Override
		public AddNonMandatory imageUrl(String image_url) {
//			checkValidImageUrl(image_url); // support jpg, png and gif images
			if (elements != null && !elements.isEmpty()) {
				elements.get(elements.size()-1).setImageUrl(image_url);
			}
			return this;
		}
		@Override
		public AddNonMandatory itemUrl(String item_url) {
//			checkValidUrl(item_url);
			if (elements != null && !elements.isEmpty()) {
				elements.get(elements.size()-1).setItemUrl(item_url);
			}
			return this;
		}
		@Override
		public AddButton addButton() {
			if (elements != null && !elements.isEmpty()) {
				buttons = elements.get(elements.size()-1).getButtons();
				if(buttons.size() < 3) {
					buttons.add(new Button());
				} else {
					   // do nothing or remove first and append the new
				}
			}
			return this;
		}
		@Override
		public AddButtonMore webUrl(String url) {
//			checkValidUrl(url);
//			
			if (buttons != null && !buttons.isEmpty()) {
				buttons.get(buttons.size()-1).setUrl(url);
				buttons.get(buttons.size()-1).setType("web_url");
			}
			return this;
		}
		@Override
		public AddButtonMore postback(String payload) {
//			payload max 1000chars
			int maxLength = 1000;
			payload = payload.length() > maxLength ? payload.substring(0, maxLength) : payload;
			
			if (buttons != null && !buttons.isEmpty()) {
				buttons.get(buttons.size()-1).setPayload(payload);
				buttons.get(buttons.size()-1).setType("postback");
			}
			return this;
		}
		@Override
		public AddButtonType buttonTitle(String title) {
//			title max 80chars
			int maxLength = 80;
			title = title.length() > maxLength ? title.substring(0, maxLength) : title;
			
			if (buttons != null && !buttons.isEmpty()) {
				buttons.get(buttons.size()-1).setTitle(title);
			}
			return this;
		}
	}

    private Message(Builder builder) {
    	if(builder.type.getCategory().equals("text")){
    		this.text = builder.text;
    	}else if(builder.type.getCategory().equals("richMedia")){
    		Payload payload = new Payload();
    		payload.setUrl(builder.url);
    		Attachment attachment = new Attachment();
    		attachment.setType(builder.type.getType());
    		attachment.setPayload(payload);
    		this.attachment = attachment;
    	}else{
    		if(builder.type.getType().equals("generic")){
    			Payload payload = new Payload();
        		payload.setElements(builder.elements);
    			payload.setTemplateType(builder.type.getType());
        		Attachment attachment = new Attachment();
        		attachment.setType(builder.type.getCategory());
        		attachment.setPayload(payload);
        		this.attachment = attachment;
    		}
    	}
    }

}
