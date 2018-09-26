package com.weichuang.sensor.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.weichuang.sensor.R;
import com.weichuang.sensor.app.Constants;
import com.weichuang.sensor.base.activity.BaseActivity;
import com.weichuang.sensor.contract.MainContract;
import com.weichuang.sensor.presenter.MainPresenter;
import com.weichuang.sensor.ui.fragment.BluetoothFragment;
import com.weichuang.sensor.ui.fragment.HospitalFragment;
import com.weichuang.sensor.utils.StatusBarUtil;
import com.weichuang.sensor.widget.RadarView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FragmentTabHost
 *
 * @author Forrest
 * @date 2018/8/12.
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabHost;
    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.radar)
    RadarView mRadar;
    Class<?>[] clas = {HospitalFragment.class, BluetoothFragment.class, HospitalFragment.class};

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        mTitleTv.setText(getString(R.string.title_hospital));
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.main_status_bar_blue), 1f);
        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTabHost();
    }

    private void initTabHost() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.real_tab_content);
        String[] titles = getResources().getStringArray(R.array.tab_title);
        for (int i = 0; i < titles.length; i++) {
            TextView tvIndicator = (TextView) View.inflate(this, R.layout.indicator, null);
            tvIndicator.setText(titles[i]);
            mTabHost.addTab(mTabHost.newTabSpec(Constants.TAGS[i]).setIndicator(tvIndicator), clas[i], null);
            mTabHost.setOnTabChangedListener(tabId -> {
                switch (tabId) {
                    case Constants.HOSPITAL:
                        mTitleTv.setText(getResources().getString(R.string.title_hospital));
                        mRadar.setText(getResources().getString(R.string.training));
                        mRadar.setTextColor(getResources().getColor(R.color.news_read));
                        break;

                    case Constants.TRAIN:
                        mTitleTv.setText(getResources().getString(R.string.title_device));
                        mRadar.setText(getResources().getString(R.string.search_device));
                        mRadar.setTextColor(getResources().getColor(R.color.blue_btn));
                        break;

                    case Constants.SETTING:
                        mTitleTv.setText(getResources().getString(R.string.title_setting));
                        mRadar.setText(getResources().getString(R.string.training));
                        mRadar.setTextColor(getResources().getColor(R.color.news_read));
                        break;

                    default:
                        break;
                }
            });

        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.radar})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.radar:
                if (mTabHost.getCurrentTab() == 1) {

                    mRadar.setTextColor(getResources().getColor(R.color.blue_btn));
                    boolean isSearching = mRadar.getState();
                    if (isSearching) {
                        mRadar.setText(getResources().getString(R.string.search_device));
                    } else {
                        mRadar.setText(getResources().getString(R.string.searching));
                    }
                    // TODO: 2018/9/26  开始或关闭搜索蓝牙设备


                    mRadar.setState(!isSearching);
                } else {
                    mTabHost.setCurrentTab(1);
                }

                break;
            default:
                break;
        }
    }

}
