package fr.esiea.mymovie.data.local.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import fr.esiea.mymovie.data.local.model.Cast;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.List;

@SuppressWarnings("unchecked")
public final class CastsDao_Impl implements CastsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCast;

  public CastsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCast = new EntityInsertionAdapter<Cast>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `cast`(`id`,`movie_id`,`characterName`,`gender`,`actorName`,`order`,`profileImagePath`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Cast value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        stmt.bindLong(2, value.getMovieId());
        if (value.getCharacterName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCharacterName());
        }
        stmt.bindLong(4, value.getGender());
        if (value.getActorName() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getActorName());
        }
        stmt.bindLong(6, value.getOrder());
        if (value.getProfileImagePath() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getProfileImagePath());
        }
      }
    };
  }

  @Override
  public void insertAllCasts(List<Cast> castList) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfCast.insert(castList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
