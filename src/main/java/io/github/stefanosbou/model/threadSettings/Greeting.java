package io.github.stefanosbou.model.threadSettings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Greeting {
 @JsonProperty("text")
 private String text;

 /**
  * 
  * @return
  * The text
  */
 public String getText() {
  return text;
 }

 /**
  * 
  * @param text
  * The text
  */
 public void setText(String text) {
  this.text = text;
 }

 @Override
 public String toString() {
  return ToStringBuilder.reflectionToString(this);
 }

 @Override
 public int hashCode() {
  return new HashCodeBuilder().append(text).toHashCode();
 }

 @Override
 public boolean equals(Object other) {
  if (other == this) {
   return true;
  }
  if ((other instanceof Greeting) == false) {
   return false;
  }
  Greeting rhs = ((Greeting) other);
  return new EqualsBuilder().append(text, rhs.text).isEquals();
 }

}