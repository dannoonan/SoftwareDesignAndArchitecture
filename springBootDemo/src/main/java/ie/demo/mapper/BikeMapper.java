package ie.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ie.demo.domain.Bike;

@Mapper
public interface BikeMapper {
	List<Bike> findBikeByNodes(int id);
	Bike findBikeById(int id);
	int createBike(Bike bike);
	int setStatus(int status, int bikeId);
	int setReturnStatus(int status, int bikeId, String position, int nodeId);
}
