package ie.demo.service.impl;

import ie.demo.domain.Bike;
import ie.demo.domain.Order;
import ie.demo.domain.User;
import ie.demo.mapper.BikeMapper;
import ie.demo.mapper.OrderMapper;
import ie.demo.mapper.UserMapper;
import ie.demo.service.BikeService;
import ie.demo.service.OrderService;
import ie.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private BikeService bikeService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderMapper orderMapper;

	@Override
	public int placeOrder(String userName, int bikeId, int amountPaid) {
		int result;
		try {
			User user = userService.findUserByUserName(userName);
			Bike bike = bikeService.findBikeById(bikeId);
			Order order = new Order();
			order.setBikeId(bike.getBikeId());
			order.setUserId(user.getUserId());
			order.setMoneyConsumed(amountPaid); // need to take from student card balance too
			order.setOrderTime(new java.util.Date());
			if(amountPaid != 0) {
				order.setPaidStatus(1);
			} else {
				order.setPaidStatus(0);
			}
			result = orderMapper.placeOrder(order);
			if(result == 1) {
				result = 200;
				bikeService.setStatus(1, bikeId);
			} else {
				result = 400;
			}
		} catch (NullPointerException e) {
			result = 404;
		}
		return result;
	}

}
