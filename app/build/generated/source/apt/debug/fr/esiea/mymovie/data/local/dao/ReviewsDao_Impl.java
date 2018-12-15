package fr.esiea.mymovie.data.local.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import fr.esiea.mymovie.data.local.model.Review;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.List;

@SuppressWarnings("unchecked")
public final class ReviewsDao_Impl implements ReviewsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfReview;

  public ReviewsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfReview = new EntityInsertionAdapter<Review>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `review`(`id`,`movie_id`,`author`,`content`,`url`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Review value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        stmt.bindLong(2, value.getMovieId());
        if (value.getAuthor() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAuthor());
        }
        if (value.getContent() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getContent());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getUrl());
        }
      }
    };
  }

  @Override
  public void insertAllReviews(List<Review> reviews) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfReview.insert(reviews);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
