package com.gec.oasys.pojo;

import java.io.Serializable;

/**
 * @author : 卓炎秋
 * @date : 2020-04-22 11:51
 */
public class AjaxResultJson implements Serializable {
    private String msg;
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
