package com.weichuang.sensor.presenter;

import com.weichuang.sensor.base.presenter.BasePresenter;
import com.weichuang.sensor.contract.MainContract;

import javax.inject.Inject;

/**
 * desc:
 *
 * @author: Forrest
 * @date: 2018/9/20
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    @Inject
    public MainPresenter() {
    }
    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
        //设置到中间的fragment
    }
}
