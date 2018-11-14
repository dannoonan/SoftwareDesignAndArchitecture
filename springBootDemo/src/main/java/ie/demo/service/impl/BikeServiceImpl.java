package ie.demo.service.impl;

import java.util.List;

import org.eclipse.jetty.server.Authentication.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.demo.domain.Bike;
import ie.demo.domain.Reports;
import ie.demo.mapper.BikeMapper;
import ie.demo.mapper.ReportMapper;
import ie.demo.mapper.StudentCardMapper;
import ie.demo.mapper.UserMapper;
import ie.demo.service.BikeService;

@Service
public class BikeServiceImpl implements BikeService {
	
	@Autowired
	private BikeMapper bikeMapper;
	
	@Autowired
	private ReportMapper reportMapper;
	
	@Autowired
	private StudentCardMapper studentCardMapper;
	
	@Autowired
	private UserMapper userMapper;

	
	@Override
	public List<Bike> findBikeByNodes(int id) {
		List<Bike> bikes = bikeMapper.findBikeByNodes(id);
		return bikes;
	}

	@Override
	public Bike findBikeById(int id) {
		Bike bike = bikeMapper.findBikeById(id);
		return bike;
	}

	@Override
	public int createBike(Bike bike) {
		int result = bikeMapper.createBike(bike);
		return result;
	}

	@Override
	public int setStatus(int status, int bikeId) {
		int result = bikeMapper.setStatus(status, bikeId);
		return result;
	}

	@Override
	public int reportBike(int bikeId, int userId, String reportText) {
		int result = reportMapper.reportBike(bikeId, userId, reportText);
		if(result > 0) {
			bikeMapper.setStatus(0, bikeId);
		}
		return result;
	}

	@Override
	public List<Reports> getReports() {
		List<Reports> reports = reportMapper.getReports();
		return reports;
	}

	@Override
	public int bikeRent(int id) {
		
		return 0;
	}

}
