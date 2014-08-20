package css.training.hibernate.domain.annotation;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "`order`")
public class Order {
	@Id
	@GeneratedValue(generator = "order-id")
	@GenericGenerator(name = "order-id", strategy = "hilo")
	private Integer id;
	private String name;
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<LineItem> lines;

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

	public List<LineItem> getLines() {
		return lines;
	}

	public void setLines(List<LineItem> lines) {
		this.lines = lines;
	}
}
