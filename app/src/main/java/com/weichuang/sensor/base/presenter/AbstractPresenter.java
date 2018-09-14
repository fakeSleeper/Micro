package com.weichuang.sensor.base.presenter;

import com.weichuang.sensor.base.view.AbstractView;

/**
 * desc : Presenter 超类
 *
 * @author Forrest
 * @date 2018/8/13.
 */

public interface AbstractPresenter<T extends AbstractView> {
    /**
     * 注入view所需的逻辑
     * @param view
     */
    void attachView(T view);

    /**
     * 回收view
     */
    void detachView();

    void setLoginStatus(boolean status);

    /**
     * 是否是登录状态（要防止在其他界面不是登录状态）
     * @return
     */
    boolean getLoginStatus();
}
