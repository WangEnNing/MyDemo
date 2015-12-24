package com.wen.demo.base;

import android.app.Application;
import android.content.Context;
import android.view.WindowManager;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.wen.demo.R;
import com.wen.demo.tools.CommonTools;

/**
 * Created by wangenning on 15/11/18.
 */
public class BaseApplication extends Application {
    private static Context context;
    public static DisplayImageOptions options;
    public static int screenWidth;
    public static int screenHeight;
    /**
     * 标题栏与状态栏的高度占比
     */
    public static float ScreenTitle;
    /**
     * 标题栏的高度占比
     */
    public static float ScreenTitle_title;


    @Override
    public void onCreate() {
        super.onCreate();
        // 获取屏幕尺寸大小，使程序能在不同大小的手机上有更好的兼容性
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        screenHeight = wm.getDefaultDisplay().getHeight();
        context = getApplicationContext();
        initImageLoader(context);

    }


    private void initImageLoader(Context applicationContext) {
        // TODO Auto-generated method stub
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory().cacheOnDisc().build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(defaultOptions).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory().discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();

        options = new DisplayImageOptions.Builder().showStubImage(R.mipmap.image_loading)
                .showImageForEmptyUri(R.mipmap.image_loading).showImageOnFail(R.mipmap.image_error)
                .cacheInMemory(true).cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(20)).build();

        ImageLoader.getInstance().init(config);
    }
    public void showToastLong(String id) {
//        if (toast == null) {
        CommonTools
                .showLongToast(getApplicationContext(), id);
//        } else {
//            toast.setText(id);
//        }
//        toast.show();
    }
}
