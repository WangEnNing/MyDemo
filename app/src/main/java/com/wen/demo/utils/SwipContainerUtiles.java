package com.wen.demo.utils;

import android.support.v4.widget.SwipeRefreshLayout;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wangenning on 15/11/20.
 */
public class SwipContainerUtiles {
    //通过反射调用起私有的自动刷新的方法！
    public static void setRefreshing(SwipeRefreshLayout refreshLayout, boolean refreshing, boolean notify) {
        Class<? extends SwipeRefreshLayout> refreshLayoutClass = refreshLayout.getClass();
        if (refreshLayoutClass != null) {

            try {
                Method setRefreshing = refreshLayoutClass.getDeclaredMethod("setRefreshing", boolean.class, boolean.class);
                setRefreshing.setAccessible(true);
                setRefreshing.invoke(refreshLayout, refreshing, notify);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
