package com.yang.entities;

import java.io.Serializable;

public class Restful<E> implements Serializable {

    private String code;
    private String msg;
    private int length;
    private E data;

    public Restful() {
    }

    public Restful(String code, String msg, int length, E data) {
        this.code = code;
        this.msg = msg;
        this.length = length;
        this.data = data;
    }

    public Restful(String code, String msg, E data) {
        this.code = code;
        this.msg = msg;
        this.length=0;
        this.data = data;
    }

    public Restful(String msg,E data) {
        this.code="200";
        this.msg = msg;
        this.length=0;
        this.data=data;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Restful{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", length=" + length +
                ", data=" + data +
                '}';
    }
}
