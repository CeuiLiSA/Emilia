package ceuilisa.mirai.utils;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FileSearch {

    private String mFolderPath;

    public FileSearch(String mFolderPath) {
        this.mFolderPath = mFolderPath;
    }

    public void run() {
        Common.showLog("开始文件检索：");
        traverseFolder(mFolderPath);
    }


    private void traverseFolder(String path) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                Common.showLog("文件夹是空的!");
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        traverseFolder(file2.getAbsolutePath());
                    } else {
                        Common.showLog(file2.getName());
                        if (file2.getName().substring(0, file2.getName().length() - 3).equals(".mp3") ||
                                file2.getName().substring(0, file2.getName().length() - 3).equals(".MP3")) {
                            Common.showLog("********************************");
                            Common.showLog("文件名:" + file2.getName());
                        }
                    }
                }
            }
        } else {
            Common.showLog("文件不存在!");
        }
    }

    private void rx() {
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            e.onNext(1);
            Translate.translateMusic(null);
            e.onComplete();
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Common.showLog("onNext");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        Common.showLog("onComplete");
                    }
                });
    }
}
