package com.weichuang.sensor.di.module;

import android.app.Activity;
import com.weichuang.sensor.ui.activity.SplashActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(
  subcomponents =
      AbstractAllActivityModule_ContributesSplashActivityInjector.SplashActivitySubcomponent.class
)
public abstract class AbstractAllActivityModule_ContributesSplashActivityInjector {
  private AbstractAllActivityModule_ContributesSplashActivityInjector() {}

  @Binds
  @IntoMap
  @ActivityKey(SplashActivity.class)
  abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(
      SplashActivitySubcomponent.Builder builder);

  @Subcomponent(modules = SplashActivityModule.class)
  public interface SplashActivitySubcomponent extends AndroidInjector<SplashActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SplashActivity> {}
  }
}
