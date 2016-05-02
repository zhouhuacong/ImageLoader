package design.zhc.com.androidsourcedesignpatterns.ImageLoader;

import android.graphics.Bitmap;

/**
 * Created by ${ZHC} on ${2016/5/2}.
 */
public interface ImageCache {

    void put(String url, Bitmap bitmap);

    Bitmap get(String url);
}
