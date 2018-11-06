package ceuilisa.mirai.activities;

import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qingmei2.rximagepicker.core.RxImagePicker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import ceuilisa.mirai.R;
import ceuilisa.mirai.interf.MyImagePicker;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.DeleteImageResponse;
import ceuilisa.mirai.utils.Common;
import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadImageActivity extends BaseActivity{

    private Uri mUri;
    private Button mButton, mButton2;
    private ProgressBar mProgressBar;
    private ImageView mImageView;

    @Override
    void initLayout() {
        mLayoutID = R.layout.activity_upload;
    }

    @Override
    void initView() {
        mProgressBar = findViewById(R.id.progress);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v->finish());
        mButton = findViewById(R.id.select);
        MyImagePicker imagePicker = new RxImagePicker.Builder()
                .with(this)
                .build()
                .create(MyImagePicker.class);
        mButton.setOnClickListener(v -> imagePicker
                .openGallery()
                .subscribe(result -> {
                    Common.showLog(result.getUri().toString());
                    mUri = result.getUri();
                    Glide.with(mContext).load(result.getUri().toString()).into(mImageView);
                }));
        mButton2 = findViewById(R.id.upload);
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUri != null){
                    File file = new File(Common.getRealFilePath(mContext, mUri));
                    changeHeadImage(file);
                }else {
                    Common.showToast(mContext, "未选择图片");
                }
            }
        });
        mImageView = findViewById(R.id.imageview);
    }

    @Override
    void initData() {
    }


    public static byte[] File2Bytes(File file) {
        int byte_size = 1024;
        byte[] b = new byte[byte_size];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(
                    byte_size);
            for (int length; (length = fileInputStream.read(b)) != -1;) {
                outputStream.write(b, 0, length);
            }
            fileInputStream.close();
            outputStream.close();
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void changeHeadImage(File file) {
        mProgressBar.setVisibility(View.VISIBLE);
        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("myfile", file.getName(), photoRequestBody);
        Call<DeleteImageResponse> call = RetrofitUtil.getTempApi().changeHeadImg(photo);
        call.enqueue(new Callback<DeleteImageResponse>() {
            @Override
            public void onResponse(Call<DeleteImageResponse> call, Response<DeleteImageResponse> response) {
                if(response.body() != null && response.body().getMessage() != null){
                    if(response.body().getMessage().equals("upload success")){
                        Toasty.success(mContext, "上传成功",
                                Toast.LENGTH_SHORT, true).show();
                    }else {
                        Toasty.error(mContext, "This is an error toast.",
                                Toast.LENGTH_SHORT, true).show();
                    }
                }else {
                    Toasty.error(mContext, "This is an error toast.",
                            Toast.LENGTH_SHORT, true).show();
                }
                mProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<DeleteImageResponse> call, Throwable t) {
                mProgressBar.setVisibility(View.INVISIBLE);
                Toasty.error(mContext, "This is an error toast.",
                        Toast.LENGTH_SHORT, true).show();
            }
        });
    }
}
