package com.wen.demo.base;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.wen.demo.R;
import com.wen.demo.tools.CommonTools;
import com.wen.demo.utils.LayoutUtil;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wangenning on 15/11/19.
 */
public class BaseFragment extends Fragment {
    private View view;
    private int layoutId;
    public ImageLoader imageLoader;
    public ImageLoadingListener animateFirstListener;
    /**
     * 默认格式
     */
    public DisplayImageOptions options;
    /**
     * 圆图
     */
    public DisplayImageOptions options_roundness;
    private Toast toast = null; //Toast的对象！

    // 布局
    private int screenWidth;
    private int screenHeight;
    float ScreenTitle; // 标题栏与状态栏的高度占比
    float ScreenTitle_title; // 标题栏的高度
    protected LayoutUtil mLayoutUtil;

    public BaseFragment(int layoutId) {
        super();
        this.layoutId = layoutId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), layoutId, null);
        //初始化头中的各个控件,以及公共控件ImageLoader
        init();
        return view;
    }

    protected void init() {
        //初始化布局参数
        screenWidth = BaseApplication.screenWidth;
        screenHeight = BaseApplication.screenHeight;
        mLayoutUtil = new LayoutUtil();
        ScreenTitle = BaseApplication.ScreenTitle;
        ScreenTitle_title = BaseApplication.ScreenTitle_title;

        //初始化ImageLoader
        imageLoader = ImageLoader.getInstance();
        animateFirstListener = new AnimateFirstDisplayListener();
        options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_loading)
                // 正在加载
                .showImageForEmptyUri(R.drawable.ic_loading)
                        // 空图片
                .showImageOnFail(R.drawable.ic_loading)
                        // 错误图片
                .showImageOnFail(R.drawable.ic_loading)
                        // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(10)) // 设置成圆角图片
                .build();
//        options_base = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.mipmap.image_loading) // resource or
//                        // drawable
//                .showImageForEmptyUri(R.mipmap.image_loading) // resource or
//                        // drawable
//                .showImageOnFail(R.mipmap.image_loading) // resource or drawable
//                .resetViewBeforeLoading(false) // default
////				.delayBeforeLoading(1000)	// 延时一秒加载
//                .cacheInMemory(true) // default //使用缓存！
//                .cacheOnDisk(true) // default
//                .considerExifParams(false) // default
//                .imageScaleType(ImageScaleType.IN_SAMPLE_INT) // default
//                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
//                .displayer(new SimpleBitmapDisplayer()) // default
//                .handler(new Handler()) // default
//                .build();
//
        options_roundness = new DisplayImageOptions.Builder().cacheInMemory() // 缓存在内存中
                .cacheOnDisc() // 磁盘缓存
                .showImageOnLoading(R.drawable.ic_loading) // resource or
                .showImageForEmptyUri(R.drawable.ic_loading) // resource
                        // or
                .showImageOnFail(R.drawable.ic_loading) // resource or
                        // drawable
                .resetViewBeforeLoading(false) // default
//				.delayBeforeLoading(1000)
                .cacheInMemory(true) // default //使用缓存！
                .cacheOnDisk(true) // default
                .considerExifParams(false) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT) // default
                .bitmapConfig(Bitmap.Config.RGB_565) // default
                .displayer(new SimpleBitmapDisplayer()) // default
                .displayer(new RoundedBitmapDisplayer(120))//设置图片为圆角显示！
                .handler(new Handler()) // default
                .build();
    }

    public void showToast(String id) {
//        if (toast == null) {
            CommonTools
                    .showShortToast(getActivity(), id);
//        } else {
//            toast.setText(id);
//        }
//        toast.show();
    }
    public void showToastLong(String id) {
//        if (toast == null) {
            CommonTools
                    .showLongToast(getActivity(), id);
//        } else {
//            toast.setText(id);
//        }
//        toast.show();
    }

    /**
     * 图片加载第一次显示监听器
     *
     * @author Administrator
     */
    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {
        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                // 是否第一次显示
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    // 图片淡入效果
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
