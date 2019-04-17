package ie.demo.service.impl;

import ie.demo.domain.Collection;
import ie.demo.service.CollectionFactory;
import org.springframework.stereotype.Service;

@Service
public class ConcreteCollectionFactory implements CollectionFactory {

	@Override
	public Collection createCollection(int driverId, int nodeId) {
		return new Collection(driverId, nodeId);
	}

}
