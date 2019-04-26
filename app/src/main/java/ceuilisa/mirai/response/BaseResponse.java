package ceuilisa.mirai.response;

import java.util.List;

import ceuilisa.mirai.interf.ListShow;

public class BaseResponse{

    /**
     * message : success
     */

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
