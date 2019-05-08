package ceuilisa.mirai.nodejs;

import java.util.List;

import ceuilisa.mirai.interf.ListShow;
import ceuilisa.mirai.response.BaseResponse;

public class FavorAlbumResponse extends BaseResponse implements ListShow<AlbumBean> {


    /**
     * data : [{"subTime":1557222444098,"msg":[],"alias":["PC游戏《白色相簿2》冬马和纱 Vocal Album"],"picId":109951163685334080,"artists":[{"img1v1Id":18686200114669624,"topicPerson":0,"alias":[],"picId":0,"briefDesc":"","albumSize":0,"img1v1Url":"https://p1.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","followed":false,"picUrl":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","musicSize":0,"trans":"","name":"冬馬かずさ","id":861562,"img1v1Id_str":"18686200114669622"}],"picUrl":"https://p1.music.126.net/gBBRhFUEQiMt4b_ic6faIA==/109951163685334078.jpg","name":"WHITE ALBUM2 ORIGINAL SOUNDTRACK～kazusa～","id":35093364,"size":10,"transNames":[]},{"subTime":1557222439591,"msg":[],"alias":[],"picId":109951163596890560,"artists":[{"img1v1Id":18686200114669624,"topicPerson":0,"alias":[],"picId":0,"briefDesc":"","albumSize":0,"img1v1Url":"https://p1.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","followed":false,"picUrl":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","musicSize":0,"trans":"","name":"森永真由美","id":17227,"img1v1Id_str":"18686200114669622"}],"picUrl":"https://p1.music.126.net/pYEN7joypDVa3EJohasZTw==/109951163596890560.jpg","name":"天ノ弱","id":59399,"size":10,"transNames":[]},{"subTime":1557222426818,"msg":[],"alias":[],"picId":7926379325753633,"artists":[{"img1v1Id":18686200114669624,"topicPerson":0,"alias":[],"picId":0,"briefDesc":"","albumSize":0,"img1v1Url":"https://p1.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","followed":false,"picUrl":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","musicSize":0,"trans":"","name":"V.A.","id":21138,"img1v1Id_str":"18686200114669622"}],"picUrl":"https://p1.music.126.net/daZcHVIJicL3wXJWMIjAng==/7926379325753633.jpg","name":"secret base～君がくれたもの～(初回生産限定盤)","id":3266177,"size":4,"transNames":[]}]
     * count : 3
     * hasMore : false
     * paidCount : 0
     */

    private int count;
    private boolean hasMore;
    private int paidCount;
    private List<AlbumBean> data;

    @Override
    public List<AlbumBean> getList() {
        return data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public int getPaidCount() {
        return paidCount;
    }

    public void setPaidCount(int paidCount) {
        this.paidCount = paidCount;
    }

    public List<AlbumBean> getData() {
        return data;
    }

    public void setData(List<AlbumBean> data) {
        this.data = data;
    }
}
