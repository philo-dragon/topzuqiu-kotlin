package cn.com.topzuqiu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.StatusBarUtil;
import com.pfl.component.R;

import java.util.ArrayList;
import java.util.List;


@Route(path = RouteUtils.APP_MAIN_ACTIVITY)
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    private List<Fragment> mList; //ViewPager的数据源
    private ViewPager viewPager;
    private BottomNavigationBar bottomNavigationBar;
    private BadgeItem badgeItem;


    @Override
    public int getContextView() {
        return R.layout.activity_main;
    }


    @Override
    public void componentInject(AppComponent appComponent) {
       /*DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule())
                .build()
                .inject(this);*/
    }

    @Override
    public void initView(View view) {

        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setTabSelectedListener(this);
        //设置被选中时隐藏角标
        badgeItem = new BadgeItem()
                .setHideOnSelect(true) //设置被选中时隐藏角标
                .setBackgroundColor(Color.RED)
                .setText("99");
        /**
         * 设置模式
         * 1、BottomNavigationBar.MODE_DEFAULT 默认
         * 如果Item的个数<=3就会使用MODE_FIXED模式，否则使用MODE_SHIFTING模式
         *
         * 2、BottomNavigationBar.MODE_FIXED 固定大小
         * 填充模式，未选中的Item会显示文字，没有换挡动画。
         *
         * 3、BottomNavigationBar.MODE_SHIFTING 不固定大小
         * 换挡模式，未选中的Item不会显示文字，选中的会显示文字。在切换的时候会有一个像换挡的动画
         */
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        /**
         * 设置背景的样式
         * 1、BottomNavigationBar.BACKGROUND_STYLE_DEFAULT 默认
         * 如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC 。
         * 如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
         *
         * 2、BottomNavigationBar.BACKGROUND_STYLE_STATIC 导航条的背景色是白色，
         * 加上setBarBackgroundColor（）可以设置成你所需要的任何背景颜色
         * 点击的时候没有水波纹效果
         *
         * 3、BottomNavigationBar.BACKGROUND_STYLE_RIPPLE 导航条的背景色是你设置的处于选中状态的
         * Item的颜色（ActiveColor），也就是setActiveColorResource这个设置的颜色
         * 点击的时候有水波纹效果
         */
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        //设置导航条背景颜色
        //在BACKGROUND_STYLE_STATIC下，表示整个容器的背景色。
        // 而在BACKGROUND_STYLE_RIPPLE下，表示选中Item的图标和文本颜色。默认 Color.WHITE
        bottomNavigationBar.setBarBackgroundColor(R.color.black_22);
        //选中时的颜色,在BACKGROUND_STYLE_STATIC下，表示选中Item的图标和文本颜色。
        // 而在BACKGROUND_STYLE_RIPPLE下，表示整个容器的背景色。默认Theme's Primary Color
        //bottomNavigationBar.setActiveColor(R.color.black);
        //未选中时的颜色，表示未选中Item中的图标和文本颜色。默认为 Color.LTGRAY
        //bottomNavigationBar.setInActiveColor("#FFFFFF");


        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_home_white_24dp, "首页").setActiveColorResource(R.color.white))
                .addItem(new BottomNavigationItem(R.mipmap.ic_music_note_white_24dp, "音乐").setActiveColorResource(R.color.white))
                .addItem(new BottomNavigationItem(R.mipmap.ic_tv_white_24dp, "电影").setActiveColorResource(R.color.white).setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.ic_videogame_asset_white_24dp, "游戏").setActiveColorResource(R.color.white))
                .setFirstSelectedPosition(0)
                .initialise(); //所有的设置需在调用该方法前完成

        //如果使用颜色的变化不足以展示一个选项Item的选中与非选中状态，
        // 可以使用BottomNavigationItem.setInActiveIcon()方法来设置。
//        new BottomNavigationItem(R.drawable.ic_home_white_24dp, "公交")//这里表示选中的图片
//                .setInactiveIcon(ContextCompat.getDrawable(this,R.mipmap.ic_launcher));//非选中的图片

        mList = new ArrayList<>();

        mList.add(RouteUtils.newFragment(RouteUtils.APP_HOME_FRAGMENT));
        mList.add(RouteUtils.newFragment(RouteUtils.MODULE1_FRAGMENT));
        mList.add(RouteUtils.newFragment(RouteUtils.MODULE2_FRAGMENT));
        mList.add(RouteUtils.newFragment(RouteUtils.MODULE_USER_FRAGMENT));

        viewPager = findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(mList.size());
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(), mList);
        viewPager.setAdapter(mainAdapter); //视图加载适配器


    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onTabSelected(int position) {//未选中 -> 选中
        //tab被选中
        viewPager.setCurrentItem(position, false);

    }

    @Override
    public void onTabUnselected(int position) {//选中 -> 未选中

    }

    @Override
    public void onTabReselected(int position) {//选中 -> 选中

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        goToPosition(position);

    }

    private void goToPosition(int position) {
        switch (position) {
            case 0:
                StatusBarUtil.darkMode(this, false);
                break;
            case 1:
                StatusBarUtil.darkMode(this, true);
                break;
            case 2:
                StatusBarUtil.darkMode(this, true);
                break;
            case 3:
                StatusBarUtil.darkMode(this, true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean isSwipeBackEnable() {
        return false;
    }

    @Override
    public boolean isSwipeRelateEnable() {
        return false;
    }

    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

}

