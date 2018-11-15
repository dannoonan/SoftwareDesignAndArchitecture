package ie.demo.service.impl;

import ie.demo.domain.Bike;
import ie.demo.domain.Order;
import ie.demo.domain.User;
import ie.demo.mapper.BikeMapper;
import ie.demo.mapper.OrderMapper;
import ie.demo.mapper.StudentCardMapper;
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
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private StudentCardMapper studentCardMapper;
	
	@Autowired
	private BikeMapper bikeMapper;

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
	
	@Override
	public int bikeRent(int bikeId, int userId) {
		try {
		User u = userMapper.findUserByUserId(userId);
		int studentCardId = u.getStudentCardId();
		float balance = studentCardMapper.getBalance(studentCardId);
		if(balance <= 0) {
			return -1;
		} else {
			bikeMapper.setStatus(0, bikeId);
			Order order = new Order();
			order.setBikeId(bikeId);
			order.setUserId(userId);
			order.setOrderTime(new java.util.Date());
			order.setMoneyConsumed(0);
			order.setPaidStatus(0);
			int result = orderMapper.placeOrder(order);
			if(result == 1) {
				 return order.getOrderId();
			} else {
				return 0;
			}
		}
		} catch (NullPointerException e) {
			return -2;
		}
	}

	@Override
	public float calculateDeductions(float minutes) {
		if(minutes <= 1) {
			return 0;
		} else if(minutes > 1 && minutes <= 30) {
			return 2;
		} else if(minutes > 30 && minutes <= 60) {
			return 5;
		} else {
			return (float) (minutes * 0.1);
		}
	}

	@Override
	public int bikeReturn(int orderId, float latitude, float longitude, float amountPaid, int studentCardId, int nodeId) {
		float balance = studentCardMapper.getBalance(studentCardId);
		balance = balance - amountPaid;
		if(balance < 0) {
			return -1;
		} else {
			studentCardMapper.setDeduction(amountPaid, studentCardId);
			int bikeId = orderMapper.setOrder(amountPaid, 1, orderId);
			String position = "" + latitude + "," + longitude;
			bikeMapper.setReturnStatus(1, bikeId, position, nodeId);
			return 1;
		}
	}

}
