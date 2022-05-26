package jpabook.jpashop.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order {

	@Id @GeneratedValue
	private Long id;
	private Long memberId;
	private LocalDateTime orderDate;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

}
