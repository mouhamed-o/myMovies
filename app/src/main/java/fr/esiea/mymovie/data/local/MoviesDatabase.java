package fr.esiea.mymovie.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import fr.esiea.mymovie.data.local.dao.CastsDao;
import fr.esiea.mymovie.data.local.dao.MoviesDao;
import fr.esiea.mymovie.data.local.dao.ReviewsDao;
import fr.esiea.mymovie.data.local.dao.TrailersDao;
import fr.esiea.mymovie.data.local.model.Cast;
import fr.esiea.mymovie.data.local.model.Converters;
import fr.esiea.mymovie.data.local.model.Movie;
import fr.esiea.mymovie.data.local.model.Review;
import fr.esiea.mymovie.data.local.model.Trailer;

@Database(
        entities = {Movie.class, Trailer.class, Cast.class, Review.class},
        version = 1,
        exportSchema = false)
@TypeConverters(Converters.class)
public abstract class MoviesDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "myMovies.db";
    private static final Object sLock = new Object();
    private static MoviesDatabase INSTANCE;

    public static MoviesDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = buildDatabase(context);
            }
            return INSTANCE;
        }
    }

    private static MoviesDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                MoviesDatabase.class,
                DATABASE_NAME).build();
    }

    public abstract MoviesDao moviesDao();

    public abstract TrailersDao trailersDao();

    public abstract CastsDao castsDao();

    public abstract ReviewsDao reviewsDao();
}
