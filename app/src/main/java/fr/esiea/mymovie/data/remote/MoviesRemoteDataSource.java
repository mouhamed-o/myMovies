package fr.esiea.mymovie.data.remote;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import fr.esiea.mymovie.data.local.model.Movie;
import fr.esiea.mymovie.data.local.model.RepoMoviesResult;
import fr.esiea.mymovie.data.local.model.Resource;
import fr.esiea.mymovie.data.remote.api.ApiResponse;
import fr.esiea.mymovie.data.remote.api.MovieService;
import fr.esiea.mymovie.data.remote.paging.MovieDataSourceFactory;
import fr.esiea.mymovie.data.remote.paging.MoviePageKeyedDataSource;
import fr.esiea.mymovie.ui.movieslist.MoviesFilterType;
import fr.esiea.mymovie.utils.AppExecutors;

public class MoviesRemoteDataSource {

    private static final int PAGE_SIZE = 20;
    private static volatile MoviesRemoteDataSource sInstance;
    private final AppExecutors mExecutors;
    private final MovieService mMovieService;

    private MoviesRemoteDataSource(MovieService movieService,
                                   AppExecutors executors) {
        mMovieService = movieService;
        mExecutors = executors;
    }

    public static MoviesRemoteDataSource getInstance(MovieService movieService,
                                                     AppExecutors executors) {
        if (sInstance == null) {
            synchronized (AppExecutors.class) {
                if (sInstance == null) {
                    sInstance = new MoviesRemoteDataSource(movieService, executors);
                }
            }
        }
        return sInstance;
    }

    public LiveData<ApiResponse<Movie>> loadMovie(final long movieId) {
        return mMovieService.getMovieDetails(movieId);
    }

    public RepoMoviesResult loadMoviesFilteredBy(MoviesFilterType sortBy) {
        MovieDataSourceFactory sourceFactory =
                new MovieDataSourceFactory(mMovieService, mExecutors.networkIO(), sortBy);
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();
        LiveData<PagedList<Movie>> moviesPagedList = new LivePagedListBuilder<>(sourceFactory, config)
                .setFetchExecutor(mExecutors.networkIO())
                .build();

        LiveData<Resource> networkState = Transformations.switchMap(sourceFactory.sourceLiveData, new Function<MoviePageKeyedDataSource, LiveData<Resource>>() {
            @Override
            public LiveData<Resource> apply(MoviePageKeyedDataSource input) {
                return input.networkState;
            }
        });
        return new RepoMoviesResult(
                moviesPagedList,
                networkState,
                sourceFactory.sourceLiveData
        );
    }
}
