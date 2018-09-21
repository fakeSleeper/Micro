package com.weichuang.sensor.ui.fragment;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

import com.weichuang.sensor.R;
import com.weichuang.sensor.app.MicroPortApp;
import com.weichuang.sensor.base.fragment.BaseFragment;
import com.weichuang.sensor.contract.BluetoothContract;
import com.weichuang.sensor.presenter.BluetoothPresenter;
import com.weichuang.sensor.utils.CommonUtils;
import com.weichuang.sensor.widget.TipsDialog;

/**
 * desc:
 *
 * @author: Forrest
 * @date: 2018/9/21
 */
public class BluetoothFragment extends BaseFragment<BluetoothPresenter> implements BluetoothContract.View {
    private final int REQUEST_ENABLE_BLE = 100;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bluetooth;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showDeviceUnSupportBleTips() {
        TipsDialog tipsDialog = new TipsDialog(getActivity()) {
            @Override
            public String getContent() {
                return MicroPortApp.getInstance().getResources().getString(R.string.un_support_ble);
            }
        };
        tipsDialog.show();
    }

    @Override
    public void requestOpenBluetooth() {
        Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableIntent, REQUEST_ENABLE_BLE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_ENABLE_BLE:
                // When the request to enable Bluetooth returns
                if (resultCode == Activity.RESULT_OK) {
                } else {
                    //finish();
                    CommonUtils.showMessage(getActivity(),"主人不让打开蓝牙");
                    mPresenter.unRegisterReceiver();
                }
                break;
            default:
                break;
        }
    }
}
