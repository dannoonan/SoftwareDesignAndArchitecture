package ie.demo.service;

public interface OrderService {

	int bikeRent(int bikeId, int userId);
	int bikeReturn(int userId, Double latitude, Double longitude, int studentCardId, Integer nodeId);
	float calculateDeductions(float minutes);
}
