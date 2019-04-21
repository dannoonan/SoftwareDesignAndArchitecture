package ie.util;

public enum StateCode {
	SUCCESS(1),
	FAIL(0),
	INSUFFICIENT_BALANCE(-1),
	NOT_EXISTS(-2),
    NOT_AVAILABLE(-3),
	ALREADY_EXISTS(409),
	ERROR(404),
	BAD_REQUEST(400),
	PROCESS_SUCCESS(200);
	
    private int code;
    
    StateCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
