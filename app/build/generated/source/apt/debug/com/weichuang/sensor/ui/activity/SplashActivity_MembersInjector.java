package com.weichuang.sensor.ui.activity;

import com.weichuang.sensor.base.activity.BaseActivity_MembersInjector;
import com.weichuang.sensor.presenter.SplashPresenter;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class SplashActivity_MembersInjector implements MembersInjector<SplashActivity> {
  private final Provider<SplashPresenter> mPresenterProvider;

  public SplashActivity_MembersInjector(Provider<SplashPresenter> mPresenterProvider) {
    this.mPresenterProvider = mPresenterProvider;
  }

  public static MembersInjector<SplashActivity> create(
      Provider<SplashPresenter> mPresenterProvider) {
    return new SplashActivity_MembersInjector(mPresenterProvider);
  }

  @Override
  public void injectMembers(SplashActivity instance) {
    BaseActivity_MembersInjector.injectMPresenter(instance, mPresenterProvider.get());
  }
}
