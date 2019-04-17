package ie.demo.controller;

import ie.demo.service.OrderService;
import ie.util.MsgResponse;
import ie.util.StateCode;

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
	
	@RequestMapping(value= "/rent", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public MsgResponse rentBike(@RequestParam int id, @RequestParam(value = "userId")int userId) {
		int result = orderService.bikeRent(id, userId);
		if(result == StateCode.INSUFFICIENT_BALANCE.getCode()) {
			return MsgResponse.fail(StateCode.ERROR.getCode()).add("error", "balance is not enough");
		} else if(result == StateCode.FAIL.getCode()){
			return MsgResponse.fail(StateCode.ERROR.getCode()).add("error", "Failed to place order.");
		} else if(result == StateCode.NOT_EXISTS.getCode()) {
			return MsgResponse.fail(StateCode.ERROR.getCode()).add("error", "Bike or User does not exist.");
		} else if(result == StateCode.NOT_AVAILABLE.getCode()) {
			return MsgResponse.fail(StateCode.ERROR.getCode()).add("error", "Bike is not available to rent.");
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
			@RequestParam(value = "latitude", required = false) Integer latitude,
			@RequestParam(value = "longitude", required = false) Integer longitude,
			@RequestParam(value = "studentCardId") int studentCardId,
			@RequestParam(value = "nodeId", required = false) Integer nodeId) {
		if((latitude == null && longitude == null) && nodeId == null) {
			return MsgResponse.fail(StateCode.ERROR.getCode()).add("error", "invalid location");
		} else {
			int result = orderService.bikeReturn(orderId, latitude, longitude, studentCardId, nodeId);
			if(result == StateCode.INSUFFICIENT_BALANCE.getCode()) {
				return MsgResponse.fail(StateCode.ERROR.getCode()).add("error", "balance is not enough");
			} else if(result == StateCode.FAIL.getCode()) {
				return MsgResponse.fail(StateCode.ERROR.getCode()).add("error", "try again");
			} else {
				return MsgResponse.success();
			}
		}
	}
	
	
}
