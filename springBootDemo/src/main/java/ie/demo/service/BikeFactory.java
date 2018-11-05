package ie.demo.service;
import ie.demo.domain.Bike;
public interface BikeFactory {
	public Bike createBike(String bikeType, int nodeId, String position);
}
