package ie.demo.mapper;

import ie.demo.domain.Order;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

	int placeOrder(Order order);
	int setOrder(float amountPaid, int isPaid, int orderId);
}
