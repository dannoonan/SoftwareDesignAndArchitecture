package ie.demo.service;

import java.util.List;

import ie.demo.domain.Bike;

public interface BikeService {

	List<Bike> findBikeByNodes(int id);
	Bike findBikeById(int id);
	int createBike(Bike bike);
	int setStatus(int status, int bikeId);
}
