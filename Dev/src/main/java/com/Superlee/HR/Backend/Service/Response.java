package com.Superlee.HR.Backend.Service;

public class Response {
    public String errmsg;
    public Object value;

    public Response(String msg, Object val) {
        errmsg = msg;
        value = val;
    }

    public Response() {
        errmsg = null;
        value = null;
    }

    public Response(Object res) {
        value = res;
    }

    public Response(String msg) {
        errmsg = msg;
    }
}
