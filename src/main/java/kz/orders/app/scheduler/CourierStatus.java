package kz.orders.app.scheduler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kz.orders.app.entity.Order;
import kz.orders.app.entity.Status;
import kz.orders.app.repository.OrderRepository;

@Component
public class CourierStatus {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Scheduled(fixedRate = 5000)
	public void updateOrderStatus() {
		
		List<Order> orders = orderRepository.findByCourierStatusChange();
		
		List<Order> updatedOrders = orders.stream().peek(n -> {
			var readedOrder = n.getCourierOrder();
			readedOrder.setReaded(true);
			n.setCourierOrder(readedOrder);
			n.setStatus(Status.COMPLITED);
		}).collect(Collectors.toList());
		
		orderRepository.saveAll(updatedOrders);
	}

}
