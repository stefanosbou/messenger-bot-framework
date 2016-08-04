package io.github.stefanosbou.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates {
	@JsonProperty("lat")
    private double latitude;
	
	@JsonProperty("long")
    private double longitude;
	
	/**
     * 
     * @return
     *     The latitude
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * 
     * @param latitude
     *     The latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    /**
     * 
     * @return
     *     The longitude
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * 
     * @param longitude
     *     The longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(latitude).append(longitude).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Coordinates) == false) {
            return false;
        }
        Coordinates rhs = ((Coordinates) other);
        return new EqualsBuilder().append(latitude, rhs.latitude).append(longitude, rhs.longitude)
                .isEquals();
    }
}
