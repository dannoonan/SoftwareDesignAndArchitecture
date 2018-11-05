package ie.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ie.demo.domain.Bike;

@Mapper
public interface BikeMapper {
	public List<Bike> findBikeByNodes(int id);
	public int createBike(Bike bike);
	public int setStatus(int status, int bikeId);
}
