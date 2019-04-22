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
import ie.util.NormalStrategy;
import ie.util.PremiumStrategy;
import ie.util.StateCode;
import ie.util.BillingStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

	public final int STATUS_PAID = 1;
	public final int STATUS_UNPAID = 0;
	
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
					order.setPaidStatus(STATUS_UNPAID);

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
	public int bikeReturn(int userId, int bikeId, Double latitude, Double longitude, int studentCardId, Integer nodeId) {
		BillingStrategy strategy = decideStrategy(userId);
		Date now = new Date();
		Order currentOrder = orderMapper.getMostRecentOrder(userId, bikeId);

		if(currentOrder == null) return StateCode.NOT_EXISTS.getCode();

		Date timePlaced = currentOrder.getOrderTime();
		float minutes = (float)((now.getTime()/60000.0) - (timePlaced.getTime()/60000.0));
		float amountPaid = strategy.getActPrice(calculateDeductions(minutes));
		float balance = studentCardMapper.getBalance(studentCardId);
		balance = balance - amountPaid;

		if(balance < 0) {
			return StateCode.INSUFFICIENT_BALANCE.getCode();
		} else {
			
			studentCardMapper.setDeduction(amountPaid, studentCardId);
			
			Order order = new Order();
			order.setMoneyConsumed(amountPaid);
			order.setPaidStatus(STATUS_PAID);
			order.setOrderId(currentOrder.getOrderId());
			
			int result = orderMapper.setOrder(order);
			
			if(result == StateCode.SUCCESS.getCode()) {
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

	private BillingStrategy decideStrategy(int userId) {
		int userType = userMapper.findUserType(userId);
		if(userType == 2) {
			return new PremiumStrategy();
		} else {
			return new NormalStrategy();
		}
	}

}
