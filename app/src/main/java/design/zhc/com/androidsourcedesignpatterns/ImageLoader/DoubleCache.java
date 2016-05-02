package design.zhc.com.androidsourcedesignpatterns.ImageLoader;

import android.graphics.Bitmap;

/**
 * Created by ZHC on ${Date}.
 */
public class DoubleCache implements ImageCache {

    private DiskCache mDiskCache;
    private MemoryCache mMemoryCache;

    DoubleCache() {
        mDiskCache = new DiskCache();
        mMemoryCache = new MemoryCache();
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
        mDiskCache.put(url, bitmap);
    }

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap != null) {
            return bitmap;
        } else {
            bitmap = mDiskCache.get(url);
            return bitmap;
        }
    }

}
