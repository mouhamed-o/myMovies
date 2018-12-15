package fr.esiea.mymovie.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker.Observer;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import fr.esiea.mymovie.data.local.model.Cast;
import fr.esiea.mymovie.data.local.model.Converters;
import fr.esiea.mymovie.data.local.model.Genre;
import fr.esiea.mymovie.data.local.model.Movie;
import fr.esiea.mymovie.data.local.model.MovieDetails;
import fr.esiea.mymovie.data.local.model.Review;
import fr.esiea.mymovie.data.local.model.Trailer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public final class MoviesDao_Impl implements MoviesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfMovie;

  private final SharedSQLiteStatement __preparedStmtOfFavoriteMovie;

  private final SharedSQLiteStatement __preparedStmtOfUnFavoriteMovie;

  public MoviesDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMovie = new EntityInsertionAdapter<Movie>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `movie`(`id`,`title`,`poster_path`,`backdrop_path`,`overview`,`originalLanguage`,`popularity`,`vote_average`,`vote_count`,`release_date`,`is_favorite`,`genres`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Movie value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getPosterPath() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPosterPath());
        }
        if (value.getBackdropPath() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getBackdropPath());
        }
        if (value.getOverview() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getOverview());
        }
        if (value.getOriginalLanguage() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getOriginalLanguage());
        }
        stmt.bindDouble(7, value.getPopularity());
        stmt.bindDouble(8, value.getVoteAverage());
        stmt.bindLong(9, value.getVoteCount());
        if (value.getReleaseDate() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getReleaseDate());
        }
        final int _tmp;
        _tmp = value.isFavorite() ? 1 : 0;
        stmt.bindLong(11, _tmp);
        final String _tmp_1;
        _tmp_1 = Converters.fromGenresList(value.getGenres());
        if (_tmp_1 == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, _tmp_1);
        }
      }
    };
    this.__preparedStmtOfFavoriteMovie = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE movie SET is_favorite = 1 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUnFavoriteMovie = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE movie SET is_favorite = 0 WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertMovie(Movie movie) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfMovie.insert(movie);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int favoriteMovie(long movieId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfFavoriteMovie.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, movieId);
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfFavoriteMovie.release(_stmt);
    }
  }

  @Override
  public int unFavoriteMovie(long movieId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUnFavoriteMovie.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, movieId);
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfUnFavoriteMovie.release(_stmt);
    }
  }

  @Override
  public LiveData<MovieDetails> getMovie(long movieId) {
    final String _sql = "SELECT * FROM movie WHERE movie.id= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    return new ComputableLiveData<MovieDetails>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected MovieDetails compute() {
        if (_observer == null) {
          _observer = new Observer("trailer","cast","review","movie") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        __db.beginTransaction();
        try {
          final Cursor _cursor = __db.query(_statement);
          try {
            final ArrayMap<Long, ArrayList<Trailer>> _collectionTrailers = new ArrayMap<Long, ArrayList<Trailer>>();
            final ArrayMap<Long, ArrayList<Cast>> _collectionCastList = new ArrayMap<Long, ArrayList<Cast>>();
            final ArrayMap<Long, ArrayList<Review>> _collectionReviews = new ArrayMap<Long, ArrayList<Review>>();
            final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
            final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
            final int _cursorIndexOfPosterPath = _cursor.getColumnIndexOrThrow("poster_path");
            final int _cursorIndexOfBackdropPath = _cursor.getColumnIndexOrThrow("backdrop_path");
            final int _cursorIndexOfOverview = _cursor.getColumnIndexOrThrow("overview");
            final int _cursorIndexOfOriginalLanguage = _cursor.getColumnIndexOrThrow("originalLanguage");
            final int _cursorIndexOfPopularity = _cursor.getColumnIndexOrThrow("popularity");
            final int _cursorIndexOfVoteAverage = _cursor.getColumnIndexOrThrow("vote_average");
            final int _cursorIndexOfVoteCount = _cursor.getColumnIndexOrThrow("vote_count");
            final int _cursorIndexOfReleaseDate = _cursor.getColumnIndexOrThrow("release_date");
            final int _cursorIndexOfIsFavorite = _cursor.getColumnIndexOrThrow("is_favorite");
            final int _cursorIndexOfGenres = _cursor.getColumnIndexOrThrow("genres");
            final MovieDetails _result;
            if(_cursor.moveToFirst()) {
              final Movie _tmpMovie;
              if (! (_cursor.isNull(_cursorIndexOfId) && _cursor.isNull(_cursorIndexOfTitle) && _cursor.isNull(_cursorIndexOfPosterPath) && _cursor.isNull(_cursorIndexOfBackdropPath) && _cursor.isNull(_cursorIndexOfOverview) && _cursor.isNull(_cursorIndexOfOriginalLanguage) && _cursor.isNull(_cursorIndexOfPopularity) && _cursor.isNull(_cursorIndexOfVoteAverage) && _cursor.isNull(_cursorIndexOfVoteCount) && _cursor.isNull(_cursorIndexOfReleaseDate) && _cursor.isNull(_cursorIndexOfIsFavorite) && _cursor.isNull(_cursorIndexOfGenres))) {
                _tmpMovie = new Movie();
                final long _tmpId;
                _tmpId = _cursor.getLong(_cursorIndexOfId);
                _tmpMovie.setId(_tmpId);
                final String _tmpTitle;
                _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
                _tmpMovie.setTitle(_tmpTitle);
                final String _tmpPosterPath;
                _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
                _tmpMovie.setPosterPath(_tmpPosterPath);
                final String _tmpBackdropPath;
                _tmpBackdropPath = _cursor.getString(_cursorIndexOfBackdropPath);
                _tmpMovie.setBackdropPath(_tmpBackdropPath);
                final String _tmpOverview;
                _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
                _tmpMovie.setOverview(_tmpOverview);
                final String _tmpOriginalLanguage;
                _tmpOriginalLanguage = _cursor.getString(_cursorIndexOfOriginalLanguage);
                _tmpMovie.setOriginalLanguage(_tmpOriginalLanguage);
                final double _tmpPopularity;
                _tmpPopularity = _cursor.getDouble(_cursorIndexOfPopularity);
                _tmpMovie.setPopularity(_tmpPopularity);
                final double _tmpVoteAverage;
                _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
                _tmpMovie.setVoteAverage(_tmpVoteAverage);
                final int _tmpVoteCount;
                _tmpVoteCount = _cursor.getInt(_cursorIndexOfVoteCount);
                _tmpMovie.setVoteCount(_tmpVoteCount);
                final String _tmpReleaseDate;
                _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
                _tmpMovie.setReleaseDate(_tmpReleaseDate);
                final boolean _tmpIsFavorite;
                final int _tmp;
                _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
                _tmpIsFavorite = _tmp != 0;
                _tmpMovie.setFavorite(_tmpIsFavorite);
                final List<Genre> _tmpGenres;
                final String _tmp_1;
                _tmp_1 = _cursor.getString(_cursorIndexOfGenres);
                _tmpGenres = Converters.toGenresList(_tmp_1);
                _tmpMovie.setGenres(_tmpGenres);
              }  else  {
                _tmpMovie = null;
              }
              _result = new MovieDetails();
              if (!_cursor.isNull(_cursorIndexOfId)) {
                final Long _tmpKey = _cursor.getLong(_cursorIndexOfId);
                ArrayList<Trailer> _tmpCollection = _collectionTrailers.get(_tmpKey);
                if(_tmpCollection == null) {
                  _tmpCollection = new ArrayList<Trailer>();
                  _collectionTrailers.put(_tmpKey, _tmpCollection);
                }
                _result.trailers = _tmpCollection;
              }
              if (!_cursor.isNull(_cursorIndexOfId)) {
                final Long _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
                ArrayList<Cast> _tmpCollection_1 = _collectionCastList.get(_tmpKey_1);
                if(_tmpCollection_1 == null) {
                  _tmpCollection_1 = new ArrayList<Cast>();
                  _collectionCastList.put(_tmpKey_1, _tmpCollection_1);
                }
                _result.castList = _tmpCollection_1;
              }
              if (!_cursor.isNull(_cursorIndexOfId)) {
                final Long _tmpKey_2 = _cursor.getLong(_cursorIndexOfId);
                ArrayList<Review> _tmpCollection_2 = _collectionReviews.get(_tmpKey_2);
                if(_tmpCollection_2 == null) {
                  _tmpCollection_2 = new ArrayList<Review>();
                  _collectionReviews.put(_tmpKey_2, _tmpCollection_2);
                }
                _result.reviews = _tmpCollection_2;
              }
              _result.movie = _tmpMovie;
            } else {
              _result = null;
            }
            __fetchRelationshiptrailerAsfrEsieaMymovieDataLocalModelTrailer(_collectionTrailers);
            __fetchRelationshipcastAsfrEsieaMymovieDataLocalModelCast(_collectionCastList);
            __fetchRelationshipreviewAsfrEsieaMymovieDataLocalModelReview(_collectionReviews);
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<List<Movie>> getAllFavoriteMovies() {
    final String _sql = "SELECT * FROM movie WHERE is_favorite = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<Movie>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<Movie> compute() {
        if (_observer == null) {
          _observer = new Observer("movie") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
          final int _cursorIndexOfPosterPath = _cursor.getColumnIndexOrThrow("poster_path");
          final int _cursorIndexOfBackdropPath = _cursor.getColumnIndexOrThrow("backdrop_path");
          final int _cursorIndexOfOverview = _cursor.getColumnIndexOrThrow("overview");
          final int _cursorIndexOfOriginalLanguage = _cursor.getColumnIndexOrThrow("originalLanguage");
          final int _cursorIndexOfPopularity = _cursor.getColumnIndexOrThrow("popularity");
          final int _cursorIndexOfVoteAverage = _cursor.getColumnIndexOrThrow("vote_average");
          final int _cursorIndexOfVoteCount = _cursor.getColumnIndexOrThrow("vote_count");
          final int _cursorIndexOfReleaseDate = _cursor.getColumnIndexOrThrow("release_date");
          final int _cursorIndexOfIsFavorite = _cursor.getColumnIndexOrThrow("is_favorite");
          final int _cursorIndexOfGenres = _cursor.getColumnIndexOrThrow("genres");
          final List<Movie> _result = new ArrayList<Movie>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Movie _item;
            _item = new Movie();
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _item.setTitle(_tmpTitle);
            final String _tmpPosterPath;
            _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
            _item.setPosterPath(_tmpPosterPath);
            final String _tmpBackdropPath;
            _tmpBackdropPath = _cursor.getString(_cursorIndexOfBackdropPath);
            _item.setBackdropPath(_tmpBackdropPath);
            final String _tmpOverview;
            _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            _item.setOverview(_tmpOverview);
            final String _tmpOriginalLanguage;
            _tmpOriginalLanguage = _cursor.getString(_cursorIndexOfOriginalLanguage);
            _item.setOriginalLanguage(_tmpOriginalLanguage);
            final double _tmpPopularity;
            _tmpPopularity = _cursor.getDouble(_cursorIndexOfPopularity);
            _item.setPopularity(_tmpPopularity);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            _item.setVoteAverage(_tmpVoteAverage);
            final int _tmpVoteCount;
            _tmpVoteCount = _cursor.getInt(_cursorIndexOfVoteCount);
            _item.setVoteCount(_tmpVoteCount);
            final String _tmpReleaseDate;
            _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
            _item.setReleaseDate(_tmpReleaseDate);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            _item.setFavorite(_tmpIsFavorite);
            final List<Genre> _tmpGenres;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfGenres);
            _tmpGenres = Converters.toGenresList(_tmp_1);
            _item.setGenres(_tmpGenres);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  private void __fetchRelationshiptrailerAsfrEsieaMymovieDataLocalModelTrailer(final ArrayMap<Long, ArrayList<Trailer>> _map) {
    final Set<Long> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      ArrayMap<Long, ArrayList<Trailer>> _tmpInnerMap = new ArrayMap<Long, ArrayList<Trailer>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _mapIndex = 0;
      int _tmpIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshiptrailerAsfrEsieaMymovieDataLocalModelTrailer(_tmpInnerMap);
          _tmpInnerMap = new ArrayMap<Long, ArrayList<Trailer>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshiptrailerAsfrEsieaMymovieDataLocalModelTrailer(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`movie_id`,`key`,`site`,`title` FROM `trailer` WHERE `movie_id` IN (");
    final int _inputSize = __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (Long _item : __mapKeySet) {
      if (_item == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindLong(_argIndex, _item);
      }
      _argIndex ++;
    }
    final Cursor _cursor = __db.query(_stmt);
    try {
      final int _itemKeyIndex = _cursor.getColumnIndex("movie_id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfMovieId = _cursor.getColumnIndexOrThrow("movie_id");
      final int _cursorIndexOfKey = _cursor.getColumnIndexOrThrow("key");
      final int _cursorIndexOfSite = _cursor.getColumnIndexOrThrow("site");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final Long _tmpKey = _cursor.getLong(_itemKeyIndex);
          ArrayList<Trailer> _tmpCollection = _map.get(_tmpKey);
          if (_tmpCollection != null) {
            final Trailer _item_1;
            _item_1 = new Trailer();
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            _item_1.setId(_tmpId);
            final long _tmpMovieId;
            _tmpMovieId = _cursor.getLong(_cursorIndexOfMovieId);
            _item_1.setMovieId(_tmpMovieId);
            final String _tmpKey_1;
            _tmpKey_1 = _cursor.getString(_cursorIndexOfKey);
            _item_1.setKey(_tmpKey_1);
            final String _tmpSite;
            _tmpSite = _cursor.getString(_cursorIndexOfSite);
            _item_1.setSite(_tmpSite);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _item_1.setTitle(_tmpTitle);
            _tmpCollection.add(_item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }

  private void __fetchRelationshipcastAsfrEsieaMymovieDataLocalModelCast(final ArrayMap<Long, ArrayList<Cast>> _map) {
    final Set<Long> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      ArrayMap<Long, ArrayList<Cast>> _tmpInnerMap = new ArrayMap<Long, ArrayList<Cast>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _mapIndex = 0;
      int _tmpIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipcastAsfrEsieaMymovieDataLocalModelCast(_tmpInnerMap);
          _tmpInnerMap = new ArrayMap<Long, ArrayList<Cast>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipcastAsfrEsieaMymovieDataLocalModelCast(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`movie_id`,`characterName`,`gender`,`actorName`,`order`,`profileImagePath` FROM `cast` WHERE `movie_id` IN (");
    final int _inputSize = __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (Long _item : __mapKeySet) {
      if (_item == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindLong(_argIndex, _item);
      }
      _argIndex ++;
    }
    final Cursor _cursor = __db.query(_stmt);
    try {
      final int _itemKeyIndex = _cursor.getColumnIndex("movie_id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfMovieId = _cursor.getColumnIndexOrThrow("movie_id");
      final int _cursorIndexOfCharacterName = _cursor.getColumnIndexOrThrow("characterName");
      final int _cursorIndexOfGender = _cursor.getColumnIndexOrThrow("gender");
      final int _cursorIndexOfActorName = _cursor.getColumnIndexOrThrow("actorName");
      final int _cursorIndexOfOrder = _cursor.getColumnIndexOrThrow("order");
      final int _cursorIndexOfProfileImagePath = _cursor.getColumnIndexOrThrow("profileImagePath");
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final Long _tmpKey = _cursor.getLong(_itemKeyIndex);
          ArrayList<Cast> _tmpCollection = _map.get(_tmpKey);
          if (_tmpCollection != null) {
            final Cast _item_1;
            _item_1 = new Cast();
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            _item_1.setId(_tmpId);
            final long _tmpMovieId;
            _tmpMovieId = _cursor.getLong(_cursorIndexOfMovieId);
            _item_1.setMovieId(_tmpMovieId);
            final String _tmpCharacterName;
            _tmpCharacterName = _cursor.getString(_cursorIndexOfCharacterName);
            _item_1.setCharacterName(_tmpCharacterName);
            final int _tmpGender;
            _tmpGender = _cursor.getInt(_cursorIndexOfGender);
            _item_1.setGender(_tmpGender);
            final String _tmpActorName;
            _tmpActorName = _cursor.getString(_cursorIndexOfActorName);
            _item_1.setActorName(_tmpActorName);
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            _item_1.setOrder(_tmpOrder);
            final String _tmpProfileImagePath;
            _tmpProfileImagePath = _cursor.getString(_cursorIndexOfProfileImagePath);
            _item_1.setProfileImagePath(_tmpProfileImagePath);
            _tmpCollection.add(_item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }

  private void __fetchRelationshipreviewAsfrEsieaMymovieDataLocalModelReview(final ArrayMap<Long, ArrayList<Review>> _map) {
    final Set<Long> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      ArrayMap<Long, ArrayList<Review>> _tmpInnerMap = new ArrayMap<Long, ArrayList<Review>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _mapIndex = 0;
      int _tmpIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipreviewAsfrEsieaMymovieDataLocalModelReview(_tmpInnerMap);
          _tmpInnerMap = new ArrayMap<Long, ArrayList<Review>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipreviewAsfrEsieaMymovieDataLocalModelReview(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`movie_id`,`author`,`content`,`url` FROM `review` WHERE `movie_id` IN (");
    final int _inputSize = __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (Long _item : __mapKeySet) {
      if (_item == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindLong(_argIndex, _item);
      }
      _argIndex ++;
    }
    final Cursor _cursor = __db.query(_stmt);
    try {
      final int _itemKeyIndex = _cursor.getColumnIndex("movie_id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfMovieId = _cursor.getColumnIndexOrThrow("movie_id");
      final int _cursorIndexOfAuthor = _cursor.getColumnIndexOrThrow("author");
      final int _cursorIndexOfContent = _cursor.getColumnIndexOrThrow("content");
      final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final Long _tmpKey = _cursor.getLong(_itemKeyIndex);
          ArrayList<Review> _tmpCollection = _map.get(_tmpKey);
          if (_tmpCollection != null) {
            final Review _item_1;
            _item_1 = new Review();
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            _item_1.setId(_tmpId);
            final long _tmpMovieId;
            _tmpMovieId = _cursor.getLong(_cursorIndexOfMovieId);
            _item_1.setMovieId(_tmpMovieId);
            final String _tmpAuthor;
            _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            _item_1.setAuthor(_tmpAuthor);
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            _item_1.setContent(_tmpContent);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            _item_1.setUrl(_tmpUrl);
            _tmpCollection.add(_item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
