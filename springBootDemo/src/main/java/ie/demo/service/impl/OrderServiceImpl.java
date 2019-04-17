package ie.demo.service.impl;

import ie.demo.domain.Bike;
import ie.demo.domain.Order;
import ie.demo.domain.User;
import ie.demo.mapper.BikeMapper;
import ie.demo.mapper.OrderMapper;
import ie.demo.mapper.StudentCardMapper;
import ie.demo.mapper.UserMapper;
import ie.demo.service.BikeState;
import ie.demo.service.CalculatePayment;
import ie.demo.service.OrderService;
import ie.util.StateCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private BikeMapper bikeMapper;
	
	@Autowired
	private StudentCardMapper studentCardMapper;
	
	@Autowired
	@Qualifier("FreePayment")
	private CalculatePayment freePayment;
	
	@Autowired
	@Qualifier("LessHalfHourPayment")
	private CalculatePayment lessHalfHourPayment;
	
	@Autowired
	@Qualifier("LessOneHourPayment")
	private CalculatePayment lessOneHourPayment;
	
	@Autowired
	@Qualifier("GreaterOneHourPayment")
	private CalculatePayment greaterOneHourPayment;
	
	@Autowired
	private BikeContext bikeContext;
	
	@Autowired
	@Qualifier("BikeUnavailableState")
	private  BikeState bikeUnavailableState;
	
	@Autowired
	@Qualifier("BikeAvailableState")
	private  BikeState bikeAvailableState;
	
	@Override
	public int bikeRent(int bikeId, int userId) {
		try {
			User u = userMapper.findUserByUserId(userId);
			int studentCardId = u.getStudentCardId();
			
			float balance = studentCardMapper.getBalance(studentCardId);

			Bike bike = bikeMapper.findBikeById(bikeId);

			if(bike.getStatus() == 1) {
				if(balance <= 0) {
					return StateCode.INSUFFICIENT_BALANCE.getCode();
				} else {

					bikeContext.setBikeState(bikeUnavailableState);
					bikeContext.setBikeId(bikeId);
					bikeContext.setUserId(userId);
					bikeContext.handleStateChange();

					Order order = new Order();
					order.setBikeId(bikeId);
					order.setUserId(userId);
					order.setOrderTime(new java.util.Date());
					order.setMoneyConsumed(0);
					order.setPaidStatus(0);

					int result = orderMapper.placeOrder(order);

					if(result == StateCode.SUCCESS.getCode()) {
						return order.getOrderId();
					} else {
						return StateCode.FAIL.getCode();
					}
				}
			} else {
				return StateCode.NOT_AVAILABLE.getCode();
			}

		} catch (NullPointerException e) {
			return StateCode.NOT_EXISTS.getCode();
		}
	}

	@Override
	public float calculateDeductions(float minutes) {
		freePayment.setNextPaymentMethod(lessHalfHourPayment);
		lessHalfHourPayment.setNextPaymentMethod(lessOneHourPayment);
		lessOneHourPayment.setNextPaymentMethod(greaterOneHourPayment);
		
		freePayment.calculatePaidAmount(minutes);
		return freePayment.getFinalPay();
	}

	@Override
	public int bikeReturn(int orderId, Integer latitude, Integer longitude, int studentCardId, Integer nodeId) {
		Date now = new Date();
		Order currentOrder = orderMapper.getOrder(orderId);
		Date timePlaced = currentOrder.getOrderTime();
		float minutes = ((now.getTime()/60000) - (timePlaced.getTime()/60000));
		float amountPaid = calculateDeductions(minutes);
		float balance = studentCardMapper.getBalance(studentCardId);
		balance = balance - amountPaid;
		if(balance < 0) {
			return StateCode.INSUFFICIENT_BALANCE.getCode();
		} else {
			
			studentCardMapper.setDeduction(amountPaid, studentCardId);
			
			Order order = new Order();
			order.setMoneyConsumed(amountPaid);
			order.setPaidStatus(1);
			order.setOrderId(orderId);
			
			int result = orderMapper.setOrder(order);
			
			if(result == StateCode.SUCCESS.getCode()) {
				int bikeId = orderMapper.getBikeId(orderId);
				String position;

				if(latitude == null && longitude == null) {
					position = null;
				} else {
					position = "" + latitude + "," + longitude;
				}

				if(nodeId != null) {
					bikeContext.setBikeState(bikeAvailableState);
				} else {
					bikeContext.setBikeState(bikeUnavailableState);
				}

				bikeContext.setBikeId(bikeId);
				bikeContext.setNode(nodeId);
				bikeContext.setPosition(position);
				bikeContext.handleStateChange();
				
				return StateCode.SUCCESS.getCode();

			} else  {
				return StateCode.FAIL.getCode();
			}
		}
	}

}
