package hu.gerlotdev.moviedbviewer.presentation;

import javax.inject.Singleton;

import dagger.Component;
import hu.gerlotdev.moviedbviewer.data.network.NetworkModule;
import hu.gerlotdev.moviedbviewer.domain.usecase.GetMoviesUseCase;
import hu.gerlotdev.moviedbviewer.presentation.main.MainFragment;

@Singleton
@Component(modules = {AndroidModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(MainFragment mainFragment);

    void inject(GetMoviesUseCase getMoviesUseCase);

}
