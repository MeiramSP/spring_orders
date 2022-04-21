package kz.orders.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	@Id
	private Long id;
	
	@Enumerated(EnumType.ORDINAL)
	private Status status;
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL,
			fetch = FetchType.LAZY, optional = false)
	@PrimaryKeyJoinColumn
	public CourierOrder courierOrder;
	
	public Order() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public CourierOrder getCourierOrder() {
		return courierOrder;
	}

	public void setCourierOrder(CourierOrder courierOrder) {
		this.courierOrder = courierOrder;
	}
}
