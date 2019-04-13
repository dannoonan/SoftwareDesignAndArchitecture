package ie.demo.domain;

public abstract class CustomerUser implements User {


	private int userId;
	private int userTypeId;
	private Integer studentCardId;
	private String userName;
	private String password;
	private String email;
	private boolean isBanned;

	CustomerUser(String username, String password, Integer studentCardId, int userTypeId, String email) {
		this.userName = username;
		this.password = password;
		this.userTypeId = userTypeId;
		this.studentCardId = studentCardId;
		this.email = email;
	}

	CustomerUser(Long userId, Long studentCardId, Long userTypeId,  String username, String password, String email, boolean isBanned) {
		this.userId = Math.toIntExact(userId);
		this.userName = username;
		this.password = password;
		this.userTypeId = Math.toIntExact(userTypeId);
		this.studentCardId = studentCardId != null ? studentCardId.intValue() : null;
		this.email = email;
		this.isBanned = isBanned;
	}

	@Override
	public int getUserId() {
		return userId;
	}
	@Override
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String getUsername() {
		return userName;
	}
	@Override
	public void setUsername(String username) {
		this.userName = username;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int getUserTypeId() {
		return userTypeId;
	}
	@Override
	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}
	@Override
	public String getEmail() {
		return email;
	}
	@Override
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public Integer getStudentCardId() { return studentCardId; }
	@Override
	public void setStudentCardId(Integer studentCardId) { this.studentCardId = studentCardId; }
	@Override
	public boolean getIsBanned() { return isBanned; }
	@Override
	public void setIsBanned(boolean isBanned) { this.isBanned = isBanned; }
	
}
