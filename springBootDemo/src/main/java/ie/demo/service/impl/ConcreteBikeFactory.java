package ie.demo.service.impl;

import org.springframework.stereotype.Service;

import ie.demo.domain.Bike;
import ie.demo.service.BikeFactory;
@Service
public class ConcreteBikeFactory implements BikeFactory {

	@Override
	public Bike createBike(String bikeType, int nodeId, String position) {
		return new Bike(bikeType, nodeId, position);
	}

}
