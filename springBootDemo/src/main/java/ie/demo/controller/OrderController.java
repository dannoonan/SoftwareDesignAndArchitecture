package ie.demo.controller;

import ie.demo.service.BikeService;
import ie.demo.service.OrderService;
import ie.response.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value= "/rent/{id}", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public MsgResponse rentBike(@PathVariable int id, @RequestParam(value = "userId")int userId) {
		int result = orderService.bikeRent(id, userId);
		if(result == -1) {
			return MsgResponse.fail(404).add("error", "balance is not enough");
		} else if(result == 0){
			return MsgResponse.fail(404).add("error", "Failed to place order.");
		} else if(result == -2) {
			return MsgResponse.fail(result).add("error", "Bike or User does not exist.");
		} else {
			return MsgResponse.success().add("orderId", result);
		}
	}
	
	@RequestMapping(value= "/money/{minutes}", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public MsgResponse calculateDeductions(@PathVariable float minutes) {
		float amountPaid = orderService.calculateDeductions(minutes);
		return MsgResponse.success().add("amountPaid", amountPaid);
	}
	
	@RequestMapping(value= "/return", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public MsgResponse returnBike(@RequestParam(value = "orderId") int orderId, 
			@RequestParam(value = "latitude")float latitude, 
			@RequestParam(value = "longitude")float longitude,
			@RequestParam(value = "amountPaid") float amountPaid,
			@RequestParam(value = "studentCardId") int studentCardId,
			@RequestParam(value = "nodeId") int nodeId) {
		int result = orderService.bikeReturn(orderId, latitude, longitude, amountPaid, studentCardId, nodeId);
		if(result == -1) {
			return MsgResponse.fail(404).add("error", "balance is not enough");
		} else {
			return MsgResponse.success();
		}
	}
	
	
}
