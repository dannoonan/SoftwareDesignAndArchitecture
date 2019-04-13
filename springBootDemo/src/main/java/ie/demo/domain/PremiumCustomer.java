package ie.demo.domain;

import java.io.Serializable;

public class PremiumCustomer extends CustomerUser implements Serializable {

	public PremiumCustomer(String username, String password, Integer studentCardId, int userTypeId, String email) {
		super(username, password, studentCardId, userTypeId, email);
	}

	public PremiumCustomer(Long userId, Long studentCardId, Long userTypeId,  String username, String password, String email, boolean isBanned) {
		super(userId, studentCardId, userTypeId, username, password, email, isBanned);
	}

}