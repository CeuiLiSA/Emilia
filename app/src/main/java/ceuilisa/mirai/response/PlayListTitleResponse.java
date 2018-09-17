package ceuilisa.mirai.response;

import java.util.List;

public class PlayListTitleResponse {

    public class Result{
        public List<PlayList> playlists;

        public class PlayList{
            public String id;
            public String name;
            public String coverImgUrl;
            public String subscribed;
            public String trackCount;
            public String userId;
            public String playCount;
            public String bookCount;
            public String description;
            public String highQuality;
            public String alg;
        }

        public String playlistCount;
    }

    public Result result;
    public int code;

}
