package com.wen.demo.fragment;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.wen.demo.R;
import com.wen.demo.adapter.NewsAdapter;
import com.wen.demo.base.BaseFragment;
import com.wen.demo.bean.ImageNewsBean;
import com.wen.demo.bean.NewsBean;
import com.wen.demo.callback.LodeMoreCallBack;
import com.wen.demo.config.Urls;
import com.wen.demo.http.VolleyTool;
import com.wen.demo.listener.RecyclerViewOnScroll;
import com.wen.demo.tools.GsonTools;
import com.wen.demo.utils.DividerItemDecoration;
import com.wen.demo.utils.SwipContainerUtiles;

import org.json.JSONArray;

import java.util.ArrayList;


public class NewsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, LodeMoreCallBack {
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeLayout;
    private LinearLayout newsfoot;
    private NewsAdapter newsAdapter;
    boolean isFirst = true;
    private int index = 1;
    private NewsBean newsBean;
    private ImageNewsBean imagenewsBean;
    private ArrayList<NewsBean.BodyEntity.ItemEntity> data = new ArrayList<NewsBean.BodyEntity.ItemEntity>();


    public NewsFragment() {
        super(R.layout.fragment_news);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.news_recycle);
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.news_swipe);
        newsfoot = (LinearLayout) view.findViewById(R.id.newsfoot);
        //进度动画的颜色
        swipeLayout.setColorSchemeResources(R.color.primary);
        //设置进度圈的大小只有两个值：DEFAULT、LARGE
        swipeLayout.setSize(SwipeRefreshLayout.DEFAULT);
        //进度圈的背景色
        swipeLayout.setProgressBackgroundColor(R.color.white);
        swipeLayout.setProgressViewEndTarget(true, 100);
        swipeLayout.setPadding(10, 10, 10, 10);
        swipeLayout.setProgressViewOffset(true, 100, 200);
        // 调整进度条距离屏幕顶部的距离
        swipeLayout.setDistanceToTriggerSync(50);
        swipeLayout.setOnRefreshListener(this);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), LinearLayoutManager.VERTICAL));
        initLocation();

    }

    public void initLocation() {
        SwipContainerUtiles.setRefreshing(swipeLayout, true, true);
    }


    private void getNewsData(int k) {
        StringRequest newsrequest = new StringRequest(Request.Method.GET, Urls.NEWS_BASE_URI + k, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                swipeLayout.setRefreshing(false);
                Log.e("1111", response.toString());
                try {
                    JSONArray jsonArray = new JSONArray(response.toString());
                    String Str_date = jsonArray.get(0).toString();
                    newsBean = GsonTools.getPerson(Str_date, NewsBean.class);
                    for (NewsBean.BodyEntity.ItemEntity mydata : newsBean.body.item) {
                        data.add(mydata);
                    }
                    if (data.size() == 0) {
                        showToast("暂无数据");
                        return;
                    }
                    if (isFirst) {
                        isFirst = false;
                        newsfoot.setVisibility(View.GONE);
                        newsAdapter = new NewsAdapter(getActivity(), imageLoader, options, animateFirstListener, data,imagenewsBean);
                        mRecyclerView.addOnScrollListener(new RecyclerViewOnScroll(newsAdapter, NewsFragment.this));
                        mRecyclerView.setAdapter(newsAdapter);
                        mRecyclerView.scrollToPosition(0);
                    } else {
                        newsAdapter.notifyDataSetChanged();
                        newsfoot.setVisibility(View.GONE);
                        swipeLayout.setRefreshing(false);//刷新完毕!
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                swipeLayout.setRefreshing(false);
                showToast(getResources().getString(R.string.network_error));
            }
        });
        VolleyTool.getInstace(getActivity()).getRequestQueue().add(newsrequest);
    }

    @Override
    public void onRefresh() {
        data.clear();
        isFirst = true;
        swipeLayout.setRefreshing(true);
        getImageNews();
        index = 1;
        getNewsData(index);
    }

    @Override
    public void LodeMore() {
        swipeLayout.setRefreshing(true);
        index++;
        getNewsData(index);
        newsfoot.setVisibility(View.VISIBLE);

    }
    public void getImageNews(){
        StringRequest newsrequest = new StringRequest(Request.Method.GET, Urls.Home_pager, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response.toString());
                    String Str_image = jsonArray.get(0).toString();
                    imagenewsBean = GsonTools.getPerson(Str_image, ImageNewsBean.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                showToast(getResources().getString(R.string.network_error));
            }
        });
        VolleyTool.getInstace(getActivity()).getRequestQueue().add(newsrequest);
    }
}
