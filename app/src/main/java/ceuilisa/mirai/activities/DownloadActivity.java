package ceuilisa.mirai.activities;

import android.os.Environment;

import ceuilisa.mirai.R;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.FileSearch;

public class DownloadActivity extends BaseActivity{
    @Override
    void initLayout() {
        mLayoutID = R.layout.activity_download;
    }

    @Override
    void initView() {

    }

    @Override
    void initData() {
        Common.showLog(Environment.getExternalStorageDirectory().getPath());
        Common.showLog(Environment.getExternalStorageDirectory().getAbsolutePath());
        FileSearch fileSearch = new FileSearch(Environment.getExternalStorageDirectory().getPath());
        fileSearch.run();
    }
}
