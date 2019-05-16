# Emilia
Material Design Music Player （neteasy api）

```java
public class Api {

    public <Ensure extends ListListen<List<TracksBean>>,
            Before extends BaseResponse<TracksBean>> void getPlaylistDetail(Ensure ensure){
        Retro.getApi()
                .getPlaylistDetail()
                .map(new Function<BaseResponse<TracksBean>, List<TracksBean>>() {
                    @Override
                    public List<TracksBean> apply(BaseResponse<TracksBean> tracksBeanBaseResponse) throws Exception {
                        return tracksBeanBaseResponse.getList();
                    }
                })
                .subscribe(ensure);
    }
}
```

![Image text](https://raw.githubusercontent.com/CeuiLiSA/Emilia/master/app/snap/Screenshot_1553849039.png)

![Image text](https://raw.githubusercontent.com/CeuiLiSA/Emilia/master/app/snap/Screenshot_1553849082.png)

![Image text](https://raw.githubusercontent.com/CeuiLiSA/Emilia/master/app/snap/Screenshot_1553849087.png)

![Image text](https://raw.githubusercontent.com/CeuiLiSA/Emilia/master/app/snap/Screenshot_1553849102.png)

![Image text](https://raw.githubusercontent.com/CeuiLiSA/Emilia/master/app/snap/Screenshot_1553849122.png)

![Image text](https://raw.githubusercontent.com/CeuiLiSA/Emilia/master/app/snap/Screenshot_1553849141.png)
