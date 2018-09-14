package com.weichuang.sensor.app;

import android.app.Activity;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class MicroPortApp_MembersInjector implements MembersInjector<MicroPortApp> {
  private final Provider<DispatchingAndroidInjector<Activity>>
      mActivityDispatchingAndroidInjectorProvider;

  public MicroPortApp_MembersInjector(
      Provider<DispatchingAndroidInjector<Activity>> mActivityDispatchingAndroidInjectorProvider) {
    this.mActivityDispatchingAndroidInjectorProvider = mActivityDispatchingAndroidInjectorProvider;
  }

  public static MembersInjector<MicroPortApp> create(
      Provider<DispatchingAndroidInjector<Activity>> mActivityDispatchingAndroidInjectorProvider) {
    return new MicroPortApp_MembersInjector(mActivityDispatchingAndroidInjectorProvider);
  }

  @Override
  public void injectMembers(MicroPortApp instance) {
    injectMActivityDispatchingAndroidInjector(
        instance, mActivityDispatchingAndroidInjectorProvider.get());
  }

  public static void injectMActivityDispatchingAndroidInjector(
      MicroPortApp instance,
      DispatchingAndroidInjector<Activity> mActivityDispatchingAndroidInjector) {
    instance.mActivityDispatchingAndroidInjector = mActivityDispatchingAndroidInjector;
  }
}
