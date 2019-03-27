package ceuilisa.mirai.nodejs;

public class AccountBean {
    /**
     * id : 113568254
     * userName : 1_13990845246
     * type : 1
     * status : 0
     * whitelistAuthority : 0
     * createTime : 1450139926380
     * salt : [B@5f2d36eb
     * tokenVersion : 0
     * ban : 0
     * baoyueVersion : 1
     * donateVersion : 0
     * vipType : 11
     * viptypeVersion : 1528468126398
     * anonimousUser : false
     */

    private int id;
    private String userName;
    private int type;
    private int status;
    private int whitelistAuthority;
    private long createTime;
    private String salt;
    private int tokenVersion;
    private int ban;
    private int baoyueVersion;
    private int donateVersion;
    private int vipType;
    private long viptypeVersion;
    private boolean anonimousUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getWhitelistAuthority() {
        return whitelistAuthority;
    }

    public void setWhitelistAuthority(int whitelistAuthority) {
        this.whitelistAuthority = whitelistAuthority;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getTokenVersion() {
        return tokenVersion;
    }

    public void setTokenVersion(int tokenVersion) {
        this.tokenVersion = tokenVersion;
    }

    public int getBan() {
        return ban;
    }

    public void setBan(int ban) {
        this.ban = ban;
    }

    public int getBaoyueVersion() {
        return baoyueVersion;
    }

    public void setBaoyueVersion(int baoyueVersion) {
        this.baoyueVersion = baoyueVersion;
    }

    public int getDonateVersion() {
        return donateVersion;
    }

    public void setDonateVersion(int donateVersion) {
        this.donateVersion = donateVersion;
    }

    public int getVipType() {
        return vipType;
    }

    public void setVipType(int vipType) {
        this.vipType = vipType;
    }

    public long getViptypeVersion() {
        return viptypeVersion;
    }

    public void setViptypeVersion(long viptypeVersion) {
        this.viptypeVersion = viptypeVersion;
    }

    public boolean isAnonimousUser() {
        return anonimousUser;
    }

    public void setAnonimousUser(boolean anonimousUser) {
        this.anonimousUser = anonimousUser;
    }
}