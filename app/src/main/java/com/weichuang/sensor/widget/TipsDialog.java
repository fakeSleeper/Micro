package com.weichuang.sensor.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.weichuang.sensor.R;

/**
 * desc : 提示窗口
 *
 * @author Forrest
 * @date 2018/9/13.
 */
public abstract class TipsDialog extends Dialog {

    public TipsDialog(Context context) {
        super(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_tips);
        //提示内容，抽取出去
        TextView content = findViewById(R.id.tips_content);
        content.setText(getContent());
        findViewById(R.id.tips_ok).setOnClickListener(v -> {
            dismiss();
        });
    }

    public abstract String getContent();
}
