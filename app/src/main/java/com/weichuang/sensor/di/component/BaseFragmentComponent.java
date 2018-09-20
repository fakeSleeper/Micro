package com.weichuang.sensor.di.component;

import com.weichuang.sensor.base.fragment.BaseFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * desc:
 *
 * @author: Forrest
 * @date: 2018/9/20
 */
@Subcomponent(modules = AndroidInjectionModule.class)
public interface BaseFragmentComponent extends AndroidInjector<BaseFragment> {
    @Subcomponent.Builder
    abstract class BaseBuilder extends AndroidInjector.Builder<BaseFragment> {

    }
}
