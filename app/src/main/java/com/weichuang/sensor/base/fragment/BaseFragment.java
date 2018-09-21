package com.weichuang.sensor.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.weichuang.sensor.base.presenter.AbstractPresenter;
import com.weichuang.sensor.base.view.AbstractView;
import com.weichuang.sensor.utils.CommonUtils;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * desc:
 *
 * @author: Forrest
 * @date: 2018/9/20
 */
public abstract class BaseFragment<T extends AbstractPresenter> extends AbstractSimpleFragment
        implements AbstractView {

    @Inject
    protected T mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AndroidSupportInjection.inject(this);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroyView();
    }


    @Override
    public void showLoginView() {
    }

    @Override
    public void showLogoutView() {
    }

    @Override
    public void showToast(String message) {
        CommonUtils.showMessage(_mActivity, message);
    }

    @Override
    public void showSnackBar(String message) {
        CommonUtils.showSnackMessage(_mActivity, message);
    }

}
