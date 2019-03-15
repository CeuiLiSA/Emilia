package ceuilisa.mirai.response;

import java.util.List;

public class LoginResponse {


    /**
     * message : 成功添加收藏
     * datas : []
     */

    private String message;
    private List<?> datas;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getDatas() {
        return datas;
    }

    public void setDatas(List<?> datas) {
        this.datas = datas;
    }
}
