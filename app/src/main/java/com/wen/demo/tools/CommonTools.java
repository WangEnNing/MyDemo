package com.wen.demo.tools;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wen.demo.R;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonTools {
	private static Object obj;

	/**
	 * 短暂显示Toast消息
	 * 
	 * @param context
	 * @param message
	 */
	public static void showShortToast(Context context, String message) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_toast, null);
		TextView text = (TextView) view.findViewById(R.id.toast_message);
		text.setText(message);
		Toast toast = new Toast(context);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, 0, 300);
		toast.setView(view);
		toast.show();
		// try {
		// // 通过反射技术，从toast对象中获取mTN对象
		// Field field = toast.getClass().getDeclaredField("mTN");
		// field.setAccessible(true);
		// obj = field.get(toast);
		// // 从TN对象中获得show方法
		// Method method = obj.getClass().getDeclaredMethod("show", null);
		// // 调用TN对象的show方法来显示Toast信息提示框
		// method.invoke(obj, null);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}
	public static void showLongToast(Context context, String message) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_toast, null);
		TextView text = (TextView) view.findViewById(R.id.toast_message);
		text.setText(message);
		Toast toast = new Toast(context);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setGravity(Gravity.BOTTOM, 0, 300);
		toast.setView(view);
		toast.show();
		// try {
		// // 通过反射技术，从toast对象中获取mTN对象
		// Field field = toast.getClass().getDeclaredField("mTN");
		// field.setAccessible(true);
		// obj = field.get(toast);
		// // 从TN对象中获得show方法
		// Method method = obj.getClass().getDeclaredMethod("show", null);
		// // 调用TN对象的show方法来显示Toast信息提示框
		// method.invoke(obj, null);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

//	public static void stopShortToast() {
//		if (obj != null) {
//			Method method;
//			try {
//				method = obj.getClass().getDeclaredMethod("hide", null);
//				method.invoke(obj, null);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//	}

	/**
	 * 根据手机分辨率从dp转成px
	 * 
	 * @param context
	 * @param dpValue
	 * @return
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率�?px(像素) 的单�?转成�?dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f) - 15;
	}

	/**
	 * 获取手机状态栏高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getStatusBarHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0;
		int statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = context.getResources().getDimensionPixelSize(x);
			return statusBarHeight;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusBarHeight;
	}

	/**
	 * 判断手机号码
	 */
	public static boolean isMobileNO(String mobiles) {

		Pattern pattern = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher matcher = pattern.matcher(mobiles);

		return matcher.matches();

	}

}
