package css.training.hibernate.domain;

import java.util.Set;

public class Person {
	private Integer id;
	private String firstName;
	private String lastName;
	private Set<Email> emails;
	private Set<Transport> transports;
	private Country country;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Transport> getTransports() {
		return transports;
	}

	public void setTransports(Set<Transport> transport) {
		this.transports = transport;
	}
}
