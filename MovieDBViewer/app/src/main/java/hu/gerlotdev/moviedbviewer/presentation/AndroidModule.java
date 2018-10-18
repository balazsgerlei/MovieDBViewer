package hu.gerlotdev.moviedbviewer.presentation;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.gerlotdev.moviedbviewer.domain.usecase.GetMoviesUseCase;

@Module
public class AndroidModule {

    private final MovieDBViewerApplication application;

    public AndroidModule(MovieDBViewerApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    UIThread provideUIThread() {
        return new UIThread();
    }

    @Provides
    @Singleton
    GetMoviesUseCase provideGetMoviesUseCase(UIThread uiThread) {
        return new GetMoviesUseCase(uiThread);
    }

}
