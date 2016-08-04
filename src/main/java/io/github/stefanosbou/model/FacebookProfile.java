package io.github.stefanosbou.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FacebookProfile {
	@JsonProperty("first_name")
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("profile_pic")
	private String profilePic;
	
	@JsonProperty("locale")
	private String locale;
	
	@JsonProperty("timezone")
	private String timezone;
	
	@JsonProperty("gender")
	private String gender;

	/**
	 *
	 * @return The firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 *
	 * @param firstName
	 *            The first_name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 *
	 * @return The lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 *
	 * @param lastName
	 *            The last_name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 *
	 * @return The profilePic
	 */
	public String getProfilePic() {
		return profilePic;
	}

	/**
	 *
	 * @param profilePic
	 *            The profile_pic
	 */
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	/**
	 *
	 * @return The locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 *
	 * @param locale
	 *            The locale
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 *
	 * @return The timezone
	 */
	public String getTimezone() {
		return timezone;
	}

	/**
	 *
	 * @param timezone
	 *            The timezone
	 */
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	/**
	 *
	 * @return The gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 *
	 * @param gender
	 *            The gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
