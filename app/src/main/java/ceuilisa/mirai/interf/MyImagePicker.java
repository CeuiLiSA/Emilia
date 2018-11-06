package ceuilisa.mirai.interf;

import com.qingmei2.rximagepicker.entity.Result;
import com.qingmei2.rximagepicker.entity.sources.Camera;
import com.qingmei2.rximagepicker.entity.sources.Gallery;

import io.reactivex.Observable;

public interface MyImagePicker {
    @Gallery
    Observable<Result> openGallery();

    @Camera
    Observable<Result> openCamera();
}
