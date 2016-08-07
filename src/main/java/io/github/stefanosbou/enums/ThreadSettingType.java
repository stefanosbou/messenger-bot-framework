package io.github.stefanosbou.enums;

public enum ThreadSettingType {
	greeting("greeting"),
	callToAactions("call_to_actions"),
	;
	
	private String type;
	
	ThreadSettingType(String type){
		this.type = type;
	}
	
	public String getType(){
		return this.type;
	}

}
