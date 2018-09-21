package com.weichuang.sensor.presenter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;

import com.weichuang.sensor.app.MicroPortApp;
import com.weichuang.sensor.base.presenter.BasePresenter;
import com.weichuang.sensor.contract.BluetoothContract;

import javax.inject.Inject;

/**
 * desc:
 *
 * @author: Forrest
 * @date: 2018/9/21
 */
public class BluetoothPresenter extends BasePresenter<BluetoothContract.View> implements BluetoothContract.Presenter {

    private BluetoothAdapter mAdapter;


    @Inject
    public BluetoothPresenter() {
    }

    /**
     * 要实现的 业务包括：
     * 1：本设备是否支持 蓝牙
     * 2：实现类似Metro大都会 相同的蓝牙 功能
     * 3:to_be_continued
     */

    /**
     * 检测 蓝牙是否可用
     *
     * @return
     */
    private boolean isDeviceSupportBluetooth() {
        boolean hasBLE = MicroPortApp.getInstance().getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
        //API 18 以上
        mAdapter = ((BluetoothManager) MicroPortApp.getInstance().getSystemService(Context.BLUETOOTH_SERVICE)).getAdapter();
        return hasBLE && mAdapter != null;
    }

    /**
     * 打开蓝牙
     */
    private void openBluetooth() {
        if (mAdapter.isEnabled()) {
            //startBluetoothLeService();
        } else {
            mView.requestOpenBluetooth();
        }
    }


    @Override
    public void attachView(BluetoothContract.View view) {
        super.attachView(view);
        if (!isDeviceSupportBluetooth()) {
            mView.showDeviceUnSupportBleTips();
            return;
        }

        openBluetooth();
        //检查蓝牙是否打开
//        registerReceiver(mReceiver, mFilter);
//        mBtAdapterEnabled = mBtAdapter.isEnabled();
//        if (mBtAdapterEnabled) {
//            // Start straight away
//            startBluetoothLeService();
//        } else {
//            // Request BT adapter to be turned on
//            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableIntent, REQ_ENABLE_BT);
//        }
    }


}
