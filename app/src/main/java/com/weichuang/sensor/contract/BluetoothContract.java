package com.weichuang.sensor.contract;

import com.weichuang.sensor.base.presenter.AbstractPresenter;
import com.weichuang.sensor.base.view.AbstractView;

/**
 * desc: 蓝牙页面私有业务
 *
 * @author: Forrest
 * @date: 2018/9/21
 */
public interface BluetoothContract {
    interface View extends AbstractView {
        /**
         * 显示 不支持蓝牙 提示
         */
        void showDeviceUnSupportBleTips();

        /**
         * 显示 蓝牙关闭 提示
         */
        void showBleClosedTips();


        /**
         * 请求打开蓝牙
         */
        void requestOpenBluetooth();

        /**
         * 6.0请求蓝牙扫描权限
         */
        void requestPermissionForScan();
    }

    interface Presenter extends AbstractPresenter<View> {
        /**
         * 本设备是否支持蓝牙
         * @return
         */
        // boolean isDeviceSupportBluetooth();
    }
}
