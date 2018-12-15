package fr.esiea.mymovie.utils;

import android.content.Context;

import fr.esiea.mymovie.data.MovieRepository;
import fr.esiea.mymovie.data.local.MoviesDatabase;
import fr.esiea.mymovie.data.local.MoviesLocalDataSource;
import fr.esiea.mymovie.data.remote.MoviesRemoteDataSource;
import fr.esiea.mymovie.data.remote.api.ApiClient;
import fr.esiea.mymovie.data.remote.api.MovieService;

public class Injection {

    public static MoviesRemoteDataSource provideMoviesRemoteDataSource() {
        MovieService apiService = ApiClient.getInstance();
        AppExecutors executors = AppExecutors.getInstance();
        return MoviesRemoteDataSource.getInstance(apiService, executors);
    }

    public static MoviesLocalDataSource provideMoviesLocalDataSource(Context context) {
        MoviesDatabase database = MoviesDatabase.getInstance(context.getApplicationContext());
        return MoviesLocalDataSource.getInstance(database);
    }

    public static MovieRepository provideMovieRepository(Context context) {
        MoviesRemoteDataSource remoteDataSource = provideMoviesRemoteDataSource();
        MoviesLocalDataSource localDataSource = provideMoviesLocalDataSource(context);
        AppExecutors executors = AppExecutors.getInstance();
        return MovieRepository.getInstance(
                localDataSource,
                remoteDataSource,
                executors);
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        MovieRepository repository = provideMovieRepository(context);
        return ViewModelFactory.getInstance(repository);
    }
}
