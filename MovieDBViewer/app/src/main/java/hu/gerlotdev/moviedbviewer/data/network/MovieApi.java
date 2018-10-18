package hu.gerlotdev.moviedbviewer.data.network;

import hu.gerlotdev.moviedbviewer.data.model.Movie;
import hu.gerlotdev.moviedbviewer.data.model.MoviePage;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("search/movie")
    Single<MoviePage> getMovies(@Query("api_key") String apiKey, @Query("query") String query);

    @GET("movie/{id}")
    Single<Movie> getMovie(@Path("id") long id, @Query("api_key") String apiKey);

}
