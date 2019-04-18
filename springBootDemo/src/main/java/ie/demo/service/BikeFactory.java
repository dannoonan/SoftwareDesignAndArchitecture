package ie.demo.service;
import ie.demo.domain.Bike;
public interface BikeFactory {
	Bike createBike(String bikeType, int nodeId);
}
