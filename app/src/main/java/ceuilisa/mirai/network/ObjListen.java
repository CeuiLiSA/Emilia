package ceuilisa.mirai.network;

import ceuilisa.mirai.response.BaseResponse;
import ceuilisa.mirai.utils.Common;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class ObjListen<T extends BaseResponse> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        if (t != null) {
            if (t.getCode() == 200) {
                success(t);
            } else {
                error();
            }
        } else {
            error();
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        Common.showToast(e.toString());
        error();
    }

    @Override
    public void onComplete() {

    }

    public abstract void success(T t);

    public abstract void error();
}
