package com.weichuang.sensor.contract;

import com.weichuang.sensor.base.presenter.AbstractPresenter;
import com.weichuang.sensor.base.view.AbstractView;

/**
 * desc: 私有业务与UI
 * @author Forrest
 * @date 2018/8/13.
 */

public interface SplashContract {
    /**
     * Splash UI操作
     */
    interface View extends AbstractView{
        /**
         * 跳转到主界面
         */
        void jumpToMain();
    }

    /**
     * Splash 逻辑业务
     */
    interface Presenter extends AbstractPresenter<View>{

    }
}
