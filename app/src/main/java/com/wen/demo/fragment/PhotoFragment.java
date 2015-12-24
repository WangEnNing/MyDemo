package com.wen.demo.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.wen.demo.R;
import com.wen.demo.adapter.PhotoAdapter;
import com.wen.demo.base.BaseFragment;
import com.wen.demo.bean.PhotoBean;
import com.wen.demo.callback.LodeMoreCallBack;
import com.wen.demo.config.Urls;
import com.wen.demo.http.VolleyTool;
import com.wen.demo.listener.RecyclerViewOnScroll;
import com.wen.demo.tools.GsonTools;
import com.wen.demo.utils.SpacesItemDecoration;
import com.wen.demo.utils.SwipContainerUtiles;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangenning on 15/11/17.
 */
public class PhotoFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, LodeMoreCallBack {
    private RecyclerView recyclerView;
    private PhotoAdapter adapter;
    private SwipeRefreshLayout swipeLayout;
    private LinearLayout footer_linearlayout;
    int lastVisibleItem = 0;
    boolean isFirstLoda = true;//是否第一次loading
    private int index = 1;
    private int lastVisibleItems;
    private GridLayoutManager mLayoutManager;
    List<PhotoBean.ResultsEntity> mResultsEntityList = new ArrayList<>();
    private Handler mhandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    swipeLayout.setRefreshing(false);
                    break;
            }
        }
    };

    public PhotoFragment() {
        super(R.layout.fragment_photo);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.test_recycler_view);
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.test_swipeLayout);
        footer_linearlayout = (LinearLayout) view.findViewById(R.id.footer_linearlayout);
        //进度动画的颜色
        swipeLayout.setColorSchemeResources(R.color.primary);
        //设置进度圈的大小只有两个值：DEFAULT、LARGE
//        swipeLayout.setSize(SwipeRefreshLayout.DEFAULT);
        //进度圈的背景色
        swipeLayout.setProgressBackgroundColor(R.color.white);
        swipeLayout.setProgressViewEndTarget(true, 100);
        swipeLayout.setPadding(10, 10, 10, 10);
        swipeLayout.setProgressViewOffset(true, 100, 200);
        swipeLayout.setDistanceToTriggerSync(50);
        swipeLayout.setOnRefreshListener(this);
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        //设置layoutManager
        recyclerView.setLayoutManager(mLayoutManager);
        SpacesItemDecoration decoration = new SpacesItemDecoration(10, 10, 0, 0);
        recyclerView.addItemDecoration(decoration);
        adapter = new PhotoAdapter(getActivity(), imageLoader, options, animateFirstListener
                , mResultsEntityList);
        recyclerView.addOnScrollListener(new RecyclerViewOnScroll(adapter, this));
        initLocation();
    }

    private void initLocation() {
        //利用反射进行设置自动刷新！
        SwipContainerUtiles.setRefreshing(swipeLayout, true, true);
    }

    private void getdata(int k) {
        StringRequest photorequest = new StringRequest(Request.Method.GET, Urls.BASE_URI + k, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                swipeLayout.setRefreshing(false);
                PhotoBean photoperson = GsonTools.getPerson(response.toString(), PhotoBean.class);
                List<PhotoBean.ResultsEntity> results = photoperson.getResults();
                //遍历结果
                for (PhotoBean.ResultsEntity result : results) {
                    mResultsEntityList.add(result);
                }
                if (mResultsEntityList.size() == 0) {
                    lastVisibleItem = 0;
                    return;
                }
                if (isFirstLoda) {
                    //第一次加载
                    isFirstLoda = false;
                    footer_linearlayout.setVisibility(View.GONE);
                    swipeLayout.setRefreshing(false);//刷新完毕!
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    //设置刷新完毕之后直接移动到首个postion
                    recyclerView.scrollToPosition(0);
                } else {
                    adapter.notifyDataSetChanged();
                    footer_linearlayout.setVisibility(View.GONE);
                    swipeLayout.setRefreshing(false);//刷新完毕!
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                swipeLayout.setRefreshing(false);
                showToast(getResources().getString(R.string.network_error));
            }
        });
        VolleyTool.getInstace(getActivity()).getRequestQueue().add(photorequest);
    }

    //刷新
    @Override
    public void onRefresh() {
        Log.e("执行刷新", "执行刷新");
        lastVisibleItem = 0;
        mResultsEntityList.clear();
        isFirstLoda = true;
        index = 1;
        getdata(index);
        swipeLayout.setRefreshing(true);

    }

    //加载更多
    @Override
    public void LodeMore() {
        Log.e("执行加载更多", "执行加载更多");
        index++;
        getdata(index);
        footer_linearlayout.setVisibility(View.VISIBLE);
    }
}
