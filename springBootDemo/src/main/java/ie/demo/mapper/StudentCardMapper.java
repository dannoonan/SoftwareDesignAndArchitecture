package ie.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentCardMapper {
	float getBalance(int studentCardId);
}
