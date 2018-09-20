package com.weichuang.sensor.ui.fragment;

import com.weichuang.sensor.R;
import com.weichuang.sensor.base.fragment.BaseFragment;
import com.weichuang.sensor.contract.HospitalContract;
import com.weichuang.sensor.presenter.HospitalPrestener;

/**
 * desc:
 *
 * @author: Forrest
 * @date: 2018/9/20
 */
public class HospitalFragment extends BaseFragment<HospitalPrestener> implements HospitalContract.View{
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hospital;
    }

    @Override
    protected void initEventAndData() {

    }
}
