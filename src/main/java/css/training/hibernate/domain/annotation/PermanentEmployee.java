package css.training.hibernate.domain.annotation;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="PT")
public class PermanentEmployee extends Employee {

	@Override
	public boolean qualifiesForMediClaim() {
		return true;
	}

}
