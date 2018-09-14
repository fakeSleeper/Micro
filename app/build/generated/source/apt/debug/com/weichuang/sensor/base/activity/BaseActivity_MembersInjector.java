package com.weichuang.sensor.base.activity;

import com.weichuang.sensor.base.presenter.AbstractPresenter;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class BaseActivity_MembersInjector<T extends AbstractPresenter>
    implements MembersInjector<BaseActivity<T>> {
  private final Provider<T> mPresenterProvider;

  public BaseActivity_MembersInjector(Provider<T> mPresenterProvider) {
    this.mPresenterProvider = mPresenterProvider;
  }

  public static <T extends AbstractPresenter> MembersInjector<BaseActivity<T>> create(
      Provider<T> mPresenterProvider) {
    return new BaseActivity_MembersInjector<T>(mPresenterProvider);
  }

  @Override
  public void injectMembers(BaseActivity<T> instance) {
    injectMPresenter(instance, mPresenterProvider.get());
  }

  public static <T extends AbstractPresenter> void injectMPresenter(
      BaseActivity<T> instance, T mPresenter) {
    instance.mPresenter = mPresenter;
  }
}
