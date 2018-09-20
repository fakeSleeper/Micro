package com.weichuang.sensor.presenter;

import com.weichuang.sensor.base.presenter.BasePresenter;
import com.weichuang.sensor.contract.HospitalContract;

import javax.inject.Inject;

/**
 * desc:
 *
 * @author: Forrest
 * @date: 2018/9/20
 */
public class HospitalPrestener extends BasePresenter<HospitalContract.View> implements HospitalContract.Presenter {
    @Inject
    public HospitalPrestener() {
    }
}
