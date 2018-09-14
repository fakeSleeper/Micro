package com.weichuang.sensor.base.presenter;

import com.weichuang.sensor.base.view.AbstractView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * desc : 管理事件流订阅的生命周期 ,所有的基本业务
 *
 * @author Forrest
 * @date 2018/8/13.
 */

public  class BasePresenter<T extends AbstractView> implements AbstractPresenter<T> {
    protected T mView;
    /**
     * 管理事件流
     */
    private CompositeDisposable mCompositeDisposable;
    @Override
    public void attachView(T view) {
        mView=view;
    }

    @Override
    public void detachView() {
        this.mView=null;
    }

    @Override
    public void setLoginStatus(boolean status) {

    }

    @Override
    public boolean getLoginStatus() {
        return false;
    }

    protected void addSubscribe(Disposable disposable){
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }
}
