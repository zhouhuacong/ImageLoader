package design.zhc.com.androidsourcedesignpatterns.ImageLoader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by qqq on 2016/5/2.
 */
public class ImageLoader {
    //线程池，线程数量为CPU的数量
    private ExecutorService mExecutorService = Executors.
            newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    ImageCache mImageCache = new MemoryCache();

    public void setImageCache(ImageCache cache) {
        mImageCache = cache;
    }

    public void displayImage(final String url, final ImageView imageView) {
        Bitmap bitmap = mImageCache.get(url);
        if (bitmap != null){
            imageView.setImageBitmap(bitmap);
            return;
        }
        //图片没有缓存
        submitLoadRequest(url, imageView);
    }

    private void submitLoadRequest(final String url, final ImageView imageView) {
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap downBitmap = downloadImage(url);
                if (downBitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(url)) {
                    mImageCache.put(url, downBitmap);
                }
                imageView.setImageBitmap(downBitmap);
            }
        });
    }

    public Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

}
