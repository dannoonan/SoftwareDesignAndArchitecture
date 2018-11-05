package ie.demo.service;

import java.util.List;

import ie.demo.domain.Bike;

public interface BikeService {

	public List<Bike> findBikeByNodes(int id);
	public int createBike(Bike bike);
	public int setStatus(int status, int bikeId);
}
