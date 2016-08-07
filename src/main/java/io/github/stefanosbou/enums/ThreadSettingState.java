package io.github.stefanosbou.enums;

public enum ThreadSettingState {
	newThread("new_thread"),
	existingThread("existing_thread"),
	;
	
	private String type;
	
	ThreadSettingState(String type){
		this.type = type;
	}
	
	public String getType(){
		return this.type;
	}
}
