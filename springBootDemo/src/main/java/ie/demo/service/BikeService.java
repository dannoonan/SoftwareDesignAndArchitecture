package ie.demo.service;

import java.util.List;

import ie.demo.domain.Bike;
import ie.demo.domain.Reports;

public interface BikeService {

	List<Bike> findBikeByNodes(int id);
	Bike findBikeById(int id);
	int createBike(Bike bike);
	int setStatus(int status, int bikeId);
	int reportBike(int bikeId, int UserId, String ReportText);
	List<Reports> getReports();
	int bikeRent(int id);
}
