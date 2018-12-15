package fr.esiea.mymovie.data.remote.paging;

import java.util.concurrent.Executor;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import fr.esiea.mymovie.data.local.model.Movie;
import fr.esiea.mymovie.data.remote.api.MovieService;
import fr.esiea.mymovie.ui.movieslist.MoviesFilterType;

public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie> {

    private final MovieService movieService;
    private final Executor networkExecutor;
    private final MoviesFilterType sortBy;
    public MutableLiveData<MoviePageKeyedDataSource> sourceLiveData = new MutableLiveData<>();

    public MovieDataSourceFactory(MovieService movieService,
                                  Executor networkExecutor, MoviesFilterType sortBy) {
        this.movieService = movieService;
        this.sortBy = sortBy;
        this.networkExecutor = networkExecutor;
    }

    @Override
    public DataSource<Integer, Movie> create() {
        MoviePageKeyedDataSource movieDataSource =
                new MoviePageKeyedDataSource(movieService, networkExecutor, sortBy);
        sourceLiveData.postValue(movieDataSource);
        return movieDataSource;
    }
}
