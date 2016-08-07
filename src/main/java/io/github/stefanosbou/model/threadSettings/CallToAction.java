package io.github.stefanosbou.model.threadSettings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CallToAction {

 @JsonProperty("type")
 private String type;
 @JsonProperty("title")
 private String title;
 @JsonProperty("payload")
 private String payload;
 @JsonProperty("url")
 private String url;

 /**
  * 
  * @return
  * The type
  */
 public String getType() {
  return type;
 }

 /**
  * 
  * @param type
  * The type
  */
 public void setType(String type) {
  this.type = type;
 }

 /**
  * 
  * @return
  * The title
  */
 public String getTitle() {
  return title;
 }

 /**
  * 
  * @param title
  * The title
  */
 public void setTitle(String title) {
  this.title = title;
 }

 /**
  * 
  * @return
  * The payload
  */
 public String getPayload() {
  return payload;
 }

 /**
  * 
  * @param payload
  * The payload
  */
 public void setPayload(String payload) {
  this.payload = payload;
 }

 /**
  * 
  * @return
  * The url
  */
 public String getUrl() {
  return url;
 }

 /**
  * 
  * @param url
  * The url
  */
 public void setUrl(String url) {
  this.url = url;
 }

 @Override
 public String toString() {
  return ToStringBuilder.reflectionToString(this);
 }

 @Override
 public int hashCode() {
  return new HashCodeBuilder().append(type).append(title).append(payload).append(url).toHashCode();
 }

 @Override
 public boolean equals(Object other) {
  if (other == this) {
   return true;
  }
  if ((other instanceof CallToAction) == false) {
   return false;
  }
  CallToAction rhs = ((CallToAction) other);
  return new EqualsBuilder().append(type, rhs.type).append(title, rhs.title).append(payload, rhs.payload).append(url, rhs.url).isEquals();
 }

}