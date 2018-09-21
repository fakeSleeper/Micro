package com.weichuang.sensor.contract;

import com.weichuang.sensor.base.presenter.AbstractPresenter;
import com.weichuang.sensor.base.view.AbstractView;

/**
 * desc:
 *
 * @author: Forrest
 * @date: 2018/9/20
 */
public interface MainContract {
    /**
     * MainActivity UI 操作
     */
    interface  View extends AbstractView{

    }

    /**
     * MainActivity 业务
     */
    interface Presenter extends AbstractPresenter<View> {

    }

}
