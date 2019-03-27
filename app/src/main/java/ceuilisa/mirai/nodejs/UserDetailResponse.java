package ceuilisa.mirai.nodejs;

import java.util.List;

public class UserDetailResponse {


    /**
     * level : 10
     * listenSongs : 117763
     * userPoint : {"userId":113568254,"balance":3404,"updateTime":1553671888166,"version":10,"status":1,"blockBalance":0}
     * mobileSign : false
     * pcSign : false
     * profile : {"remarkName":null,"expertTags":null,"authStatus":0,"mutual":false,"backgroundImgId":109951163198268110,"userType":0,"djStatus":0,"experts":{},"avatarImgIdStr":"19085322835203895","backgroundImgIdStr":"109951163198268119","detailDescription":"","followed":false,"description":"","userId":113568254,"avatarImgId":19085322835203896,"accountStatus":0,"vipType":11,"gender":1,"birthday":877276800000,"nickname":"CeuiLiSA","city":1002400,"province":1000000,"defaultAvatar":false,"avatarUrl":"https://p2.music.126.net/9BSnF5WgYnnBjIqi2aLm-w==/19085322835203895.jpg","backgroundUrl":"http://p1.music.126.net/id-110tprTCxF4BXNbZIOw==/109951163198268119.jpg","signature":"肥宅，腿控，御姐控，救老婆，真正的粉丝，只土不豪，约，爱过，不是很懂你们二次元 ( \u2022 ̀ω\u2022́ )✧","authority":0,"avatarImgId_str":"19085322835203895","artistIdentity":[],"followeds":212,"follows":21,"cCount":0,"blacklist":false,"eventCount":103,"sDJPCount":0,"allSubscribedCount":0,"playlistCount":11,"playlistBeSubscribedCount":18,"sCount":0}
     * peopleCanSeeMyPlayRecord : true
     * bindings : [{"expiresIn":2147483647,"refreshTime":1450139947,"expired":false,"userId":113568254,"tokenJsonStr":null,"url":"","id":53756045,"type":1},{"expiresIn":2610044,"refreshTime":1548558201,"expired":true,"userId":113568254,"tokenJsonStr":null,"url":"http://weibo.com/u/3961286026","id":2842037708,"type":2},{"expiresIn":2593422,"refreshTime":1499081778,"expired":true,"userId":113568254,"tokenJsonStr":null,"url":"http://www.renren.com/899824729","id":2872984263,"type":4},{"expiresIn":604799,"refreshTime":1469016779,"expired":true,"userId":113568254,"tokenJsonStr":null,"url":"http://www.douban.com/people/147688412","id":2872898337,"type":3},{"expiresIn":7776000,"refreshTime":1551179362,"expired":false,"userId":113568254,"tokenJsonStr":null,"url":"","id":53732854,"type":5},{"expiresIn":7200,"refreshTime":1462725349,"expired":true,"userId":113568254,"tokenJsonStr":null,"url":"","id":2856304321,"type":10},{"expiresIn":2147483647,"refreshTime":1467191821,"expired":false,"userId":113568254,"tokenJsonStr":null,"url":"","id":2872896420,"type":0}]
     * adValid : false
     * code : 200
     * createTime : 1450139926380
     * createDays : 1199
     */

    private int level;
    private int listenSongs;
    private UserPointBean userPoint;
    private boolean mobileSign;
    private boolean pcSign;
    private ProfileBean profile;
    private boolean peopleCanSeeMyPlayRecord;
    private boolean adValid;
    private int code;
    private long createTime;
    private int createDays;
    private List<UserBindingsBean> bindings;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getListenSongs() {
        return listenSongs;
    }

    public void setListenSongs(int listenSongs) {
        this.listenSongs = listenSongs;
    }

    public UserPointBean getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(UserPointBean userPoint) {
        this.userPoint = userPoint;
    }

    public boolean isMobileSign() {
        return mobileSign;
    }

    public void setMobileSign(boolean mobileSign) {
        this.mobileSign = mobileSign;
    }

    public boolean isPcSign() {
        return pcSign;
    }

    public void setPcSign(boolean pcSign) {
        this.pcSign = pcSign;
    }

    public ProfileBean getProfile() {
        return profile;
    }

    public void setProfile(ProfileBean profile) {
        this.profile = profile;
    }

    public boolean isPeopleCanSeeMyPlayRecord() {
        return peopleCanSeeMyPlayRecord;
    }

    public void setPeopleCanSeeMyPlayRecord(boolean peopleCanSeeMyPlayRecord) {
        this.peopleCanSeeMyPlayRecord = peopleCanSeeMyPlayRecord;
    }

    public boolean isAdValid() {
        return adValid;
    }

    public void setAdValid(boolean adValid) {
        this.adValid = adValid;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getCreateDays() {
        return createDays;
    }

    public void setCreateDays(int createDays) {
        this.createDays = createDays;
    }

    public List<UserBindingsBean> getBindings() {
        return bindings;
    }

    public void setBindings(List<UserBindingsBean> bindings) {
        this.bindings = bindings;
    }

    public static class UserPointBean {
        /**
         * userId : 113568254
         * balance : 3404
         * updateTime : 1553671888166
         * version : 10
         * status : 1
         * blockBalance : 0
         */

        private int userId;
        private int balance;
        private long updateTime;
        private int version;
        private int status;
        private int blockBalance;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getBlockBalance() {
            return blockBalance;
        }

        public void setBlockBalance(int blockBalance) {
            this.blockBalance = blockBalance;
        }
    }

    public static class ProfileBean {
        /**
         * remarkName : null
         * expertTags : null
         * authStatus : 0
         * mutual : false
         * backgroundImgId : 109951163198268110
         * userType : 0
         * djStatus : 0
         * experts : {}
         * avatarImgIdStr : 19085322835203895
         * backgroundImgIdStr : 109951163198268119
         * detailDescription :
         * followed : false
         * description :
         * userId : 113568254
         * avatarImgId : 19085322835203896
         * accountStatus : 0
         * vipType : 11
         * gender : 1
         * birthday : 877276800000
         * nickname : CeuiLiSA
         * city : 1002400
         * province : 1000000
         * defaultAvatar : false
         * avatarUrl : https://p2.music.126.net/9BSnF5WgYnnBjIqi2aLm-w==/19085322835203895.jpg
         * backgroundUrl : http://p1.music.126.net/id-110tprTCxF4BXNbZIOw==/109951163198268119.jpg
         * signature : 肥宅，腿控，御姐控，救老婆，真正的粉丝，只土不豪，约，爱过，不是很懂你们二次元 ( • ̀ω•́ )✧
         * authority : 0
         * avatarImgId_str : 19085322835203895
         * artistIdentity : []
         * followeds : 212
         * follows : 21
         * cCount : 0
         * blacklist : false
         * eventCount : 103
         * sDJPCount : 0
         * allSubscribedCount : 0
         * playlistCount : 11
         * playlistBeSubscribedCount : 18
         * sCount : 0
         */

        private Object remarkName;
        private Object expertTags;
        private int authStatus;
        private boolean mutual;
        private long backgroundImgId;
        private int userType;
        private int djStatus;
        private ExpertsBean experts;
        private String avatarImgIdStr;
        private String backgroundImgIdStr;
        private String detailDescription;
        private boolean followed;
        private String description;
        private int userId;
        private long avatarImgId;
        private int accountStatus;
        private int vipType;
        private int gender;
        private long birthday;
        private String nickname;
        private int city;
        private int province;
        private boolean defaultAvatar;
        private String avatarUrl;
        private String backgroundUrl;
        private String signature;
        private int authority;
        private String avatarImgId_str;
        private int followeds;
        private int follows;
        private int cCount;
        private boolean blacklist;
        private int eventCount;
        private int sDJPCount;
        private int allSubscribedCount;
        private int playlistCount;
        private int playlistBeSubscribedCount;
        private int sCount;
        private List<?> artistIdentity;

        public Object getRemarkName() {
            return remarkName;
        }

        public void setRemarkName(Object remarkName) {
            this.remarkName = remarkName;
        }

        public Object getExpertTags() {
            return expertTags;
        }

        public void setExpertTags(Object expertTags) {
            this.expertTags = expertTags;
        }

        public int getAuthStatus() {
            return authStatus;
        }

        public void setAuthStatus(int authStatus) {
            this.authStatus = authStatus;
        }

        public boolean isMutual() {
            return mutual;
        }

        public void setMutual(boolean mutual) {
            this.mutual = mutual;
        }

        public long getBackgroundImgId() {
            return backgroundImgId;
        }

        public void setBackgroundImgId(long backgroundImgId) {
            this.backgroundImgId = backgroundImgId;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public int getDjStatus() {
            return djStatus;
        }

        public void setDjStatus(int djStatus) {
            this.djStatus = djStatus;
        }

        public ExpertsBean getExperts() {
            return experts;
        }

        public void setExperts(ExpertsBean experts) {
            this.experts = experts;
        }

        public String getAvatarImgIdStr() {
            return avatarImgIdStr;
        }

        public void setAvatarImgIdStr(String avatarImgIdStr) {
            this.avatarImgIdStr = avatarImgIdStr;
        }

        public String getBackgroundImgIdStr() {
            return backgroundImgIdStr;
        }

        public void setBackgroundImgIdStr(String backgroundImgIdStr) {
            this.backgroundImgIdStr = backgroundImgIdStr;
        }

        public String getDetailDescription() {
            return detailDescription;
        }

        public void setDetailDescription(String detailDescription) {
            this.detailDescription = detailDescription;
        }

        public boolean isFollowed() {
            return followed;
        }

        public void setFollowed(boolean followed) {
            this.followed = followed;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public long getAvatarImgId() {
            return avatarImgId;
        }

        public void setAvatarImgId(long avatarImgId) {
            this.avatarImgId = avatarImgId;
        }

        public int getAccountStatus() {
            return accountStatus;
        }

        public void setAccountStatus(int accountStatus) {
            this.accountStatus = accountStatus;
        }

        public int getVipType() {
            return vipType;
        }

        public void setVipType(int vipType) {
            this.vipType = vipType;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public long getBirthday() {
            return birthday;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public int getProvince() {
            return province;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public boolean isDefaultAvatar() {
            return defaultAvatar;
        }

        public void setDefaultAvatar(boolean defaultAvatar) {
            this.defaultAvatar = defaultAvatar;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getBackgroundUrl() {
            return backgroundUrl;
        }

        public void setBackgroundUrl(String backgroundUrl) {
            this.backgroundUrl = backgroundUrl;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getAuthority() {
            return authority;
        }

        public void setAuthority(int authority) {
            this.authority = authority;
        }

        public String getAvatarImgId_str() {
            return avatarImgId_str;
        }

        public void setAvatarImgId_str(String avatarImgId_str) {
            this.avatarImgId_str = avatarImgId_str;
        }

        public int getFolloweds() {
            return followeds;
        }

        public void setFolloweds(int followeds) {
            this.followeds = followeds;
        }

        public int getFollows() {
            return follows;
        }

        public void setFollows(int follows) {
            this.follows = follows;
        }

        public int getCCount() {
            return cCount;
        }

        public void setCCount(int cCount) {
            this.cCount = cCount;
        }

        public boolean isBlacklist() {
            return blacklist;
        }

        public void setBlacklist(boolean blacklist) {
            this.blacklist = blacklist;
        }

        public int getEventCount() {
            return eventCount;
        }

        public void setEventCount(int eventCount) {
            this.eventCount = eventCount;
        }

        public int getSDJPCount() {
            return sDJPCount;
        }

        public void setSDJPCount(int sDJPCount) {
            this.sDJPCount = sDJPCount;
        }

        public int getAllSubscribedCount() {
            return allSubscribedCount;
        }

        public void setAllSubscribedCount(int allSubscribedCount) {
            this.allSubscribedCount = allSubscribedCount;
        }

        public int getPlaylistCount() {
            return playlistCount;
        }

        public void setPlaylistCount(int playlistCount) {
            this.playlistCount = playlistCount;
        }

        public int getPlaylistBeSubscribedCount() {
            return playlistBeSubscribedCount;
        }

        public void setPlaylistBeSubscribedCount(int playlistBeSubscribedCount) {
            this.playlistBeSubscribedCount = playlistBeSubscribedCount;
        }

        public int getSCount() {
            return sCount;
        }

        public void setSCount(int sCount) {
            this.sCount = sCount;
        }

        public List<?> getArtistIdentity() {
            return artistIdentity;
        }

        public void setArtistIdentity(List<?> artistIdentity) {
            this.artistIdentity = artistIdentity;
        }

        public static class ExpertsBean {
        }
    }
}
