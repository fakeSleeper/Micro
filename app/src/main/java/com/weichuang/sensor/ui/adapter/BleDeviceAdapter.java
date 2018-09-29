package com.weichuang.sensor.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.weichuang.sensor.R;
import com.weichuang.sensor.ui.viewholder.BleListViewHolder;

import java.util.List;

/**
 * desc:
 *
 * @author: Forrest
 * @date: 2018/9/28
 */
public class BleDeviceAdapter extends BaseQuickAdapter<String, BleListViewHolder> {


    public BleDeviceAdapter(int item_ble_device, List<String> feedArticleDataList) {
        super(item_ble_device, feedArticleDataList);
    }


    @Override
    protected void convert(BleListViewHolder holder, String item) {
        holder.setText(R.id.item_device_name_view, item);
        //添加点击事件
        holder.addOnClickListener(R.id.item_device_info_view);

    }
}
