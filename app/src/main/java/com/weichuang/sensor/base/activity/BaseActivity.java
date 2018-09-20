package com.weichuang.sensor.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.weichuang.sensor.base.presenter.AbstractPresenter;
import com.weichuang.sensor.base.view.AbstractView;
import com.weichuang.sensor.utils.CommonUtils;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * desc : 所有activity基类
 *
 * @author Forrest
 * @date 2018/8/13.
 */

public abstract class BaseActivity<T extends AbstractPresenter> extends AbstractSimpleActivity implements AbstractView, HasSupportFragmentInjector {

    /**
     * 为每个fragment分配注入器，注入依赖
     */
    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentDispatchingAndroidInjector;

    /**
     * 逻辑业务
     */
    @Inject
    T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onViewCreated() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentDispatchingAndroidInjector;
    }

    @Override
    public void showLoginView() {

    }

    @Override
    public void showLogoutView() {

    }

    @Override
    public void showSnackBar(String message) {
        CommonUtils.showSnackMessage(this, message);
    }

    @Override
    public void showToast(String message) {
        CommonUtils.showMessage(this, message);
    }
}
