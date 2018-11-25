package ie.demo.service;

public interface OrderService {

	//int placeOrder(String userName, int bikeId, int amountPaid);
	int bikeRent(int bikeId, int userId);
	int bikeReturn(int orderId, float latitude, float longitude, float amountPaid, int studentCardId, int nodeId);
	float calculateDeductions(float minutes);
}
