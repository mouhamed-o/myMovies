package fr.esiea.mymovie.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import fr.esiea.mymovie.data.local.model.Movie;
import fr.esiea.mymovie.data.local.model.MovieDetails;
import fr.esiea.mymovie.data.local.model.RepoMoviesResult;
import fr.esiea.mymovie.data.local.model.Resource;
import fr.esiea.mymovie.ui.movieslist.MoviesFilterType;

public interface DataSource {

    LiveData<Resource<MovieDetails>> loadMovie(long movieId);

    RepoMoviesResult loadMoviesFilteredBy(MoviesFilterType sortBy);

    LiveData<List<Movie>> getAllFavoriteMovies();

    void favoriteMovie(Movie movie);

    void unfavoriteMovie(Movie movie);
}
