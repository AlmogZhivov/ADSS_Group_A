package com.Superlee.HR.Backend.Service;

public class Response {
    public String errMsg;
    public Object value;

    public Response() {
        errMsg = null;
        value = null;
    }

    public Response(Object res) {
        value = res;
    }

    public Response(String msg) {
        errMsg = msg;
    }
}
