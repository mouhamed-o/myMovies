package fr.esiea.mymovie.data.local.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import fr.esiea.mymovie.data.local.model.Trailer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.List;

@SuppressWarnings("unchecked")
public final class TrailersDao_Impl implements TrailersDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfTrailer;

  public TrailersDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTrailer = new EntityInsertionAdapter<Trailer>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `trailer`(`id`,`movie_id`,`key`,`site`,`title`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Trailer value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        stmt.bindLong(2, value.getMovieId());
        if (value.getKey() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getKey());
        }
        if (value.getSite() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSite());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getTitle());
        }
      }
    };
  }

  @Override
  public void insertAllTrailers(List<Trailer> trailers) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfTrailer.insert(trailers);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
