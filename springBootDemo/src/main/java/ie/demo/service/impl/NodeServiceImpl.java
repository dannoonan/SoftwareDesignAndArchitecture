package ie.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.demo.domain.Node;
import ie.demo.mapper.NodeMapper;
import ie.demo.service.NodeService;
@Service
public class NodeServiceImpl implements NodeService {
	
	@Autowired
	private NodeMapper nodeMapper;

	@Override
	public List<Node> findNode() {
		List<Node> nodeList = (List<Node>) nodeMapper.findNode();
		return nodeList;
	}
}
