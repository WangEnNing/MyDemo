package com.wen.demo.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.wen.demo.R;
import com.wen.demo.bean.ImageNewsBean;

import java.util.List;

/**
 * Created by wangenning on 15/12/2.
 */
public class ImageNewsAdapter extends PagerAdapter {
    ImageNewsBean list;
    List<View> views;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private ImageLoadingListener animateFirstListener;
    private ImageView image;

    public ImageNewsAdapter(ImageNewsBean list, List<View> views, DisplayImageOptions options, ImageLoader imageLoader, ImageLoadingListener animateFirstListener) {
        this.list = list;
        this.views=views;
        this.imageLoader = imageLoader;
        this.animateFirstListener = animateFirstListener;
        this.views = views;
        this.options = options;
    }


    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position);
        image = (ImageView) view.findViewById(R.id.pager_image);
        imageLoader.displayImage(list.getBody().getItem().get(position).getThumbnail(),image,options,animateFirstListener);
        container.removeView(views.get(position));
        container.addView(views.get(position));
        return views.get(position);
    }
}
