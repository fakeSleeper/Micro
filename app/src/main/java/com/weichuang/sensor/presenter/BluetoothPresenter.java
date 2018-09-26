package com.weichuang.sensor.presenter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;

import com.weichuang.sensor.app.MicroPortApp;
import com.weichuang.sensor.base.presenter.BasePresenter;
import com.weichuang.sensor.contract.BluetoothContract;
import com.weichuang.sensor.service.BluetoothLeService;
import com.weichuang.sensor.ui.fragment.BluetoothFragment;

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

    /**
     * 注册蓝牙广播
     */
    private void registerBleReceiver() {
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        filter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        ((BluetoothFragment) mView).getActivity().registerReceiver(mReceiver, filter);
    }

    /**
     * 注册蓝牙广播
     */
    public void unRegisterReceiver() {
        ((BluetoothFragment) mView).getActivity().unregisterReceiver(mReceiver);
    }

    @Override
    public void attachView(BluetoothContract.View view) {
        super.attachView(view);
        if (!isDeviceSupportBluetooth()) {
            mView.showDeviceUnSupportBleTips();
            return;
        }
        registerBleReceiver();
        openBluetooth();
    }


    @Override
    public void detachView() {
        //切换fragment时，关闭接收广播
        ((BluetoothFragment) mView).getActivity().unregisterReceiver(mReceiver);
        super.detachView();
    }

    /**
     * 蓝牙广播
     */
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                switch (mAdapter.getState()) {
                    case BluetoothAdapter.STATE_ON:
                        System.out.println("蓝牙已打开");
                        //mConnIndex = NO_DEVICE;
                        //startBluetoothLeService();
                        break;
                    case BluetoothAdapter.STATE_OFF:
                        System.out.println("蓝牙已关闭");
                        mView.showBleClosedTips();
                        break;
                    default:
                        break;
                }
                //更新UI
                //updateUI();
            }
//            else if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
//                // GATT connect
//                int status = intent.getIntExtra(BluetoothLeService.EXTRA_STATUS,
//                        BluetoothGatt.GATT_FAILURE);
//                if (status == BluetoothGatt.GATT_SUCCESS) {
//                    setBusy(false);
//                    startDeviceActivity();
//                } else
//                    setError("Connect failed. Status: " + status);
//            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {
//                // GATT disconnect
//                int status = intent.getIntExtra(BluetoothLeService.EXTRA_STATUS,
//                        BluetoothGatt.GATT_FAILURE);
//                stopDeviceActivity();
//                if (status == BluetoothGatt.GATT_SUCCESS) {
//                    setBusy(false);
//                    mScanView.setStatus(mBluetoothDevice.getName() + " disconnected",
//                            STATUS_DURATION);
//                } else {
//                    setError("Disconnect failed. Status: " + status);
//                }
//                mConnIndex = NO_DEVICE;
//                mBluetoothLeService.close();
//            } else {
//                // Log.w(TAG,"Unknown action: " + action);
//            }

        }
    };


}
