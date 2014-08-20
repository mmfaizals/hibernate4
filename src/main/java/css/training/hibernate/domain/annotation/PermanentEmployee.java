package css.training.hibernate.domain.annotation;

import javax.persistence.Entity;

@Entity
public class PermanentEmployee extends Employee {
	private Double salaryPerMonth;
	private Double bonus;

	public Double getSalaryPerMonth() {
		return salaryPerMonth;
	}

	public void setSalaryPerMonth(Double salaryPerMonth) {
		this.salaryPerMonth = salaryPerMonth;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	@Override
	public boolean qualifiesForMediClaim() {
		return true;
	}

}
