// Generated code from Butter Knife. Do not modify!
package com.weichuang.sensor.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.lottie.LottieAnimationView;
import com.weichuang.sensor.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SplashActivity_ViewBinding implements Unbinder {
  private SplashActivity target;

  @UiThread
  public SplashActivity_ViewBinding(SplashActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SplashActivity_ViewBinding(SplashActivity target, View source) {
    this.target = target;

    target.mOneAnimation = Utils.findRequiredViewAsType(source, R.id.one_animation, "field 'mOneAnimation'", LottieAnimationView.class);
    target.mTwoAnimation = Utils.findRequiredViewAsType(source, R.id.two_animation, "field 'mTwoAnimation'", LottieAnimationView.class);
    target.mThreeAnimation = Utils.findRequiredViewAsType(source, R.id.three_animation, "field 'mThreeAnimation'", LottieAnimationView.class);
    target.mFourAnimation = Utils.findRequiredViewAsType(source, R.id.four_animation, "field 'mFourAnimation'", LottieAnimationView.class);
    target.mFiveAnimation = Utils.findRequiredViewAsType(source, R.id.five_animation, "field 'mFiveAnimation'", LottieAnimationView.class);
    target.mSixAnimation = Utils.findRequiredViewAsType(source, R.id.six_animation, "field 'mSixAnimation'", LottieAnimationView.class);
    target.mSevenAnimation = Utils.findRequiredViewAsType(source, R.id.seven_animation, "field 'mSevenAnimation'", LottieAnimationView.class);
    target.mEightAnimation = Utils.findRequiredViewAsType(source, R.id.eight_animation, "field 'mEightAnimation'", LottieAnimationView.class);
    target.mNineAnimation = Utils.findRequiredViewAsType(source, R.id.nine_animation, "field 'mNineAnimation'", LottieAnimationView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SplashActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mOneAnimation = null;
    target.mTwoAnimation = null;
    target.mThreeAnimation = null;
    target.mFourAnimation = null;
    target.mFiveAnimation = null;
    target.mSixAnimation = null;
    target.mSevenAnimation = null;
    target.mEightAnimation = null;
    target.mNineAnimation = null;
  }
}
