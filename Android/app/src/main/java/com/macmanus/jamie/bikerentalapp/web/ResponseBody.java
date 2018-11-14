package com.macmanus.jamie.bikerentalapp.web;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseBody {
    @SerializedName("code")
    @Expose
    private Integer responseCode;

    @SerializedName("msg")
    @Expose
    private String message;

    @SerializedName("extend")
    @Expose
    private Extend extend;


    public Integer getResponseCode() {
        return responseCode;
    }

    public String getMessage() {
        return message;
    }

    public Extend getExtend() {
        return extend;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setExtend(Extend extend) {
        this.extend = extend;
    }
}
