package com.weichuang.sensor.di.module;

import com.weichuang.sensor.core.HttpHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppModule_ProvideHttpHelperFactory implements Factory<HttpHelper> {
  private final AppModule module;

  public AppModule_ProvideHttpHelperFactory(AppModule module) {
    this.module = module;
  }

  @Override
  public HttpHelper get() {
    return Preconditions.checkNotNull(
        module.ProvideHttpHelper(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static AppModule_ProvideHttpHelperFactory create(AppModule module) {
    return new AppModule_ProvideHttpHelperFactory(module);
  }

  public static HttpHelper proxyProvideHttpHelper(AppModule instance) {
    return Preconditions.checkNotNull(
        instance.ProvideHttpHelper(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
