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
import ie.demo.domain.Reports;
import ie.demo.service.BikeFactory;
import ie.demo.service.BikeService;
import ie.demo.service.NodeService;
import ie.util.MsgResponse;
import ie.util.StateCode;

@RestController
public class BikeController {
	
	@Autowired
	private BikeService bikeService;
	
	@Autowired
	private NodeService nodeService;
	
	@Autowired
	private BikeFactory bikeFactory;
	
	@RequestMapping(value= "/node", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public MsgResponse getNode() {
		List<Node> nodes = nodeService.findNode();
		if(nodes == null) {
			return MsgResponse.fail(404).add("error", "No nodes found.");
		} else {
			return MsgResponse.success().add("nodes", nodes);
		}
	}
	
	@RequestMapping(value= "/node/{id}", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public MsgResponse getBikesByNode(@PathVariable int id) {
		List<Bike> bikes = bikeService.findBikeByNodes(id);
		if(bikes == null) {
			return MsgResponse.success().add("warning", "Empty node.");
		} else {
			return MsgResponse.success().add("bikes", bikes);
		}
	}

	@RequestMapping(value= "/bike", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public MsgResponse getAllBikes() {
		List<Bike> bikes = bikeService.findAllBikes();
		if(bikes == null) {
			return MsgResponse.success().add("warning", "Empty node.");
		} else {
			return MsgResponse.success().add("bikes", bikes);
		}
	}
	
	@RequestMapping(value= "/bike", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public MsgResponse insertBike(@RequestParam(value = "bikeType") String bikeType,
			  		@RequestParam(value = "nodeId") int nodeId) {
		Bike bike = bikeFactory.createBike(bikeType, nodeId);
		int result = bikeService.createBike(bike);
		if(result == StateCode.SUCCESS.getCode()) {
			return MsgResponse.success();
		} else {
			return MsgResponse.fail(result).add("error", "fail to insert this bike");
		}
	}
	
	@RequestMapping(value= "/report/{id}", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public MsgResponse reportBike(@PathVariable int id,
					@RequestParam(value = "userId") int userId,
					@RequestParam(value = "reportText") String reportText) {
		int result = bikeService.reportBike(id, userId, reportText);
		if(result == StateCode.SUCCESS.getCode()) {
			return MsgResponse.success();
		} else {
			return MsgResponse.fail(result);
		}
	}
	
	@RequestMapping(value= "/reports", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public MsgResponse getReportedBikes() {
		List<Reports> reports = bikeService.getReports();
		if(reports == null) {
			return MsgResponse.success().add("warning", "Empty reports.");
		} else {
			return MsgResponse.success().add("reports", reports);
		}
	}

	@RequestMapping(value= "/collect", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public MsgResponse collectBike(@RequestParam(value = "bikeIds") List<Integer> bikeIds,
								   @RequestParam(value = "nodeId") int nodeId,
								   @RequestParam(value = "driverId") int driverId) {
		int result = bikeService.scheduleCollection(bikeIds, nodeId, driverId);
		if(result == StateCode.SUCCESS.getCode()) {
			return MsgResponse.success();
		} else if(result == StateCode.USER_NOT_FOUND.getCode()) {
			return MsgResponse.fail(result).add("bad request", "Driver ID provided is incorrect.");
		} else if(result == StateCode.ERROR.getCode()) {
			return MsgResponse.fail(result).add("bad request", "One of the provided Bikes was either not found or has already been scheduled for collection.");
		} else {
			return MsgResponse.fail(result);
		}
	}
}
