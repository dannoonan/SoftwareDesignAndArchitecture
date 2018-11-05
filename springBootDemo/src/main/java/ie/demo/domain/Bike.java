package ie.demo.domain;

import java.io.Serializable;

public class Bike implements Serializable{
	
	private int bikeId;
	private String bikeType;
	private int nodeId;
	private String position;
	private int status;
	private int lastUserId;
	
	public Bike() {
	}
	
	public Bike(String bikeType, int nodeId, String position) {
		this.bikeType = bikeType;
		this.nodeId = nodeId;
		this.position = position;
		this.status = 0;
	}
	
	public int getBikeId() {
		return bikeId;
	}
	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}
	public String getBikeType() {
		return bikeType;
	}
	public void setBikeType(String bikeType) {
		this.bikeType = bikeType;
	}
	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getLastUserId() {
		return lastUserId;
	}
	public void setLastUserId(int lastUserId) {
		this.lastUserId = lastUserId;
	}
}
