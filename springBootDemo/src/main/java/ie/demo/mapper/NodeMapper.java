package ie.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ie.demo.domain.Node;

@Mapper
public interface NodeMapper {
	
	public List<Node> findNode();
	
}
