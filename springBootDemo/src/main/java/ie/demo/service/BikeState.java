package ie.demo.service;

public interface BikeState {
	public void handleState(int bikeId, int userId, int nodeId, String position);
}
