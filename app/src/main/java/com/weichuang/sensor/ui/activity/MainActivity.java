package com.weichuang.sensor.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.weichuang.sensor.R;
import com.weichuang.sensor.base.activity.BaseActivity;
import com.weichuang.sensor.contract.MainContract;
import com.weichuang.sensor.presenter.MainPresenter;
import com.weichuang.sensor.ui.fragment.HospitalFragment;

import butterknife.BindView;

/**
 * FragmentTabHost
 *
 * @author Forrest
 * @date 2018/8/12.
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.main_image_train)
    ImageView mIvTrain;
    @BindView(R.id.main_tv_train)
    TextView mTvTrain;
    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabHost;
    Class<?>[] clas = {HospitalFragment.class, HospitalFragment.class, HospitalFragment.class};
    int[] images = {R.drawable.tab_1_selector, 1, R.drawable.tab_2_selector};

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTabHost();
    }

    private void initTabHost() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.real_tab_content);
        String[] titles =getResources().getStringArray(R.array.tab_title);
        for (int i = 0; i < titles.length; i++) {
            View indicatorView = View.inflate(this, R.layout.indicator, null);
            TextView tvIndicator = indicatorView.findViewById(R.id.title_indicator);
            tvIndicator.setText(titles[i]);
            ImageView imageView = indicatorView.findViewById(R.id.image_indicator);
            if (i != 1) {
                imageView.setImageResource(images[i]);
            }
            mTabHost.addTab(mTabHost.newTabSpec("tab" + i).setIndicator(indicatorView), clas[i], null);
            mTabHost.setOnTabChangedListener(tabId -> {
                switch (tabId) {
                    case "tab2":
                        mIvTrain.setImageResource(R.mipmap.message_pre);
                        break;
                    default:
                        mIvTrain.setImageResource(R.mipmap.message);
                        break;
                }
            });
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
