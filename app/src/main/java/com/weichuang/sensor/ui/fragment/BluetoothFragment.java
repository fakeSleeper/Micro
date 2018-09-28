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

import com.weichuang.sensor.R;
import com.weichuang.sensor.app.MicroPortApp;
import com.weichuang.sensor.base.fragment.BaseFragment;
import com.weichuang.sensor.contract.BluetoothContract;
import com.weichuang.sensor.presenter.BluetoothPresenter;
import com.weichuang.sensor.ui.adapter.BleDeviceAdapter;
import com.weichuang.sensor.utils.CommonUtils;
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

    }

    /**
     * 设置RecyclerView
     */
    private void initRecyclerView() {
        mFeedArticleDataList = new ArrayList<>();
        mFeedArticleDataList.add("唐三藏");
        mFeedArticleDataList.add("孙悟空");
        mFeedArticleDataList.add("猪八戒");
        mFeedArticleDataList.add("沙和尚");
        mConnectedRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));

        mConnectedRecyclerView.setHasFixedSize(true);

        mAdapter = new BleDeviceAdapter(R.layout.item_ble_device, mFeedArticleDataList);

        mConnectedRecyclerView.setAdapter(mAdapter);
        //mFeedArticleDataList = new ArrayList<>();
//        mAdapter = new BleDeviceAdapter(R.layout.item_search_pager, mFeedArticleDataList);
//        mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
//        mAdapter.setOnItemChildClickListener((adapter, view, position) -> clickChildEvent(view, position));
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
//        mRecyclerView.setHasFixedSize(true);
//        //add head banner
//        LinearLayout mHeaderGroup = ((LinearLayout) LayoutInflater.from(_mActivity).inflate(R.layout.head_banner, null));
//        mBanner = mHeaderGroup.findViewById(R.id.head_banner);
//        mHeaderGroup.removeView(mBanner);
//        mAdapter.addHeaderView(mBanner);
//        mRecyclerView.setAdapter(mAdapter);
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
    public void showBleClosedTips() {
        TipsDialog tipsDialog = new TipsDialog(getActivity()) {
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
            if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                System.out.println("未授权");
                getActivity().requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
            } else {
                System.out.println("已授权");
                mPresenter.doScan(true);
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_ENABLE_BLE:
                if (resultCode == Activity.RESULT_OK) {
                } else {
                    CommonUtils.showMessage(getActivity(), "主人不让打开蓝牙");
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
                } else {
                    System.out.println("扫描权限请求失败");
                }
                break;
            default:
                break;
        }
    }
}
