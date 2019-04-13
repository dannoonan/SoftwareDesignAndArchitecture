package com.cs4125.bikerentalapp.web;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class ResponseBody {
    @SerializedName("code")
    @Expose
    private Integer responseCode;

    @SerializedName("msg")
    @Expose
    private String message;

    @SerializedName("extend")
    @Expose
    private HashMap extend;

    public Integer getResponseCode() {
        return responseCode;
    }

    public String getMessage() {
        return message;
    }

    public HashMap getExtend() {
        return extend;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setExtend(HashMap extend) {
        this.extend = extend;
    }

    @Override
    public String toString(){
        return "{" + "code:" + responseCode + " msg: "
                + message + " extend: " + extend.toString() + "}";
    }
}
