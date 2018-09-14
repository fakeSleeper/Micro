package com.weichuang.sensor.base.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.weichuang.sensor.utils.ActivityCollector;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * desc : 继承自me.yokeyword.fragmentation.SupportActivity
 *
 * @author Forrest
 * @date 2018/8/13.
 */

public abstract class AbstractSimpleActivity extends SupportActivity {
    private Unbinder mUnbinder;
    private AbstractSimpleActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mActivity = this;
        mUnbinder = ButterKnife.bind(this);
        ActivityCollector.getInstance().addActivity(this);
        onViewCreated();
        initToolbar();
        initEventAndData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

    protected abstract void initEventAndData();

    protected abstract void initToolbar();

    protected abstract void onViewCreated();

    protected abstract int getLayoutId();
}
