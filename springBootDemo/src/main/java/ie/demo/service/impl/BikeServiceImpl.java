package ie.demo.service.impl;

import java.util.List;

import com.mysql.jdbc.exceptions.MySQLDataException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import ie.demo.domain.Collection;
import ie.demo.service.BikeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import ie.demo.domain.Bike;
import ie.demo.domain.Reports;
import ie.demo.mapper.BikeMapper;
import ie.demo.mapper.UserMapper;
import ie.demo.mapper.ReportMapper;
import ie.demo.service.BikeService;
import ie.util.StateCode;

@Service
public class BikeServiceImpl implements BikeService {
	
	@Autowired
	private BikeMapper bikeMapper;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ReportMapper reportMapper;

	@Autowired
	private BikeFactory bikeFactory;
	
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
	public List<Bike> findAllBikes() {
		List<Bike> bikes = bikeMapper.findAllBikes();
		return bikes;
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
		if(result == StateCode.SUCCESS.getCode()) {
			bikeMapper.setStatus(0, bikeId);
		}
		return StateCode.SUCCESS.getCode();
	}

	@Override
	public List<Reports> getReports() {
		List<Reports> reports = reportMapper.getReports();
		return reports;
	}

	@Override
	public int scheduleCollection(Integer[] bikeIds, int nodeId, int driverId) {

		int result;

		if(userMapper.findUserType(driverId) != 4) {
			result = 400;
		} else {

			try {
				Collection collection = new Collection();
				collection.setDriverId(driverId);
				collection.setNodeId(nodeId);
				result = bikeMapper.createCollection(collection);
				if(result == 1) {
					for(int i = 0; i < bikeIds.length; i++) {
						bikeMapper.createCollectionBikes(bikeIds[i], collection.getCollectionId());
						bikeMapper.setStatus(0, bikeIds[i]);
					}
				}
			} catch (DataIntegrityViolationException e) {
				result = 404;
			}
		}
		return result;
	}
}
