package com.weichuang.sensor.ui.activity;

import android.content.Intent;

import com.airbnb.lottie.LottieAnimationView;
import com.weichuang.sensor.R;
import com.weichuang.sensor.app.MicroPortApp;
import com.weichuang.sensor.base.activity.BaseActivity;
import com.weichuang.sensor.contract.SplashContract;
import com.weichuang.sensor.presenter.SplashPresenter;
import com.weichuang.sensor.utils.StatusBarUtil;
import butterknife.BindView;

/**
 * 闪屏界面
 *
 * @author Forrest
 * @date 2018/8/12.
 */

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {


    @BindView(R.id.one_animation)
    LottieAnimationView mOneAnimation;
    @BindView(R.id.two_animation)
    LottieAnimationView mTwoAnimation;
    @BindView(R.id.three_animation)
    LottieAnimationView mThreeAnimation;
    @BindView(R.id.four_animation)
    LottieAnimationView mFourAnimation;
    @BindView(R.id.five_animation)
    LottieAnimationView mFiveAnimation;
    @BindView(R.id.six_animation)
    LottieAnimationView mSixAnimation;
    @BindView(R.id.seven_animation)
    LottieAnimationView mSevenAnimation;
    @BindView(R.id.eight_animation)
    LottieAnimationView mEightAnimation;
    @BindView(R.id.nine_animation)
    LottieAnimationView mNineAnimation;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initToolbar() {
        if (!MicroPortApp.isFirstRun) {
            jumpToMain();
            return;
        }
        MicroPortApp.isFirstRun = false;
        //沉浸式状态栏
        StatusBarUtil.immersive(this);
    }


    @Override
    protected void initEventAndData() {
        startAnimation(mOneAnimation, "M.json");
        startAnimation(mTwoAnimation, "I.json");
        startAnimation(mThreeAnimation, "C.json");
        startAnimation(mFourAnimation, "R.json");
        startAnimation(mFiveAnimation, "O.json");
        startAnimation(mSixAnimation, "P.json");
        startAnimation(mSevenAnimation, "O.json");
        startAnimation(mEightAnimation, "R.json");
        startAnimation(mNineAnimation, "T.json");

    }

    @Override
    public void jumpToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        cancelAnimation();
        super.onDestroy();
    }

    private void cancelAnimation() {
        cancelAnimation(mOneAnimation);
        cancelAnimation(mTwoAnimation);
        cancelAnimation(mThreeAnimation);
        cancelAnimation(mFourAnimation);
        cancelAnimation(mFiveAnimation);
        cancelAnimation(mSixAnimation);
        cancelAnimation(mSevenAnimation);
        cancelAnimation(mEightAnimation);
        cancelAnimation(mNineAnimation);
    }

    private void startAnimation( LottieAnimationView mLottieAnimationView, String animationName) {
        mLottieAnimationView.setAnimation(animationName);
        mLottieAnimationView.playAnimation();
    }

    private void cancelAnimation(LottieAnimationView mLottieAnimationView) {
        if (mLottieAnimationView != null) {
            mLottieAnimationView.cancelAnimation();
        }
    }
}
