package com.weichuang.sensor.di.component;

import com.weichuang.sensor.app.MicroPortApp;
import com.weichuang.sensor.di.module.AbstractAllActivityModule;
import com.weichuang.sensor.di.module.AbstractAllFragmentModule;
import com.weichuang.sensor.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * desc:
 *
 * @author: Forrest
 * @date: 2018/8/15
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class,AppModule.class,AbstractAllActivityModule.class, AbstractAllFragmentModule.class})
public interface AppComponent {

    void inject(MicroPortApp microPortApp);

    /**
     * 向外暴露 可以提供的依赖
     * @return
     */
    MicroPortApp getContext();
}
