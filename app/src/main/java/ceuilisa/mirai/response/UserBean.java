package ceuilisa.mirai.response;

public class UserBean {

    private String userName;
    private String password;
    private String netEasyAccount;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    private boolean isLogin;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNetEasyAccount() {
        return netEasyAccount;
    }

    public void setNetEasyAccount(String netEasyAccount) {
        this.netEasyAccount = netEasyAccount;
    }
}
