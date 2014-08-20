package css.training.hibernate.domain.annotation;

import javax.persistence.Entity;

@Entity
public class ContractEmployee extends Employee {
	private Double salaryPerHour;

	public Double getSalaryPerHour() {
		return salaryPerHour;
	}

	public void setSalaryPerHour(Double salaryPerHour) {
		this.salaryPerHour = salaryPerHour;
	}

	@Override
	public boolean qualifiesForMediClaim() {
		return false;
	}
}
