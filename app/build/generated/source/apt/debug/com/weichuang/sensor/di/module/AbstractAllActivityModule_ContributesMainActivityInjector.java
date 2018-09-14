package com.weichuang.sensor.di.module;

import android.app.Activity;
import com.weichuang.sensor.ui.activity.MainActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(
  subcomponents =
      AbstractAllActivityModule_ContributesMainActivityInjector.MainActivitySubcomponent.class
)
public abstract class AbstractAllActivityModule_ContributesMainActivityInjector {
  private AbstractAllActivityModule_ContributesMainActivityInjector() {}

  @Binds
  @IntoMap
  @ActivityKey(MainActivity.class)
  abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(
      MainActivitySubcomponent.Builder builder);

  @Subcomponent(modules = MainActivityModule.class)
  public interface MainActivitySubcomponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {}
  }
}
