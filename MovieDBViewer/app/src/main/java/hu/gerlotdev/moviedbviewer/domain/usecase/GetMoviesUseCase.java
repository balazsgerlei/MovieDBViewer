package hu.gerlotdev.moviedbviewer.domain.usecase;

import java.util.List;

import javax.inject.Inject;

import hu.gerlotdev.moviedbviewer.data.model.Movie;
import hu.gerlotdev.moviedbviewer.data.model.MoviePage;
import hu.gerlotdev.moviedbviewer.data.network.MovieApi;
import hu.gerlotdev.moviedbviewer.data.network.NetworkManager;
import hu.gerlotdev.moviedbviewer.domain.executor.PostExecutionThread;
import hu.gerlotdev.moviedbviewer.presentation.MovieDBViewerApplication;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class GetMoviesUseCase extends UseCase<List<Movie>> {

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
    protected Single<List<Movie>> buildUseCaseObservable() {
        return movieApi.getMovies(NetworkManager.API_KEY, query)
                .map(new Function<MoviePage, List<Movie>>() {
                    @Override
                    public List<Movie> apply(MoviePage moviePage) throws Exception {
                        return moviePage.getResults(); // Transform Single<MoviePage> to Single<List<Movie>>
                    }
                })
                .toObservable() // Transform Single<List<Movie>> to Observable<List<Movie>>
                .flatMap(new Function<List<Movie>, ObservableSource<Movie>>() {
                    @Override
                    public ObservableSource<Movie> apply(List<Movie> movies) throws Exception {
                        return Observable.fromIterable(movies); // Transform Observable<List<Movie>> to Observable<Movie> which emits items one at a time
                    }
                })
                .flatMap(new Function<Movie, ObservableSource<? extends Movie>>() {
                    @Override
                    public ObservableSource<? extends Movie> apply(Movie movie) throws Exception {
                        return movieApi.getMovie(movie.getId(), NetworkManager.API_KEY).toObservable(); // Fire an API request for each Movie item to get the full details for that movie
                    }
                }).toList(); // Transform Observable<Movie> back into Single<List<Movie>> which contains all emitted items in the list that it emits - now with full details for each movie
    }

}
