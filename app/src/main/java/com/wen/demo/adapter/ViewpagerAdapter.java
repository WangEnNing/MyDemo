package com.wen.demo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wen.demo.fragment.NewsFragment;
import com.wen.demo.fragment.PhotoFragment;

/**
 * Created by wangenning on 15/11/19.
 */
public class ViewpagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[]{"今日头条", "福利社区"};
    private Context context;

    public ViewpagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                NewsFragment newsFragment = new NewsFragment();
                return newsFragment;
            case 1:
                PhotoFragment photoFragment = new PhotoFragment();
                return photoFragment;
//            case 2:
//                OtherFragment otherFragment = new OtherFragment();
//                return otherFragment;

        }
        return null;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
