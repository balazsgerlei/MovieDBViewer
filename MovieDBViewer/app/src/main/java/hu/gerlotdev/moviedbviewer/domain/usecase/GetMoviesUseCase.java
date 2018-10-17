package hu.gerlotdev.moviedbviewer.domain.usecase;

import hu.gerlotdev.moviedbviewer.data.model.MoviePage;
import hu.gerlotdev.moviedbviewer.data.network.MovieApi;
import hu.gerlotdev.moviedbviewer.data.network.NetworkManager;
import hu.gerlotdev.moviedbviewer.domain.executor.PostExecutionThread;
import io.reactivex.Single;

public class GetMoviesUseCase extends UseCase<MoviePage> {

    private MovieApi movieApi;
    private String query;

    public GetMoviesUseCase(PostExecutionThread postExecutionThread, MovieApi movieApi, String query) {
        super(postExecutionThread);
        this.movieApi = movieApi;
        this.query = query;
    }

    @Override
    protected Single<MoviePage> buildUseCaseObservable() {
        return movieApi.getMovies(NetworkManager.API_KEY, query);
    }

}
