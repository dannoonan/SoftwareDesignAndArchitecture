package ie.demo.domain;

public interface User {
	int getUserId();
	void setUserId(int userId);
	String getUsername();
	void setUsername(String username);
	String getPassword();
	void setPassword(String password);
	int getUserTypeId();
	void setUserTypeId(int userTypeId);
	String getEmail();
	void setEmail(String email);
	Integer getStudentCardId();
	void setStudentCardId(Integer studentCardId);
	boolean getIsBanned();
	void setIsBanned(boolean isBanned);
}
