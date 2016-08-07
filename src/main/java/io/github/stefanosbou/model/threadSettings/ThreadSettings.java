package io.github.stefanosbou.model.threadSettings;

import io.github.stefanosbou.enums.ThreadSettingState;
import io.github.stefanosbou.enums.ThreadSettingType;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThreadSettings {

 @JsonProperty("setting_type")
 private String settingType;
 @JsonProperty("greeting")
 private Greeting greeting;
 @JsonProperty("thread_state")
 private String threadState;
 @JsonProperty("call_to_actions")
 private List<CallToAction> callToActions;

 	
 private ThreadSettings(ThreadSettingsBuilder builder) {
	if(builder.type == "greeting"){
		this.settingType = builder.type;
		this.greeting = builder.greeting;
	}else{
		this.settingType = builder.type;
		this.threadState = builder.threadState;
		this.callToActions = builder.callToActions;
	}
 }
 public String getSettingType() {
  return settingType;
 }
 public void setSettingType(String settingType) {
  this.settingType = settingType;
 }

 public Greeting getGreeting() {
  return greeting;
 }
 public void setGreeting(Greeting greeting) {
  this.greeting = greeting;
 }

 public String getThreadState() {
  return threadState;
 }
 public void setThreadState(String threadState) {
  this.threadState = threadState;
 }

 public List < CallToAction > getCallToActions() {
  return callToActions;
 }
 public void setCallToActions(List < CallToAction > callToActions) {
  this.callToActions = callToActions;
 }

 @Override
 public String toString() {
  return ToStringBuilder.reflectionToString(this);
 }

 @Override
 public int hashCode() {
  return new HashCodeBuilder().append(settingType).append(threadState).append(callToActions).toHashCode();
 }

 @Override
 public boolean equals(Object other) {
  if (other == this) {
   return true;
  }
  if ((other instanceof ThreadSettings) == false) {
   return false;
  }
  ThreadSettings rhs = ((ThreadSettings) other);
  return new EqualsBuilder().append(settingType, rhs.settingType).append(threadState, rhs.threadState).append(callToActions, rhs.callToActions).isEquals();
 }
 
	 public static NewThreadSettings builder() {
	     return new ThreadSettingsBuilder();
	 }
	 public interface NewThreadSettings {
	     Build addGreetingText(String greetingText);
	     Build addGetStartedButton(String payload);
	     PersistentMenu addPersistentMenu();
	 }
	 public interface PersistentMenu{
		 PersistentMenuTitle newButton();
	 }
	 public interface PersistentMenuTitle{
		 PersistentMenuType addTitle(String title);
	 }
	 public interface PersistentMenuType{
		 PersistentMenuEnd addPostback(String payload);
		 PersistentMenuEnd addUrl(String url);
	 }
	 public interface PersistentMenuEnd{
		 ThreadSettings build();
		 PersistentMenuTitle newButton();
	 }
	 
	 public interface Build{
		 ThreadSettings build();
	 }
 
	 private static class ThreadSettingsBuilder implements NewThreadSettings, Build, PersistentMenu, PersistentMenuTitle, PersistentMenuType, PersistentMenuEnd{
		private List<CallToAction> callToActions;
		private String threadState = null;
		private Greeting greeting = null;
		private String type = null;

		@Override
		public Build addGreetingText(String greetingText) {
			this.type = ThreadSettingType.greeting.getType();
			greeting = new Greeting();
			greeting.setText(greetingText);
			return this;
		}

		@Override
		public ThreadSettings build() {
			return new ThreadSettings(this);
		}

		@Override
		public Build addGetStartedButton(String payload) {
			this.type = ThreadSettingType.callToAactions.getType();
			this.threadState = ThreadSettingState.newThread.getType();
			this.callToActions = new ArrayList<>();
			CallToAction cta = new CallToAction();
			cta.setPayload(payload);
			this.callToActions.add(cta);
			return this;
		}

		@Override
		public PersistentMenu addPersistentMenu() {
			this.type = ThreadSettingType.callToAactions.getType();
			this.threadState = ThreadSettingState.existingThread.getType();
			this.callToActions = new ArrayList<>();
			return this;
		}

		@Override
		public PersistentMenuTitle newButton() {
			if(this.callToActions.size() < 5){
				callToActions.add(new CallToAction());
			}else{
				// do nothing or remove first and append the new
			}
			return this;
		}

		@Override
		public PersistentMenuType addTitle(String title) {
			int maxLength = 30;
			title = title.length() > maxLength ? title.substring(0, maxLength) : title;

			if (callToActions != null && !callToActions.isEmpty()) {
				callToActions.get(callToActions.size()-1).setTitle(title);
			}
			return this;
		}

		@Override
		public PersistentMenuEnd addPostback(String payload) {
			int maxLength = 1000;
			payload = payload.length() > maxLength ? payload.substring(0, maxLength) : payload;
			
			if (callToActions != null && !callToActions.isEmpty()) {
				callToActions.get(callToActions.size()-1).setType("postback");
				callToActions.get(callToActions.size()-1).setPayload(payload);
			}
			return this;
		}

		@Override
		public PersistentMenuEnd addUrl(String url) {
			if (callToActions != null && !callToActions.isEmpty()) {
				callToActions.get(callToActions.size()-1).setType("web_url");
				callToActions.get(callToActions.size()-1).setUrl(url);
			}
			return this;
		}
		
	 }
}