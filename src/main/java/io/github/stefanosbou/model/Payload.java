package io.github.stefanosbou.model;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payload {

	@JsonProperty("template_type")
	private String templateType;
	
	@JsonProperty("text")
	private String text;
	
	@JsonProperty("elements")
	private List<Element> elements;
	
	@JsonProperty("buttons")
	private List<Button> buttons;
	
	@JsonProperty("url")
	private String url;
	
	@JsonProperty("coordinates")
	private Coordinates coordinates;
	
	public List<Button> getButtons() {
		return buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	/**
	 * 
	 * @return The templateType
	 */
	public String getTemplateType() {
		return templateType;
	}

	/**
	 * 
	 * @param templateType
	 *            The template_type
	 */
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	/**
	 * 
	 * @return The elements
	 */
	public List<Element> getElements() {
		return elements;
	}

	/**
	 * 
	 * @param elements
	 *            The elements
	 */
	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(templateType).append(elements).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Payload) == false) {
			return false;
		}
		Payload rhs = ((Payload) other);
		return new EqualsBuilder().append(templateType, rhs.templateType).append(elements, rhs.elements).isEquals();
	}

}
