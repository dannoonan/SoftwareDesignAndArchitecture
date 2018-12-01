package ie.demo.mapper;

import ie.demo.domain.StudentCard;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentCardMapper {
	float getBalance(int studentCardId);
	int cardExists(int studentCardId);
	int createCard(StudentCard studentCard);
	int setDeduction(float amountPaid, int studentCardId);
}
