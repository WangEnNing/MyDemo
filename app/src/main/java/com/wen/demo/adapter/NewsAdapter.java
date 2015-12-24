package com.wen.demo.adapter;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.wen.demo.R;
import com.wen.demo.bean.ImageNewsBean;
import com.wen.demo.bean.NewsBean;
import com.wen.demo.utils.MyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangenning on 15/11/18.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private ArrayList<NewsBean.BodyEntity.ItemEntity> dataList;
    private Activity mActivity;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private ImageLoadingListener animateFirstListener;
    private ImageNewsBean imagenewsBean;
    private int Title_type = 1;
    private List<View> list;

    private ImageView[] indicator_imgs;
    private ImageNewsAdapter imageadapter;


    public NewsAdapter(Activity activity, ImageLoader imageLoader, DisplayImageOptions options, ImageLoadingListener animateFirstListener, ArrayList<NewsBean.BodyEntity.ItemEntity> data, ImageNewsBean imagenewsBean) {
        this.dataList = data;
        this.mActivity = activity;
        this.imageLoader = imageLoader;
        this.options = options;
        this.animateFirstListener = animateFirstListener;
        this.imagenewsBean = imagenewsBean;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        if (Title_type == viewType) {
            view = View.inflate(parent.getContext(), R.layout.pager_item, null);
        } else {
            view = View.inflate(parent.getContext(), R.layout.news_item, null);
        }
        return new ViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        Log.e("1111", "222" + itemViewType);
        if (itemViewType == Title_type) {
            imageadapter = new ImageNewsAdapter(imagenewsBean, initHeadView(), options, imageLoader, animateFirstListener);
            if (holder.view_pager != null) {
                holder.view_pager.setAdapter(imageadapter);
                initIndicator(holder.point_layout);
                holder.view_pager.setOnPageChangeListener(new MyPageChangeListener());

            }

        } else {
            holder.texttitle.setText(dataList.get(position).channel);
            holder.textcontext.setText(dataList.get(position).title);
            holder.textcomment.setText(dataList.get(position).updateTime);
            imageLoader.displayImage(dataList.get(position).thumbnail, holder.home_image, options, animateFirstListener);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Log.e("position", "position" + position);
        if (position == 0) {
            return Title_type;
        } else {
            return super.getItemViewType(position);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MyViewPager view_pager;
        LinearLayout point_layout;
        ImageView home_image;
        TextView texttitle;
        TextView textcontext;
        TextView textcomment;


        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == Title_type) {
                view_pager = (MyViewPager) itemView.findViewById(R.id.view_pager);
                point_layout = (LinearLayout) itemView.findViewById(R.id.position_indicator);
            } else {
                home_image = (ImageView) itemView.findViewById(R.id.home_image);
                texttitle = (TextView) itemView.findViewById(R.id.texttitle);
                textcontext = (TextView) itemView.findViewById(R.id.textcontext);
                textcomment = (TextView) itemView.findViewById(R.id.textcomment);
            }


        }
    }

    /**
     * 头部信息，轮播图
     */
    private List<View> initHeadView() {
        list = new ArrayList<View>();
        list.clear();
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        LinearLayout item;
        /**
         * 创建多个item （每一条viewPager都是一个item） 从服务器获取完数据（如文章标题、url地址） 后，再设置适配器
         */
        try {


            int length = imagenewsBean.getBody().getItem().size();
            indicator_imgs = new ImageView[length];
            for (int i = 0; i < length; i++) {
                final int index = i;
                item = (LinearLayout) inflater.inflate(R.layout.image_news_item, null);
                TextView title = (TextView) item.findViewById(R.id.pager_text);
                title.setText(imagenewsBean.getBody().getItem().get(i).getTitle());
                list.add(item);
            }
            return list;
        } catch (Exception e) {
return  null;
        }

    }

    private void initIndicator(LinearLayout point_layout) {
        ImageView imgView;
        Log.i("mytest", indicator_imgs.length + "");
        for (int i = 0; i < indicator_imgs.length; i++) {
            imgView = new ImageView(mActivity);
            LinearLayout.LayoutParams params_linear = new LinearLayout.LayoutParams(
                    10, 10);
            params_linear.setMargins(7, 10, 7, 10);
            imgView.setLayoutParams(params_linear);
            indicator_imgs[i] = imgView;

            if (i == 0) { // 初始化第一个为选中状态
                indicator_imgs[i]
                        .setBackgroundResource(R.drawable.gallery_click);
            } else {
                indicator_imgs[i].setBackgroundResource(R.drawable.gallery);
            }
            ((ViewGroup) point_layout).addView(indicator_imgs[i]);

        }
    }

    /**
     * 翻页监听
     */
    class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(final int position) {
            // TODO Auto-generated method stub
            // 改变所有导航的背景图片为：未选中
            for (int i = 0; i < indicator_imgs.length; i++) {
                indicator_imgs[i].setBackgroundResource(R.drawable.gallery);
            }
            // 改变当前背景图片为：选中
            indicator_imgs[position]
                    .setBackgroundResource(R.drawable.gallery_click);
        }
    }
}
