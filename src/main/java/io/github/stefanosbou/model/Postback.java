package io.github.stefanosbou.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Postback {

	@JsonProperty("payload")
	private String payload;

	/**
	 *
	 * @return The payload
	 */
	public String getPayload() {
		return payload;
	}

	/**
	 *
	 * @param payload
	 *            The payload
	 */
	public void setPayload(String payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(payload).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Postback) == false) {
			return false;
		}
		Postback rhs = ((Postback) other);
		return new EqualsBuilder().append(payload, rhs.payload).isEquals();
	}

}
