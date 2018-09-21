package com.weichuang.sensor.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.weichuang.sensor.R;
import com.weichuang.sensor.app.Constants;
import com.weichuang.sensor.base.activity.BaseActivity;
import com.weichuang.sensor.contract.MainContract;
import com.weichuang.sensor.presenter.MainPresenter;
import com.weichuang.sensor.ui.fragment.BluetoothFragment;
import com.weichuang.sensor.ui.fragment.HospitalFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FragmentTabHost
 *
 * @author Forrest
 * @date 2018/8/12.
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.main_image_train)
    ImageView mTrainImageView;
    @BindView(R.id.main_text_train)
    TextView mTrainTextView;
    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabHost;
    Class<?>[] clas = {HospitalFragment.class, BluetoothFragment.class, HospitalFragment.class};

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
            TextView tvIndicator = (TextView)View.inflate(this, R.layout.indicator, null);
            tvIndicator.setText(titles[i]);
            mTabHost.addTab(mTabHost.newTabSpec(Constants.TAGS[i]).setIndicator(tvIndicator), clas[i], null);
            mTabHost.setOnTabChangedListener(tabId -> {
                switch (tabId) {
                    case Constants.TRAIN:
                        mTrainImageView.setImageResource(R.drawable.circle_blue);
                        mTrainTextView.setTextColor(getResources().getColor(R.color.blue_btn));
                        break;
                    default:
                        mTrainImageView.setImageResource(R.drawable.circle_grey);
                        mTrainTextView.setTextColor(getResources().getColor(R.color.news_read));
                        break;
                }
            });

        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.main_image_train})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_image_train:
                mTabHost.setCurrentTab(1);
                break;
            default:
                break;
        }
    }
}
