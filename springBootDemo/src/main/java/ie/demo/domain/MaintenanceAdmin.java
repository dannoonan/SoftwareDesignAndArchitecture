package ie.demo.domain;

import java.io.Serializable;

public class MaintenanceAdmin extends AdminUser implements Serializable {

	public MaintenanceAdmin(String username, String password, Integer studentCardId, int userTypeId, String email) {
		super(username, password, studentCardId, userTypeId, email);

	}
	
}
