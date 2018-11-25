package ie.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.demo.domain.Bike;
import ie.demo.service.BikeState;

@Service
public class BikeContext {
	private int bikeId;
	private BikeState bikeState;
	private int nodeId;
	private String position;
	private int userId;
	
	public void setBikeState(BikeState bikeState) {
		this.bikeState = bikeState;
	}
	
	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}
	
	public void setNode(int nodeId) {
		this.nodeId = nodeId;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void handleStateChange() {
		bikeState.handleState(bikeId, userId, nodeId, position);
	}
	
}
