package io.github.stefanosbou.enums;

public enum MessageType {
	text("text", "text"),
	image("image", "richMedia"),
	video("video", "richMedia"),
	audio("audio", "richMedia"),
	file("file", "richMedia"),
	genericTemplate("generic", "template"),
	buttonTemplate("button", "template"),
	receipt("receipt", "template");
	
	private String type;
	private String category;
	
	MessageType(String type, String category){
		this.type = type;
		this.category = category;
	}
	
	public String getType(){
		return this.type;
	}
	
	public String getCategory(){
		return this.category;
	}
}