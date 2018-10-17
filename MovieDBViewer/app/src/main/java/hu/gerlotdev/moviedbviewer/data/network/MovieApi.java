package hu.gerlotdev.moviedbviewer.data.network;

import java.util.List;

import hu.gerlotdev.moviedbviewer.data.entity.Movie;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("/movie")
    Single<List<Movie>> getMovies(@Query("api_key") String apiKey, @Query("query") String query);

    @GET("/movie/{id}")
    Single<Movie> getMovie(@Path("id") int id, @Query("api_key") String apiKey);

}
