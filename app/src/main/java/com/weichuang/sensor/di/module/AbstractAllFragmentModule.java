package com.weichuang.sensor.di.module;

import com.weichuang.sensor.di.component.BaseFragmentComponent;
import com.weichuang.sensor.ui.fragment.HospitalFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * desc:
 *
 * @author: Forrest
 * @date: 2018/9/20
 */
@Module(subcomponents =  BaseFragmentComponent.class)
public abstract class AbstractAllFragmentModule {
    @ContributesAndroidInjector(modules = HospitalFragmentModule.class)
    abstract HospitalFragment contributesHospitalFragmentInjector();
}
