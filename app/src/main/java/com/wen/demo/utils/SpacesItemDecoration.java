package com.wen.demo.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wangenning on 15/11/18.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int left_space,right_space,top_space,botton_space;

    public SpacesItemDecoration(int left_space,int right_space,int top_space,int botton_space ) {
        this.left_space=left_space;
        this.right_space=right_space;
        this.top_space=top_space;
        this.botton_space=botton_space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=left_space;
        outRect.right=right_space;
        outRect.bottom=botton_space;
        if(parent.getChildAdapterPosition(view)==0){
            outRect.top=top_space;
        }
    }
}