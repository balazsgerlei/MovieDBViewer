package hu.gerlotdev.moviedbviewer.domain.usecase;

import javax.inject.Inject;

import hu.gerlotdev.moviedbviewer.data.model.MoviePage;
import hu.gerlotdev.moviedbviewer.data.network.MovieApi;
import hu.gerlotdev.moviedbviewer.data.network.NetworkManager;
import hu.gerlotdev.moviedbviewer.domain.executor.PostExecutionThread;
import hu.gerlotdev.moviedbviewer.presentation.MovieDBViewerApplication;
import io.reactivex.Single;

public class GetMoviesUseCase extends UseCase<MoviePage> {

    @Inject
    MovieApi movieApi;

    private String query;

    public GetMoviesUseCase(PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        MovieDBViewerApplication.applicationComponent.inject(this);
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    protected Single<MoviePage> buildUseCaseObservable() {
        return movieApi.getMovies(NetworkManager.API_KEY, query);
    }

}
