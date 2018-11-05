package ie.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.demo.domain.Bike;
import ie.demo.mapper.BikeMapper;
import ie.demo.service.BikeService;

@Service
public class BikeServiceImpl implements BikeService {
	
	@Autowired
	private BikeMapper bikeMapper;
	
	@Override
	public List<Bike> findBikeByNodes(int id) {
		List<Bike> bikes = bikeMapper.findBikeByNodes(id);
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

}
