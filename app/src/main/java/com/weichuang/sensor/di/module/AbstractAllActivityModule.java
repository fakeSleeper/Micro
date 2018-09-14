package com.weichuang.sensor.di.module;

import com.weichuang.sensor.di.component.BaseActivityComponent;
import com.weichuang.sensor.ui.activity.MainActivity;
import com.weichuang.sensor.ui.activity.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author
 * @date
 */

@Module(subcomponents = {BaseActivityComponent.class})
public abstract class AbstractAllActivityModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributesMainActivityInjector();

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity contributesSplashActivityInjector();



}
