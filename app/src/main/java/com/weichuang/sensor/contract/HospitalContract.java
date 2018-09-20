package com.weichuang.sensor.contract;

import com.weichuang.sensor.base.presenter.AbstractPresenter;
import com.weichuang.sensor.base.view.AbstractView;

/**
 * desc:
 *
 * @author: Forrest
 * @date: 2018/9/20
 */
public interface HospitalContract {
    interface View extends AbstractView{

    }

    interface Presenter extends AbstractPresenter<View> {

    }
}
