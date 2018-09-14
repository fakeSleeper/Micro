package com.weichuang.sensor.di.module;

import com.weichuang.sensor.app.MicroPortApp;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppModule_ProvideApplicationContextFactory implements Factory<MicroPortApp> {
  private final AppModule module;

  public AppModule_ProvideApplicationContextFactory(AppModule module) {
    this.module = module;
  }

  @Override
  public MicroPortApp get() {
    return Preconditions.checkNotNull(
        module.provideApplicationContext(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static AppModule_ProvideApplicationContextFactory create(AppModule module) {
    return new AppModule_ProvideApplicationContextFactory(module);
  }

  public static MicroPortApp proxyProvideApplicationContext(AppModule instance) {
    return Preconditions.checkNotNull(
        instance.provideApplicationContext(),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
