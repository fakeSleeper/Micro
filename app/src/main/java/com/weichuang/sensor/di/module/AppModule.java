package com.weichuang.sensor.di.module;

import com.weichuang.sensor.app.MicroPortApp;
import com.weichuang.sensor.core.HttpHelper;
import com.weichuang.sensor.core.HttpHelperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * desc:
 *
 * @author: Forrest
 * @date: 2018/8/15
 */
@Module
public class AppModule {
    private final MicroPortApp application;

    public AppModule(MicroPortApp app) {
        application = app;
    }

    @Provides
    @Singleton
    MicroPortApp provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper ProvideHttpHelper() {

        return new HttpHelperImpl();
    }
}
