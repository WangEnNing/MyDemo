package com.wen.demo.http;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class JsonArrayPostRequest extends Request<JSONArray> {
	private Map<String, String> mMap;
	private Listener<JSONArray> mListener;
	private String url;
	private static final String UTF_8 = "UTF-8";
	private int TIME_OUT = 10000;// 默认超时时间为10s

	public JsonArrayPostRequest(String url, Listener<JSONArray> listener, ErrorListener errorListener,
			Map<String, String> map)
	{
		super(Method.POST, url, errorListener);
		this.url = url;
		mListener = listener;
		this.setShouldCache(false);// 每次都是新的请求，不在内存中缓存请求防止内存数据被窃取
		mMap = map;
	}



	/**
	 * 设置连接超时时间
	 * 
	 * @param time_out
	 */
	public void setTIME_OUT(int time_out)
	{
		TIME_OUT = time_out;
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError
	{
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Charset", UTF_8);
		return headers;
	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError
	{
		return mMap;
	}

	@Override
	public RetryPolicy getRetryPolicy()
	{
		RetryPolicy retryPolicy = new DefaultRetryPolicy(TIME_OUT, 0, 1.0f);// 网络超时为10s
		return retryPolicy;
	}
	@Override
	protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
		try {
			String jsonString = new String(response.data, UTF_8);
			// response.headers.put(HTTP.CONTENT_TYPE,
			// response.headers.get("content-type"));
			// String jsonString = new String(response.data,
			// HttpHeaderParser.parseCharset(response.headers));
			return Response.success(new JSONArray(jsonString),
					HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JSONException je) {
			return Response.error(new ParseError(je));
		}
	}

	@Override
	protected void deliverResponse(JSONArray response)
	{
		mListener.onResponse(response);
	}

}