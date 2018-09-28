package com.weichuang.sensor.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.weichuang.sensor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc :
 *
 * @author Forrest
 * @date 2018/9/28.
 */

public class BleListViewHolder extends BaseViewHolder {

    @BindView(R.id.item_device_info_view)
    ImageView mItemSearchPagerLikeIv;
    @BindView(R.id.item_device_name_view)
    TextView mItemSearchPagerTitle;
    public BleListViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
