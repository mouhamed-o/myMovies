package fr.esiea.mymovie.data.remote.api;

import androidx.lifecycle.LiveData;
import fr.esiea.mymovie.data.local.model.Movie;
import fr.esiea.mymovie.data.local.model.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("page") int page);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("page") int page);

    @GET("movie/{id}?append_to_response=videos,credits,reviews")
    LiveData<ApiResponse<Movie>> getMovieDetails(@Path("id") long id);
}
