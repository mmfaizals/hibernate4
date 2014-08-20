package css.training.hibernate.domain.annotation;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "CT")
public class ContractEmployee extends Employee {
	@Override
	public boolean qualifiesForMediClaim() {
		return false;
	}
}
