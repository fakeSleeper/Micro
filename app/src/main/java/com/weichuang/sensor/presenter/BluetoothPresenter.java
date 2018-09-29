package com.weichuang.sensor.presenter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;

import com.weichuang.sensor.app.Constants;
import com.weichuang.sensor.app.MicroPortApp;
import com.weichuang.sensor.base.presenter.BasePresenter;
import com.weichuang.sensor.contract.BluetoothContract;
import com.weichuang.sensor.model.BleDeviceInfo;
import com.weichuang.sensor.service.BluetoothLeService;
import com.weichuang.sensor.ui.fragment.BluetoothFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * desc:
 *
 * @author: Forrest
 * @date: 2018/9/21
 */
public class BluetoothPresenter extends BasePresenter<BluetoothContract.View> implements BluetoothContract.Presenter {

    private BluetoothAdapter mAdapter;
    private FragmentActivity mContext;
    private BluetoothLeService mBluetoothLeService = null;
    private List<BleDeviceInfo> mDeviceInfoList;
    private Disposable mDisposable;


    @Inject
    public BluetoothPresenter() {
        mDeviceInfoList = new ArrayList<>();
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
            startBluetoothLeService();
        } else {
            mView.requestOpenBluetooth();
        }
    }

    /**
     * 开始蓝牙服务
     */
    private void startBluetoothLeService() {
        Intent bindIntent = new Intent(mContext, BluetoothLeService.class);
        mContext.startService(bindIntent);
        mContext.bindService(bindIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }


    /**
     * 注册蓝牙广播
     */
    private void registerBleReceiver() {
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        filter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        mContext.registerReceiver(mReceiver, filter);
    }

    /**
     * 注册蓝牙广播
     */
    public void unRegisterReceiver() {
        mContext.unregisterReceiver(mReceiver);
    }

    @Override
    public void attachView(BluetoothContract.View view) {
        super.attachView(view);
        if (!isDeviceSupportBluetooth()) {
            mView.showDeviceUnSupportBleTips();
            return;
        }
        mContext = ((BluetoothFragment) mView).getActivity();
        registerBleReceiver();
        openBluetooth();
    }


    @Override
    public void detachView() {
        //切换fragment时，关闭接收广播 ,关闭蓝牙服务
        mContext.unregisterReceiver(mReceiver);
        mContext.stopService(new Intent(mContext, BluetoothLeService.class));
        mContext.unbindService(mServiceConnection);
        if (mDisposable != null && !mDisposable.isDisposed()) {
            System.out.println("取消定时器");
            mDisposable.dispose();
        }
        doScan(false);
        super.detachView();
    }

    /**
     * 蓝牙服务
     */
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentName, IBinder service) {
            System.out.println("bind服务已连接");
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service)
                    .getService();
            if (!mBluetoothLeService.initialize()) {
                System.out.println("Unable to initialize BluetoothLeService");
                return;
            }
            final int n = mBluetoothLeService.numConnectedDevices();
            if (n > 0) {

            } else {
                Scan(true);
                System.out.println("BluetoothLeService connected");
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
            System.out.println("BluetoothLeService disconnected");
        }
    };

    /**
     * 开启或关闭搜索蓝牙设备，先判断是否有权限
     */
    private void Scan(boolean start) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mView.requestPermissionForScan();
        } else {
            doScan(start);
        }

    }

    /**
     * 开始或停止扫描 ,每次扫描时长为20秒
     *
     * @param start
     */
    public void doScan(boolean start) {
        if (start) {
            mDeviceInfoList.clear();
            mAdapter.startLeScan(mScanCallback);
            //取消以前的定时任务
            if (mDisposable != null && !mDisposable.isDisposed()) {
                System.out.println("要重置啦");
                mDisposable.dispose();
            }
            //雷达开始扫描
            mView.Scaning(true);
            //10s 后停止扫描
            Observable.timer(Constants.SCAN_DURATION, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io())
                    .subscribe(new Observer<Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            //Observer 与 Observable 的连接
                            //切换fragment或连续点击 radarView的时候
                            mDisposable = d;
                        }

                        @Override
                        public void onNext(Long aLong) {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {
                            System.out.println("停止蓝牙扫描");
                            mAdapter.stopLeScan(mScanCallback);
                            mView.Scaning(false);
                        }
                    });
        } else {
            mAdapter.stopLeScan(mScanCallback);
            mView.Scaning(false);
        }
    }


    /**
     * 蓝牙搜索callback
     */
    private BluetoothAdapter.LeScanCallback mScanCallback = (device, rssi, scanRecord) -> {

        System.out.println(String.format("%s (address : %s ,rssi : %d)", device.getName(), device.getAddress(), rssi));
        if (!deviceInfoExists(device.getAddress())) {
            // 新设备
            BleDeviceInfo deviceInfo = new BleDeviceInfo(device, rssi);
            mDeviceInfoList.add(deviceInfo);
        } else {
            // 在集合中已经存在, 更新 RSSI info
            BleDeviceInfo deviceInfo = findDeviceInfo(device);
            deviceInfo.updateRssi(rssi);
            //mScanView.notifyDataSetChanged();
        }

    };

    /**
     * 设备是否已添加
     *
     * @param device
     * @return
     */
    private BleDeviceInfo findDeviceInfo(BluetoothDevice device) {
        for (int i = 0; i < mDeviceInfoList.size(); i++) {
            if (mDeviceInfoList.get(i).getBluetoothDevice().getAddress()
                    .equals(device.getAddress())) {
                return mDeviceInfoList.get(i);
            }
        }
        return null;
    }

    /**
     * 设备是否已经存在
     *
     * @param address
     * @return
     */
    private boolean deviceInfoExists(String address) {
        for (int i = 0; i < mDeviceInfoList.size(); i++) {
            if (mDeviceInfoList.get(i).getBluetoothDevice().getAddress()
                    .equals(address)) {
                return true;
            }
        }
        return false;
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
