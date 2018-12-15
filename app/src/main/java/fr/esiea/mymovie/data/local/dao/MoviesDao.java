package fr.esiea.mymovie.data.local.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import fr.esiea.mymovie.data.local.model.Movie;
import fr.esiea.mymovie.data.local.model.MovieDetails;

@Dao
public interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMovie(Movie movie);

    @Transaction
    @Query("SELECT * FROM movie WHERE movie.id= :movieId")
    LiveData<MovieDetails> getMovie(long movieId);

    @Query("SELECT * FROM movie WHERE is_favorite = 1")
    LiveData<List<Movie>> getAllFavoriteMovies();

    @Query("UPDATE movie SET is_favorite = 1 WHERE id = :movieId")
    int favoriteMovie(long movieId);

    @Query("UPDATE movie SET is_favorite = 0 WHERE id = :movieId")
    int unFavoriteMovie(long movieId);

}
