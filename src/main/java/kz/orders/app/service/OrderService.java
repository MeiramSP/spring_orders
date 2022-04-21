package kz.orders.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kz.orders.app.entity.Order;
import kz.orders.app.entity.Status;
import kz.orders.app.exception.OrderException;
import kz.orders.app.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	public List<Order> getAll() {
		return orderRepository.findAll();
	}
	
	
	public void changeOrderStatus(Long order_id) {
		Order order = orderRepository.findById(order_id)
			.orElseThrow(() -> new OrderException("Заказ с id = " + order_id + " не найден"));
		
		if(order.getStatus() != Status.ACCEPTED)
			throw new OrderException("Этот статус может быть изменен только курьерской службой");
		
		order.setStatus(Status.PROCESSING);
		orderRepository.save(order);
		
	}
		
	

}
