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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class JsonObjectPostRequest extends Request<JSONObject>
{
	private Map<String, String> mMap;
	private Listener<JSONObject> mListener;
	private String url;
	private static final String UTF_8 = "UTF-8";
	private static  int TIME_OUT = 10000;// 默认超时时间为10s


	public JsonObjectPostRequest(String url, Listener<JSONObject> listener, ErrorListener errorListener,
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
	public Request<?> setRetryPolicy(RetryPolicy retryPolicy) {
		retryPolicy = new DefaultRetryPolicy(TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);// 网络超时为10s
		return super.setRetryPolicy(retryPolicy);
	}

	@Override
	public RetryPolicy getRetryPolicy()
	{
		RetryPolicy retryPolicy = new DefaultRetryPolicy(TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);// 网络超时为10s
		return retryPolicy;
	}


	@Override
	protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
		try {
			String jsonString = new String(response.data, UTF_8);
			// String jsonString = new String(response.data,
			// HttpHeaderParser.parseCharset(response.headers));
			return Response.success(new JSONObject(jsonString),
					HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JSONException je) {
			return Response.error(new ParseError(je));
		}
	}


	@Override
	protected void deliverResponse(JSONObject response)
	{
		mListener.onResponse(response);
	}

}
