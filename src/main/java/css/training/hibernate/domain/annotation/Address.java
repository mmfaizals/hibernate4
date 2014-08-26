package css.training.hibernate.domain.annotation;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	private String street;
	private String city;
	private Integer pincode;
}
