package com.wen.demo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.wen.demo.R;
import com.wen.demo.bean.PhotoBean;

import java.util.List;

/**
 * Created by wangenning on 15/11/18.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private Activity activity;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private ImageLoadingListener animateFirstListener;
    private List<PhotoBean.ResultsEntity> photoperson;



    public PhotoAdapter(Activity activity, ImageLoader imageLoader, DisplayImageOptions options, ImageLoadingListener animateFirstListener, List<PhotoBean.ResultsEntity> photoperson) {
        this.activity = activity;
        this.imageLoader=imageLoader;
        this.options=options;
        this.animateFirstListener=animateFirstListener;
        this.photoperson=photoperson;
        setHasStableIds(true);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        imageLoader.displayImage(photoperson.get(position).getUrl(), holder.imageView, options, animateFirstListener);
        holder.textView.setText("第" + position+ "张");
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "点击的是" + position, Toast.LENGTH_LONG).show();
            }
        });
    }
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return photoperson.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.test_item_img);
            textView = (TextView) itemView.findViewById(R.id.test_item_title);
        }

    }


}
