package com.weichuang.sensor.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.weichuang.sensor.R;
import com.weichuang.sensor.app.MicroPortApp;
import com.weichuang.sensor.base.fragment.BaseFragment;
import com.weichuang.sensor.contract.BluetoothContract;
import com.weichuang.sensor.presenter.BluetoothPresenter;
import com.weichuang.sensor.ui.adapter.BleDeviceAdapter;
import com.weichuang.sensor.utils.CommonUtils;
import com.weichuang.sensor.widget.RadarView;
import com.weichuang.sensor.widget.TipsDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * desc:
 *
 * @author: Forrest
 * @date: 2018/9/21
 */
public class BluetoothFragment extends BaseFragment<BluetoothPresenter> implements BluetoothContract.View {
    private final int REQUEST_ENABLE_BLE = 100;
    private final int PERMISSION_REQUEST_COARSE_LOCATION = 10;
    @BindView(R.id.connected_recycle_view)
    RecyclerView mConnectedRecyclerView;

    RadarView mRadarView;
    BleDeviceAdapter mAdapter;
    List<String> mFeedArticleDataList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bluetooth;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void initView() {
        super.initView();
        initRecyclerView();
        mRadarView = getActivity().findViewById(R.id.radar);

    }

    @Override
    public void onDestroyView() {
        //切换到其他页面时，监听置空，取消这个监听
        //先置空 listener，避免切换fragment关闭扫描时，与presenter 中 detachView() 形成死循环
        mRadarView.setBleScanListener(null);
        super.onDestroyView();

    }

    /**
     * 设置RecyclerView
     */
    private void initRecyclerView() {
        mFeedArticleDataList = new ArrayList<>();
        mConnectedRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mConnectedRecyclerView.setHasFixedSize(true);
        mAdapter = new BleDeviceAdapter(R.layout.item_ble_device, mFeedArticleDataList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> startConnectBle(view, position));
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> clickChildEvent(view, position));
        TextView pairTitle = (TextView) View.inflate(_mActivity, R.layout.indicator, null);
        pairTitle.setText(_mActivity.getResources().getString(R.string.pair_device_title));
        pairTitle.setTextColor(_mActivity.getResources().getColor(R.color.white));
        pairTitle.setTextSize(16);
        mAdapter.addHeaderView(pairTitle);
        mConnectedRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 给 子view设置点击事件
     *
     * @param view
     * @param position
     */
    private void clickChildEvent(View view, int position) {
        switch (view.getId()) {
            case R.id.item_device_info_view:
                System.out.println("圈圈被点击了");
                break;
            default:
                break;
        }
    }

    /**
     * 开始连接此蓝牙设备
     *
     * @param view
     * @param position
     */
    private void startConnectBle(View view, int position) {
        System.out.println("开始连接蓝牙设备 + " + position);
    }

    @Override
    public void showDeviceUnSupportBleTips() {
        TipsDialog tipsDialog = new TipsDialog(_mActivity) {
            @Override
            public String getContent() {
                return MicroPortApp.getInstance().getResources().getString(R.string.un_support_ble);
            }
        };
        tipsDialog.show();
    }

    @Override
    public void showBleClosedTips() {
        TipsDialog tipsDialog = new TipsDialog(_mActivity) {
            @Override
            public String getContent() {
                return MicroPortApp.getInstance().getResources().getString(R.string.ble_closed);
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
    public void requestPermissionForScan() {
        // TODO: 2018/9/20 要测试不同的机型 ，小米，华为，oppo的处理可能不同
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (_mActivity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                System.out.println("未授权");
                _mActivity.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
            } else {
                System.out.println("已授权");
                mPresenter.doScan(true);

                //当获取到权限后 再设置radarView的扫描监听，不然无意义
                mRadarView.setBleScanListener(on -> mPresenter.doScan(on));
            }
        }
    }

    @Override
    public void Scaning(boolean start) {
        if (start) {
            mRadarView.setStateTrue();
        } else {
            mRadarView.setStateFalse();
        }
        //isDetached()，当显示的不是蓝牙fragment时，radar 文字改为 “训练”
        mRadarView.setText(start ? R.string.searching : (isDetached() ? R.string.training : R.string.search_device));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_ENABLE_BLE:
                if (resultCode == Activity.RESULT_OK) {
                } else {
                    CommonUtils.showMessage(_mActivity, "主人不让打开蓝牙");
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_COARSE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mPresenter.doScan(true);
                    //当获取到权限后 再设置radarView的 扫描监听 ，不然无意义
                    mRadarView.setBleScanListener(on -> mPresenter.doScan(on));
                } else {
                    System.out.println("扫描权限请求失败");
                }
                break;
            default:
                break;
        }
    }
}
