package com.weichuang.sensor.di.component;

import android.app.Activity;
import com.weichuang.sensor.app.MicroPortApp;
import com.weichuang.sensor.app.MicroPortApp_MembersInjector;
import com.weichuang.sensor.base.activity.BaseActivity_MembersInjector;
import com.weichuang.sensor.di.module.AbstractAllActivityModule_ContributesMainActivityInjector;
import com.weichuang.sensor.di.module.AbstractAllActivityModule_ContributesSplashActivityInjector;
import com.weichuang.sensor.di.module.AppModule;
import com.weichuang.sensor.di.module.AppModule_ProvideApplicationContextFactory;
import com.weichuang.sensor.presenter.SplashPresenter;
import com.weichuang.sensor.ui.activity.MainActivity;
import com.weichuang.sensor.ui.activity.SplashActivity;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.DispatchingAndroidInjector_Factory;
import dagger.internal.DoubleCheck;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import java.util.Map;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerAppComponent implements AppComponent {
  private Provider<
          AbstractAllActivityModule_ContributesMainActivityInjector.MainActivitySubcomponent
              .Builder>
      mainActivitySubcomponentBuilderProvider;

  private Provider<
          AbstractAllActivityModule_ContributesSplashActivityInjector.SplashActivitySubcomponent
              .Builder>
      splashActivitySubcomponentBuilderProvider;

  private Provider<MicroPortApp> provideApplicationContextProvider;

  private DaggerAppComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  private Map<Class<? extends Activity>, Provider<AndroidInjector.Factory<? extends Activity>>>
      getMapOfClassOfAndProviderOfFactoryOf() {
    return MapBuilder
        .<Class<? extends Activity>, Provider<AndroidInjector.Factory<? extends Activity>>>
            newMapBuilder(2)
        .put(MainActivity.class, (Provider) mainActivitySubcomponentBuilderProvider)
        .put(SplashActivity.class, (Provider) splashActivitySubcomponentBuilderProvider)
        .build();
  }

  private DispatchingAndroidInjector<Activity> getDispatchingAndroidInjectorOfActivity() {
    return DispatchingAndroidInjector_Factory.newDispatchingAndroidInjector(
        getMapOfClassOfAndProviderOfFactoryOf());
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.mainActivitySubcomponentBuilderProvider =
        new Provider<
            AbstractAllActivityModule_ContributesMainActivityInjector.MainActivitySubcomponent
                .Builder>() {
          @Override
          public AbstractAllActivityModule_ContributesMainActivityInjector.MainActivitySubcomponent
                  .Builder
              get() {
            return new MainActivitySubcomponentBuilder();
          }
        };
    this.splashActivitySubcomponentBuilderProvider =
        new Provider<
            AbstractAllActivityModule_ContributesSplashActivityInjector.SplashActivitySubcomponent
                .Builder>() {
          @Override
          public AbstractAllActivityModule_ContributesSplashActivityInjector
                  .SplashActivitySubcomponent.Builder
              get() {
            return new SplashActivitySubcomponentBuilder();
          }
        };
    this.provideApplicationContextProvider =
        DoubleCheck.provider(AppModule_ProvideApplicationContextFactory.create(builder.appModule));
  }

  @Override
  public void inject(MicroPortApp microPortApp) {
    injectMicroPortApp(microPortApp);
  }

  @Override
  public MicroPortApp getContext() {
    return provideApplicationContextProvider.get();
  }

  private MicroPortApp injectMicroPortApp(MicroPortApp instance) {
    MicroPortApp_MembersInjector.injectMActivityDispatchingAndroidInjector(
        instance, getDispatchingAndroidInjectorOfActivity());
    return instance;
  }

  public static final class Builder {
    private AppModule appModule;

    private Builder() {}

    public AppComponent build() {
      if (appModule == null) {
        throw new IllegalStateException(AppModule.class.getCanonicalName() + " must be set");
      }
      return new DaggerAppComponent(this);
    }

    public Builder appModule(AppModule appModule) {
      this.appModule = Preconditions.checkNotNull(appModule);
      return this;
    }
  }

  private final class MainActivitySubcomponentBuilder
      extends AbstractAllActivityModule_ContributesMainActivityInjector.MainActivitySubcomponent
          .Builder {
    private MainActivity seedInstance;

    @Override
    public AbstractAllActivityModule_ContributesMainActivityInjector.MainActivitySubcomponent
        build() {
      if (seedInstance == null) {
        throw new IllegalStateException(MainActivity.class.getCanonicalName() + " must be set");
      }
      return new MainActivitySubcomponentImpl(this);
    }

    @Override
    public void seedInstance(MainActivity arg0) {
      this.seedInstance = Preconditions.checkNotNull(arg0);
    }
  }

  private final class MainActivitySubcomponentImpl
      implements AbstractAllActivityModule_ContributesMainActivityInjector
          .MainActivitySubcomponent {
    private MainActivitySubcomponentImpl(MainActivitySubcomponentBuilder builder) {}

    @Override
    public void inject(MainActivity arg0) {}
  }

  private final class SplashActivitySubcomponentBuilder
      extends AbstractAllActivityModule_ContributesSplashActivityInjector.SplashActivitySubcomponent
          .Builder {
    private SplashActivity seedInstance;

    @Override
    public AbstractAllActivityModule_ContributesSplashActivityInjector.SplashActivitySubcomponent
        build() {
      if (seedInstance == null) {
        throw new IllegalStateException(SplashActivity.class.getCanonicalName() + " must be set");
      }
      return new SplashActivitySubcomponentImpl(this);
    }

    @Override
    public void seedInstance(SplashActivity arg0) {
      this.seedInstance = Preconditions.checkNotNull(arg0);
    }
  }

  private final class SplashActivitySubcomponentImpl
      implements AbstractAllActivityModule_ContributesSplashActivityInjector
          .SplashActivitySubcomponent {
    private SplashActivitySubcomponentImpl(SplashActivitySubcomponentBuilder builder) {}

    @Override
    public void inject(SplashActivity arg0) {
      injectSplashActivity(arg0);
    }

    private SplashActivity injectSplashActivity(SplashActivity instance) {
      BaseActivity_MembersInjector.injectMPresenter(instance, new SplashPresenter());
      return instance;
    }
  }
}
