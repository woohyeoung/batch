package woo.hyeoung.batch.domain;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * name         : MARKET
 * version      : 1.0.0.1
 * date         : 2023-05-06
 * description  : 설명
 */
@Getter
@Table(name = "MARKET")
@Entity
public class MARKET {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigDecimal id;
	private String name;
	private BigDecimal price;

	@Override
	public String toString() {
		return "MARKET{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price=" + price +
				'}';
	}

	public void setPrice(BigDecimal price) {
		this.price = this.price.add(price);
	}
}
