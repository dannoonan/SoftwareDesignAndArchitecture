package ie.demo.service;

import java.util.List;

import ie.demo.domain.Bike;
import ie.demo.domain.Reports;

public interface BikeService {

	List<Bike> findBikeByNodes(int id);
	Bike findBikeById(int id);
	List<Bike> findAllBikes();
	int createBike(Bike bike);
	int setStatus(int status, int bikeId);
	int reportBike(int bikeId, int UserId, String ReportText);
	List<Reports> getReports();
	int scheduleCollection(List<Integer> bikeIds, int nodeId, int driverId);
}
