package ie.demo.service.impl;

import java.util.List;

import ie.demo.domain.Collection;
import ie.demo.service.CollectionFactory;
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
	private CollectionFactory collectionFactory;
	
	@Override
	public List<Bike> findBikeByNodes(int id) {
		return bikeMapper.findBikeByNodes(id);
	}

	@Override
	public Bike findBikeById(int id) {
		return bikeMapper.findBikeById(id);
	}

	@Override
	public List<Bike> findAllBikes() {
		return bikeMapper.findAllBikes();
	}

	@Override
	public int createBike(Bike bike) {
		return bikeMapper.createBike(bike);
	}

	@Override
	public int setStatus(int status, int bikeId) {
		return bikeMapper.setStatus(status, bikeId);
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
		return reportMapper.getReports();
	}

	@Override
	public int scheduleCollection(List<Integer> bikeIds, int nodeId, int driverId) {

		int result;

		if(userMapper.findUserType(driverId) != 4) {
			result = StateCode.USER_NOT_FOUND.getCode();
		} else {

			try {
				Collection collection = collectionFactory.createCollection(driverId, nodeId);
				result = bikeMapper.createCollection(collection);
				if(result == StateCode.SUCCESS.getCode()) {
					for(Integer bikeId: bikeIds) {
						bikeMapper.createCollectionBikes(bikeId, collection.getCollectionId());
						bikeMapper.setStatus(0, bikeId);
					}
				}
			} catch (DataIntegrityViolationException e) {
				result = StateCode.ERROR.getCode();
			}
		}
		return result;
	}
}
