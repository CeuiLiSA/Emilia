package ceuilisa.mirai.nodejs;

public class UserBindingsBean {
    /**
     * userId : 113568254
     * tokenJsonStr : {"countrycode":"","cellphone":"13990845246","hasPassword":true}
     * expiresIn : 2147483647
     * url :
     * refreshTime : 1450139947
     * expired : false
     * id : 53756045
     * type : 1
     */

    private int userId;
    private String tokenJsonStr;
    private long expiresIn;
    private String url;
    private int refreshTime;
    private boolean expired;
    private long id;
    private int type;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTokenJsonStr() {
        return tokenJsonStr;
    }

    public void setTokenJsonStr(String tokenJsonStr) {
        this.tokenJsonStr = tokenJsonStr;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(int refreshTime) {
        this.refreshTime = refreshTime;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
