package ie.demo.service;
import ie.demo.domain.Collection;

public interface CollectionFactory {
	Collection createCollection(int driverId, int nodeId);
}
