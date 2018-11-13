package ie.demo.controller;

import ie.demo.service.OrderService;
import ie.response.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value= "/order", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public MsgResponse insertUser(@RequestParam(value = "userName") String userName,
								  @RequestParam(value = "bikeId") int bikeId,
								  @RequestParam(value = "amountPaid") int amountPaid) {

		int result = orderService.placeOrder(userName, bikeId, amountPaid);
		if(result == 200) {
			return MsgResponse.success();
		}
		else if(result == 404) {
			return MsgResponse.fail(result).add("error", "Bike or User does not exist.");
		}
		else {
			return MsgResponse.fail(result).add("error", "Failed to place order.");
		}
	}
}
