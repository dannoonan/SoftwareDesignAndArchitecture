package ie.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ie.demo.mapper.BikeMapper;
import ie.demo.service.BikeState;

@Service
@Component("BikeUnavailableState")
public class BikeUnavailableState implements BikeState {
	
	@Autowired
	private BikeMapper bikeMapper;

	@Override
	public void handleState(int bikeId, int userId, Integer nodeId, String position) {
		bikeMapper.setRentStatus(0, bikeId, userId, nodeId, position);
	}

}
