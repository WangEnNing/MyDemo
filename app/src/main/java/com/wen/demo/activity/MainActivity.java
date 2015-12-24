package com.wen.demo.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.wen.demo.R;
import com.wen.demo.adapter.ViewpagerAdapter;
import com.wen.demo.base.BaseActivity;


public class MainActivity extends BaseActivity {
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView left_view, right_view;
    private ViewpagerAdapter viewpageradapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = initToolbar();
        initDrawer();
        initNavigationViewHeader();
        initTabLayout();
    }


    private void initDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void initNavigationViewHeader() {
        left_view = (NavigationView) findViewById(R.id.left_navigation);
        right_view = (NavigationView) findViewById(R.id.right_navigation);
        //设置头像，布局app:headerLayout="@layout/drawer_header"所指定的头布局
        View view = left_view.inflateHeaderView(R.layout.drawer_header);
        TextView userName = (TextView) view.findViewById(R.id.userName);
        userName.setText(R.string.name);
        //View mNavigationViewHeader = View.inflate(MainActivity.this, R.layout.drawer_header, null);
        //navigationView.addHeaderView(mNavigationViewHeader);//此方法在魅族note 1，头像显示不全
        //菜单点击事件
        left_view.setNavigationItemSelectedListener(new NavigationItemSelected());
    }

    class NavigationItemSelected implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            mDrawerLayout.closeDrawers();
            menuItem.setChecked(true);
            switch (menuItem.getItemId()) {
                case R.id.navigation_item_1:

                    return true;
                case R.id.navigation_item_2:
                    return true;
                default:

                    return true;
            }
        }
    }

    //顶部tab
    private void initTabLayout() {
        //顶部滑动的tab
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewpageradapter = new ViewpagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(viewpageradapter);
        // 设置ViewPager的数据等
        tabLayout.setupWithViewPager(viewPager);
        //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//适合很多tab
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//tab均分,适合少的tab
        //tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);//tab均分,适合少的tab,TabLayout.GRAVITY_CENTER

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            mDrawerLayout.openDrawer(right_view);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
