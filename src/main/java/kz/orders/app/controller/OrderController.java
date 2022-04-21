package kz.orders.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kz.orders.app.service.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {
	

	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("orders", orderService.getAll());				
		return "order-list";
	}
	
	@PostMapping("/{order_id}")
	public String sendOrder(@PathVariable(name = "order_id") Long orderId) {
		orderService.changeOrderStatus(orderId);
		return "redirect:/orders";
	}

}
