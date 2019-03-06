package ie.demo.domain;

import java.io.Serializable;

public class StandardAdmin extends AdminUser implements Serializable {

	public StandardAdmin(String username, String password, Integer studentCardId, int userTypeId, String email) {
		super(username, password, studentCardId, userTypeId, email);

	}
	
}
