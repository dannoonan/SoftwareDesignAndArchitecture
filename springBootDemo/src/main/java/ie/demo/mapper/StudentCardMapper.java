package ie.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentCardMapper {
	float getBalance(int studentCardId);
	int cardExists(int studentCardId);
	int createCard(int studentCardId);
	int setDeduction(float amountPaid, int studentCardId);
}