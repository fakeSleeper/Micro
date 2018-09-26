package com.weichuang.sensor.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.weichuang.sensor.R;

/**
 * desc: 搜索按钮 特效
 *
 * @author: Forrest
 * @date: 2018/9/25
 */
public class RadarView extends AppCompatTextView {
    /**
     * 思路：我们首先初始化画笔，并且获取到控件的宽高，在onMeasure()中设置铺满，然后在onDraw()方法中绘制四个静态圆和一个渐变圆，
     * 我们通过Matrix矩阵来让他不停的旋转就达到我们想要的效果了
     */
    private Paint mPaintLine, mPaintCircle;
    private int w, h;
    // 动画
    private Matrix matrix;
    // 旋转角度
    private int start;
    // Handler定时动画
    private Handler handler = new Handler();

    //是否正在搜索中
    private boolean isSearching =false;

    private Runnable run = new Runnable() {
        @Override
        public void run() {
            start = start + 2;
            matrix = new Matrix();
            // 参数：旋转角度，围绕点坐标的x,y坐标点
            matrix.postRotate(start, w / 2, h / 2);
            // 刷新重绘
            RadarView.this.invalidate();
            // 继续循环
            handler.postDelayed(run, 60);
        }
    };

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mPaintLine = new Paint();
        mPaintLine.setColor(getContext().getResources().getColor(R.color.blue_btn));
        mPaintLine.setAntiAlias(true);
        mPaintLine.setStyle(Paint.Style.STROKE);

        mPaintCircle = new Paint();
        mPaintCircle.setAntiAlias(true);
        matrix = new Matrix();
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        w = right - left;
        h = bottom - top;
        // 旋转
        //handler.post(run);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 画一个圆形
        canvas.drawCircle(w / 2, h / 2, w / 2, mPaintLine);

        /*
        // 绘制渐变圆
        Shader mShader = new SweepGradient(w / 2, h / 2, Color.TRANSPARENT,
                getContext().getResources().getColor(R.color.blue_btn_55));
        // 绘制时渐变
        mPaintCircle.setShader(mShader);
        // 增加旋转动画，使用矩阵实现
        canvas.concat(matrix); // 前置动画
        canvas.drawCircle(w / 2, h / 2, w /2, mPaintCircle);
        */


    }
    public boolean getState() {
        return isSearching;
    }

    public void setState(boolean state) {
        this.isSearching = state;
    }
}
