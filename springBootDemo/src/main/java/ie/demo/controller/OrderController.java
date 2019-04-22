package ie.demo.controller;

import ie.demo.interceptorframework.Framework;
import ie.demo.interceptorframework.RentContext;
import ie.demo.interceptorframework.ReturnContext;
import ie.demo.service.BikeService;
import ie.demo.service.NodeService;
import ie.demo.service.OrderService;
import ie.util.MsgResponse;
import ie.util.StateCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private BikeService bikeService;

	@Autowired
	private NodeService nodeService;
	
	@RequestMapping(value= "/rent", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public MsgResponse rentBike(@RequestParam int id, @RequestParam(value = "userId")int userId) {
		Framework framework = Framework.getInstance();
		framework.handleRequest(new RentContext
				.Builder()
				.setBikeId(id)
				.setUserId(userId)
				.setTime(new Date())
				.build(framework));

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
	public MsgResponse returnBike(@RequestParam(value = "userId") int userId,
								  @RequestParam(value = "bikeId", required = false) int bikeId,
								  @RequestParam(value = "latitude", required = false) Double latitude,
								  @RequestParam(value = "studentCardId") int studentCardId,
								  @RequestParam(value = "longitude", required = false) Double longitude,
								  @RequestParam(value = "nodeId", required = false) Integer nodeId) {

		Framework framework = Framework.getInstance();
		framework.supplyNodeService(nodeService);
		framework.supplyBikeService(bikeService);

		framework.handleRequest(new ReturnContext.Builder()
				.setUserId(userId)
				.setBikeId(bikeId)
				.setLatitude(latitude)
				.setLongitude(longitude)
				.setStudentCardId(studentCardId)
				.setNodeId(nodeId)
				.build(framework));

		if((latitude == null && longitude == null) && nodeId == null) {
			return MsgResponse.fail(StateCode.ERROR.getCode()).add("error", "invalid location");
		} else {
			int result = orderService.bikeReturn(userId, bikeId, latitude, longitude, studentCardId, nodeId);
			if(result == StateCode.NOT_EXISTS.getCode()){
				return MsgResponse.fail(StateCode.NOT_EXISTS.getCode()).add("error", "cannot return vehicle - none rented");
			}
			if(result == StateCode.INSUFFICIENT_BALANCE.getCode()) {
				return MsgResponse.fail(StateCode.BAD_REQUEST.getCode()).add("error", "balance is not enough");
			} else if(result == StateCode.FAIL.getCode()) {
				return MsgResponse.fail(StateCode.ERROR.getCode()).add("error", "try again");
			} else {
				return MsgResponse.success();
			}
		}
	}
	
	
}
