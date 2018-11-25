package ie.util;

public enum StateCode {
	SUCCESS(1),
	FAIL(0),
	INSUFFICIENT_BALANCE(-1),
	NOT_EXISTS(-2),
	ALREADY_EXISTS(409),
	ERROR(404),
	USER_NOT_FOUND(400),
	PROCESS_SUCCESS(200);
	
    private int code;
    
    private StateCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
