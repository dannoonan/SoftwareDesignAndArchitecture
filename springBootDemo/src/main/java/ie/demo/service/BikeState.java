package ie.demo.service;

public interface BikeState {
	void handleState(int bikeId, int userId, Integer nodeId, String position);
}
