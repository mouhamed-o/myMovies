package fr.esiea.mymovie.data.local;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import fr.esiea.mymovie.data.local.dao.CastsDao;
import fr.esiea.mymovie.data.local.dao.CastsDao_Impl;
import fr.esiea.mymovie.data.local.dao.MoviesDao;
import fr.esiea.mymovie.data.local.dao.MoviesDao_Impl;
import fr.esiea.mymovie.data.local.dao.ReviewsDao;
import fr.esiea.mymovie.data.local.dao.ReviewsDao_Impl;
import fr.esiea.mymovie.data.local.dao.TrailersDao;
import fr.esiea.mymovie.data.local.dao.TrailersDao_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class MoviesDatabase_Impl extends MoviesDatabase {
  private volatile MoviesDao _moviesDao;

  private volatile TrailersDao _trailersDao;

  private volatile CastsDao _castsDao;

  private volatile ReviewsDao _reviewsDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `movie` (`id` INTEGER NOT NULL, `title` TEXT, `poster_path` TEXT, `backdrop_path` TEXT, `overview` TEXT, `originalLanguage` TEXT, `popularity` REAL NOT NULL, `vote_average` REAL NOT NULL, `vote_count` INTEGER NOT NULL, `release_date` TEXT, `is_favorite` INTEGER NOT NULL, `genres` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `trailer` (`id` TEXT NOT NULL, `movie_id` INTEGER NOT NULL, `key` TEXT, `site` TEXT, `title` TEXT, PRIMARY KEY(`id`), FOREIGN KEY(`movie_id`) REFERENCES `movie`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        _db.execSQL("CREATE  INDEX `index_trailer_movie_id` ON `trailer` (`movie_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `cast` (`id` TEXT NOT NULL, `movie_id` INTEGER NOT NULL, `characterName` TEXT, `gender` INTEGER NOT NULL, `actorName` TEXT, `order` INTEGER NOT NULL, `profileImagePath` TEXT, PRIMARY KEY(`id`), FOREIGN KEY(`movie_id`) REFERENCES `movie`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        _db.execSQL("CREATE  INDEX `index_cast_movie_id` ON `cast` (`movie_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `review` (`id` TEXT NOT NULL, `movie_id` INTEGER NOT NULL, `author` TEXT, `content` TEXT, `url` TEXT, PRIMARY KEY(`id`), FOREIGN KEY(`movie_id`) REFERENCES `movie`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        _db.execSQL("CREATE  INDEX `index_review_movie_id` ON `review` (`movie_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"096304796c856f42f200303d71d41096\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `movie`");
        _db.execSQL("DROP TABLE IF EXISTS `trailer`");
        _db.execSQL("DROP TABLE IF EXISTS `cast`");
        _db.execSQL("DROP TABLE IF EXISTS `review`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsMovie = new HashMap<String, TableInfo.Column>(12);
        _columnsMovie.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsMovie.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsMovie.put("poster_path", new TableInfo.Column("poster_path", "TEXT", false, 0));
        _columnsMovie.put("backdrop_path", new TableInfo.Column("backdrop_path", "TEXT", false, 0));
        _columnsMovie.put("overview", new TableInfo.Column("overview", "TEXT", false, 0));
        _columnsMovie.put("originalLanguage", new TableInfo.Column("originalLanguage", "TEXT", false, 0));
        _columnsMovie.put("popularity", new TableInfo.Column("popularity", "REAL", true, 0));
        _columnsMovie.put("vote_average", new TableInfo.Column("vote_average", "REAL", true, 0));
        _columnsMovie.put("vote_count", new TableInfo.Column("vote_count", "INTEGER", true, 0));
        _columnsMovie.put("release_date", new TableInfo.Column("release_date", "TEXT", false, 0));
        _columnsMovie.put("is_favorite", new TableInfo.Column("is_favorite", "INTEGER", true, 0));
        _columnsMovie.put("genres", new TableInfo.Column("genres", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMovie = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMovie = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMovie = new TableInfo("movie", _columnsMovie, _foreignKeysMovie, _indicesMovie);
        final TableInfo _existingMovie = TableInfo.read(_db, "movie");
        if (! _infoMovie.equals(_existingMovie)) {
          throw new IllegalStateException("Migration didn't properly handle movie(fr.esiea.mymovie.data.local.model.Movie).\n"
                  + " Expected:\n" + _infoMovie + "\n"
                  + " Found:\n" + _existingMovie);
        }
        final HashMap<String, TableInfo.Column> _columnsTrailer = new HashMap<String, TableInfo.Column>(5);
        _columnsTrailer.put("id", new TableInfo.Column("id", "TEXT", true, 1));
        _columnsTrailer.put("movie_id", new TableInfo.Column("movie_id", "INTEGER", true, 0));
        _columnsTrailer.put("key", new TableInfo.Column("key", "TEXT", false, 0));
        _columnsTrailer.put("site", new TableInfo.Column("site", "TEXT", false, 0));
        _columnsTrailer.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTrailer = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysTrailer.add(new TableInfo.ForeignKey("movie", "CASCADE", "CASCADE",Arrays.asList("movie_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesTrailer = new HashSet<TableInfo.Index>(1);
        _indicesTrailer.add(new TableInfo.Index("index_trailer_movie_id", false, Arrays.asList("movie_id")));
        final TableInfo _infoTrailer = new TableInfo("trailer", _columnsTrailer, _foreignKeysTrailer, _indicesTrailer);
        final TableInfo _existingTrailer = TableInfo.read(_db, "trailer");
        if (! _infoTrailer.equals(_existingTrailer)) {
          throw new IllegalStateException("Migration didn't properly handle trailer(fr.esiea.mymovie.data.local.model.Trailer).\n"
                  + " Expected:\n" + _infoTrailer + "\n"
                  + " Found:\n" + _existingTrailer);
        }
        final HashMap<String, TableInfo.Column> _columnsCast = new HashMap<String, TableInfo.Column>(7);
        _columnsCast.put("id", new TableInfo.Column("id", "TEXT", true, 1));
        _columnsCast.put("movie_id", new TableInfo.Column("movie_id", "INTEGER", true, 0));
        _columnsCast.put("characterName", new TableInfo.Column("characterName", "TEXT", false, 0));
        _columnsCast.put("gender", new TableInfo.Column("gender", "INTEGER", true, 0));
        _columnsCast.put("actorName", new TableInfo.Column("actorName", "TEXT", false, 0));
        _columnsCast.put("order", new TableInfo.Column("order", "INTEGER", true, 0));
        _columnsCast.put("profileImagePath", new TableInfo.Column("profileImagePath", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCast = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysCast.add(new TableInfo.ForeignKey("movie", "CASCADE", "CASCADE",Arrays.asList("movie_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesCast = new HashSet<TableInfo.Index>(1);
        _indicesCast.add(new TableInfo.Index("index_cast_movie_id", false, Arrays.asList("movie_id")));
        final TableInfo _infoCast = new TableInfo("cast", _columnsCast, _foreignKeysCast, _indicesCast);
        final TableInfo _existingCast = TableInfo.read(_db, "cast");
        if (! _infoCast.equals(_existingCast)) {
          throw new IllegalStateException("Migration didn't properly handle cast(fr.esiea.mymovie.data.local.model.Cast).\n"
                  + " Expected:\n" + _infoCast + "\n"
                  + " Found:\n" + _existingCast);
        }
        final HashMap<String, TableInfo.Column> _columnsReview = new HashMap<String, TableInfo.Column>(5);
        _columnsReview.put("id", new TableInfo.Column("id", "TEXT", true, 1));
        _columnsReview.put("movie_id", new TableInfo.Column("movie_id", "INTEGER", true, 0));
        _columnsReview.put("author", new TableInfo.Column("author", "TEXT", false, 0));
        _columnsReview.put("content", new TableInfo.Column("content", "TEXT", false, 0));
        _columnsReview.put("url", new TableInfo.Column("url", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReview = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysReview.add(new TableInfo.ForeignKey("movie", "CASCADE", "CASCADE",Arrays.asList("movie_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesReview = new HashSet<TableInfo.Index>(1);
        _indicesReview.add(new TableInfo.Index("index_review_movie_id", false, Arrays.asList("movie_id")));
        final TableInfo _infoReview = new TableInfo("review", _columnsReview, _foreignKeysReview, _indicesReview);
        final TableInfo _existingReview = TableInfo.read(_db, "review");
        if (! _infoReview.equals(_existingReview)) {
          throw new IllegalStateException("Migration didn't properly handle review(fr.esiea.mymovie.data.local.model.Review).\n"
                  + " Expected:\n" + _infoReview + "\n"
                  + " Found:\n" + _existingReview);
        }
      }
    }, "096304796c856f42f200303d71d41096", "36b8505baede4b94d093a6e7187853ea");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "movie","trailer","cast","review");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `movie`");
      _db.execSQL("DELETE FROM `trailer`");
      _db.execSQL("DELETE FROM `cast`");
      _db.execSQL("DELETE FROM `review`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public MoviesDao moviesDao() {
    if (_moviesDao != null) {
      return _moviesDao;
    } else {
      synchronized(this) {
        if(_moviesDao == null) {
          _moviesDao = new MoviesDao_Impl(this);
        }
        return _moviesDao;
      }
    }
  }

  @Override
  public TrailersDao trailersDao() {
    if (_trailersDao != null) {
      return _trailersDao;
    } else {
      synchronized(this) {
        if(_trailersDao == null) {
          _trailersDao = new TrailersDao_Impl(this);
        }
        return _trailersDao;
      }
    }
  }

  @Override
  public CastsDao castsDao() {
    if (_castsDao != null) {
      return _castsDao;
    } else {
      synchronized(this) {
        if(_castsDao == null) {
          _castsDao = new CastsDao_Impl(this);
        }
        return _castsDao;
      }
    }
  }

  @Override
  public ReviewsDao reviewsDao() {
    if (_reviewsDao != null) {
      return _reviewsDao;
    } else {
      synchronized(this) {
        if(_reviewsDao == null) {
          _reviewsDao = new ReviewsDao_Impl(this);
        }
        return _reviewsDao;
      }
    }
  }
}
