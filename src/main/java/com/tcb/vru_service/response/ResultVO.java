package com.tcb.vru_service.response;

import java.io.Serializable;

/**
 * @program: vru_service
 * @description: 返回响应
 * @author: laoXue
 * @create: 2020-06-10 17:39
 **/
public class ResultVO<T> implements Serializable {
    private Boolean status;
    private String msg;
    private T data;

    public ResultVO(boolean status, String msg) {
        this(status, msg, null);
    }

    public ResultVO(T data) {
        this(true, null, data);
    }

    private ResultVO(Boolean status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultVO{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data.toString() +
                '}';
    }
}
