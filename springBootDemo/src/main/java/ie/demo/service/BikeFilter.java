package ie.demo.service;

import java.util.List;

import ie.demo.domain.Bike;

public interface BikeFilter {
	public List<Bike> bikeFilter(List<Bike> bikes);
}
