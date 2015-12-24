package com.wen.demo.http;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**
 * volley管理工具类 不需要一直new对象
 *
 * @author wen
 *
 */

public class VolleyTool {
	private RequestQueue mRequestQueue;
	private static VolleyTool mInstace = null;

	private VolleyTool(Context context) {
		mRequestQueue = Volley.newRequestQueue(context);
	}

	public static VolleyTool getInstace(Context context) {
		if (mInstace == null) {
			mInstace = new VolleyTool(context);
		}
		return mInstace;

	}

	public RequestQueue getRequestQueue() {
		return mRequestQueue;
	}


	//取消网络请求
	public void cancle(Object tag) {
		this.mRequestQueue.cancelAll(tag);
	}

	public void release() {
		this.mRequestQueue = null;
	}
}
