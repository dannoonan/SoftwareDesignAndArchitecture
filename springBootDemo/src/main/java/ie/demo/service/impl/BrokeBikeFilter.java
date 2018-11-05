package ie.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ie.demo.domain.Bike;
import ie.demo.service.BikeFilter;
@Service
public class BrokeBikeFilter implements BikeFilter {

	@Override
	public List<Bike> bikeFilter(List<Bike> bikes) {
		
		List<Bike> brokeBikes = new ArrayList<Bike>();
		for(Bike bike: bikes) {
			if(bike.getStatus() == 2) {
				brokeBikes.add(bike);
			}
		}
		return brokeBikes;
	}

}
