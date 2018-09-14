package com.weichuang.sensor.presenter;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class SplashPresenter_Factory implements Factory<SplashPresenter> {
  private static final SplashPresenter_Factory INSTANCE = new SplashPresenter_Factory();

  @Override
  public SplashPresenter get() {
    return new SplashPresenter();
  }

  public static SplashPresenter_Factory create() {
    return INSTANCE;
  }
}
