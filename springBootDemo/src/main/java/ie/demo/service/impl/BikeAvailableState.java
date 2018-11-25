package ie.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ie.demo.mapper.BikeMapper;
import ie.demo.service.BikeState;

@Service
@Component("BikeAvailableState")
public class BikeAvailableState implements BikeState {
	
	@Autowired
	BikeMapper bikeMapper;
	
	@Override
	public void handleState(int bikeId, int userId, int nodeId, String position) {
		bikeMapper.setReturnStatus(1, bikeId, position, nodeId);
	}

}
