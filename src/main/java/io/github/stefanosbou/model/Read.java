package io.github.stefanosbou.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Read {

	@JsonProperty("watermark")
	private Long watermark;
	
	@JsonProperty("seq")
	private Long seq;

	/**
	 *
	 * @return The watermark
	 */
	public Long getWatermark() {
		return watermark;
	}

	/**
	 *
	 * @param watermark
	 *            The watermark
	 */
	public void setWatermark(Long watermark) {
		this.watermark = watermark;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(watermark).append(seq).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Read) == false) {
			return false;
		}
		Read rhs = ((Read) other);
		return new EqualsBuilder().append(watermark, rhs.watermark).append(seq, rhs.seq)
				.isEquals();
	}

}