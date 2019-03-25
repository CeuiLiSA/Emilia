package ceuilisa.mirai.response;

import java.util.List;

public abstract class BaseResponse<Item> {

    /**
     * message : success
     */

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public abstract List<Item> getList();
}
