package ie.response;

import java.util.HashMap;
import java.util.Map;

public class MsgResponse {
	private int code;
	private String msg;
	private Map<String, Object> extend = new HashMap<>();
	
	public static MsgResponse success() {
		MsgResponse msg = new MsgResponse();
		msg.setCode(200);
		msg.setMsg("success");
		return msg;
	}
	
	public static MsgResponse fail() {
		MsgResponse msg = new MsgResponse();
		msg.setCode(400);
		msg.setMsg("fail");
		return msg;
	}
	
	public MsgResponse add(String key, Object value) {
		this.getExtend().put(key, value);
		return this;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}

}
