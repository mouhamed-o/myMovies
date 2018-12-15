package fr.esiea.mymovie.data.remote.paging;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;
import fr.esiea.mymovie.data.local.model.Movie;
import fr.esiea.mymovie.data.local.model.MoviesResponse;
import fr.esiea.mymovie.data.local.model.Resource;
import fr.esiea.mymovie.data.remote.api.MovieService;
import fr.esiea.mymovie.ui.movieslist.MoviesFilterType;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePageKeyedDataSource extends PageKeyedDataSource<Integer, Movie> {

    private static final int FIRST_PAGE = 1;
    private final MovieService movieService;
    private final Executor networkExecutor;
    private final MoviesFilterType sortBy;
    public MutableLiveData<Resource> networkState = new MutableLiveData<>();
    public RetryCallback retryCallback = null;

    public MoviePageKeyedDataSource(MovieService movieService,
                                    Executor networkExecutor, MoviesFilterType sortBy) {
        this.movieService = movieService;
        this.networkExecutor = networkExecutor;
        this.sortBy = sortBy;
    }

    @Override
    public void loadInitial(@NonNull final LoadInitialParams<Integer> params,
                            @NonNull final LoadInitialCallback<Integer, Movie> callback) {
        networkState.postValue(Resource.loading(null));

        // load data from API
        Call<MoviesResponse> request;
        if (sortBy == MoviesFilterType.POPULAR) {
            request = movieService.getPopularMovies(FIRST_PAGE);
        } else {
            request = movieService.getTopRatedMovies(FIRST_PAGE);
        }
        try {
            Response<MoviesResponse> response = request.execute();
            MoviesResponse data = response.body();
            List<Movie> movieList =
                    data != null ? data.getMovies() : Collections.<Movie>emptyList();

            retryCallback = null;
            networkState.postValue(Resource.success(null));
            callback.onResult(movieList, null, FIRST_PAGE + 1);
        } catch (IOException e) {
            retryCallback = new RetryCallback() {
                @Override
                public void invoke() {
                    networkExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            loadInitial(params, callback);
                        }
                    });

                }
            };
            networkState.postValue(Resource.error(e.getMessage(), null));
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params,
                           @NonNull LoadCallback<Integer, Movie> callback) {
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params,
                          @NonNull final LoadCallback<Integer, Movie> callback) {
        networkState.postValue(Resource.loading(null));
        Call<MoviesResponse> request;
        if (sortBy == MoviesFilterType.POPULAR) {
            request = movieService.getPopularMovies(params.key);
        } else {
            request = movieService.getTopRatedMovies(params.key);
        }

        request.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()) {
                    MoviesResponse data = response.body();
                    List<Movie> movieList =
                            data != null ? data.getMovies() : Collections.<Movie>emptyList();

                    retryCallback = null;
                    callback.onResult(movieList, params.key + 1);
                    networkState.postValue(Resource.success(null));
                } else {
                    retryCallback = new RetryCallback() {
                        @Override
                        public void invoke() {
                            loadAfter(params, callback);
                        }
                    };
                    networkState.postValue(Resource.error("error code: " + response.code(), null));
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                retryCallback = new RetryCallback() {
                    @Override
                    public void invoke() {
                        networkExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                loadAfter(params, callback);
                            }
                        });
                    }
                };
                networkState.postValue(Resource.error(t != null ? t.getMessage() : "unknown error", null));
            }
        });
    }

    public interface RetryCallback {
        void invoke();
    }

}
