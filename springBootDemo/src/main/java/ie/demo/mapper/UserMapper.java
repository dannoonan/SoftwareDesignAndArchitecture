package ie.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ie.demo.domain.User;
@Mapper
public interface UserMapper {

	public int register(User u);
	
	public int userExists(String username);
	
}
