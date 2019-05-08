package ceuilisa.mirai.response;

public class BaseResponse {

    public int code;
    /**
     * message : success
     */

    public String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
