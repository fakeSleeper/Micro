package com.weichuang.sensor.presenter;

import com.weichuang.sensor.base.presenter.BasePresenter;
import com.weichuang.sensor.contract.SplashContract;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * desc:
 *
 * @author: Forrest
 * @date: 2018/8/15
 */
public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {
    @Inject
    public SplashPresenter() {
    }

    /**
     * 两秒后跳转界面
     * @param view
     */
    @Override
    public void attachView(SplashContract.View view) {
        super.attachView(view);
        long splashTime = 2000;
        addSubscribe(Observable.timer(splashTime, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        //是否登录
                        if (getLoginStatus()) {
                            view.jumpToMain();
                        }else {
                            //跳转到登录状态
                            //第一次提交
                        }
                    }
                }));
    }

    /**
     * 是否登录
     * @return true:已登录   false:未登录
     */
    @Override
    public boolean getLoginStatus() {

        return false;
    }
}
