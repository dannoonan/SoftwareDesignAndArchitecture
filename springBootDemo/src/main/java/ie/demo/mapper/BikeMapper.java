package ie.demo.mapper;

import java.util.List;

import ie.demo.domain.Collection;
import org.apache.ibatis.annotations.Mapper;

import ie.demo.domain.Bike;
import org.springframework.dao.DataIntegrityViolationException;

@Mapper
public interface BikeMapper {
	List<Bike> findBikeByNodes(int id);
	Bike findBikeById(int id);
	List<Bike> findAllBikes();
	int createBike(Bike bike);
	int createCollection(Collection collection);
	int createCollectionBikes(int bikeId, int collectionId) throws DataIntegrityViolationException;
	int setStatus(int status, int bikeId);
	int setRentStatus(int status, int bikeId, int userId, Integer nodeId, String position);
	int setReturnStatus(int status, int bikeId, String position, Integer nodeId);
}
