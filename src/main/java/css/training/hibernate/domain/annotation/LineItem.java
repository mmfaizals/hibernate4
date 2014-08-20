package css.training.hibernate.domain.annotation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class LineItem {
	@Id
	@GeneratedValue(generator = "line-id")
	@GenericGenerator(name = "line-id", strategy = "hilo")
	private Integer id;
	private String name;
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
