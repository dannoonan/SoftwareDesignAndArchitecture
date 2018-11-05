package ie.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ie.demo.domain.Bike;
import ie.demo.domain.Node;
import ie.demo.service.BikeFactory;
import ie.demo.service.BikeService;
import ie.demo.service.NodeService;
import ie.response.MsgResponse;

@RestController
public class BikeController {
	
	@Autowired
	BikeService bikeService;
	
	@Autowired
	NodeService nodeService;
	
	@Autowired
	BikeFactory bikeFactory;
	
	@RequestMapping(value= "/node", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public MsgResponse getNode() {
		List<Node> nodes = nodeService.findNode();
		if(nodes == null) {
			return MsgResponse.fail().add("error", "no node exsits");
		} else {
			return MsgResponse.success().add("nodes", nodes);
		}
	}
	
	@RequestMapping(value= "/node/{id}", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public MsgResponse getBikesByNode(@PathVariable int id) {
		List<Bike> bikes = bikeService.findBikeByNodes(id);
		if(bikes == null) {
			return MsgResponse.success().add("warning", "sorry, no bike exsits in this node");
		} else {
			return MsgResponse.success().add("bikes", bikes);
		}
	}
	
	@RequestMapping(value= "/bike", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public MsgResponse insertBike(@RequestParam(value = "bikeType")String bikeType,
			  @RequestParam(value = "nodeId")int nodeId,
			  @RequestParam(value = "position") String position) {
		Bike bike = bikeFactory.createBike(bikeType, nodeId, position);
		int result = bikeService.createBike(bike);
		if(result > 0) {
			return MsgResponse.success();
		} else {
			return MsgResponse.fail().add("error", "fail to insert this bike");
		}
	}
	
	@RequestMapping(value= "/bike/{id}", method=RequestMethod.PUT, produces="application/json;charset=UTF-8")
	public MsgResponse setBike(@PathVariable int id, @RequestParam(value = "status")int status) {
		bikeService.setStatus(status, id);
		return MsgResponse.success();
	}
	
}
