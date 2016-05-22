package com.sjn.healthassistant.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.sjn.healthassistant.R;

import java.io.File;
import java.io.IOException;

/**
 * Created by sjn on 16/4/24.
 */
public class ImageLoadUtil {
    public static final DisplayImageOptions cache_disk_options = new DisplayImageOptions.Builder()
        .cacheOnDisk(true)
        .cacheInMemory(true)
        .bitmapConfig(Bitmap.Config.RGB_565)
        .build();
    public static final DisplayImageOptions cache_option_16_x_9 = new DisplayImageOptions.Builder()
        .showImageOnLoading(R.drawable.ic_loading16x9)
        .cacheOnDisk(true)
        .cacheInMemory(true)
        .bitmapConfig(Bitmap.Config.RGB_565)
        .build();
    public static final DisplayImageOptions drug = new DisplayImageOptions.Builder()
        .showImageOnLoading(R.drawable.ic_loading16x9)
        .showImageForEmptyUri(R.drawable.ic_no_data)
        .showImageOnFail(R.drawable.ic_no_data)
        .cacheOnDisk(true)
        .cacheInMemory(true)
        .bitmapConfig(Bitmap.Config.RGB_565)
        .build();

    public static void init(Context context) {
        try {
            int MAX_CACHE_MEMORY_SIZE = (int) (Runtime.getRuntime().maxMemory() / 8);
            File cacheDir = StorageUtils.getOwnCacheDirectory(context, context.getPackageName() + "/cache/image/");
            ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(MAX_CACHE_MEMORY_SIZE))
                .diskCache(new LruDiskCache(cacheDir, new Md5FileNameGenerator(), 0))
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .build();
            ImageLoader.getInstance().init(configuration);
        } catch (IOException e) {
            LogUtil.e(e.getMessage());
        }
    }

    private static void loadImage(String url, ImageView imageView, DisplayImageOptions options) {
        ImageLoader.getInstance().displayImage(url, imageView, options);
    }

    public static void loadImageCacheDisk(String url, ImageView imageView) {
        loadImage(url, imageView, cache_disk_options);
    }

    public static void load16x9Image(String url, ImageView imageView) {
        loadImage(url, imageView, cache_option_16_x_9);
    }

    public static void loadDrug(String url, ImageView imageView) {
        loadImage(url, imageView, drug);
    }


}
