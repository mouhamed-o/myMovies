package fr.esiea.mymovie.data.local.model;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "cast",
        foreignKeys = @ForeignKey(
                entity = Movie.class,
                parentColumns = "id",
                childColumns = "movie_id",
                onDelete = CASCADE,
                onUpdate = CASCADE
        ),
        indices = {
                @Index(value = {"movie_id"})
        }
)
public class Cast {

    @NonNull
    @PrimaryKey
    @SerializedName("credit_id")
    private String id;

    @NonNull
    @ColumnInfo(name = "movie_id")
    private long movieId;

    @SerializedName("character")
    private String characterName;

    @SerializedName("gender")
    private int gender;

    @SerializedName("name")
    private String actorName;

    @SerializedName("order")
    private int order;

    @SerializedName("profile_path")
    private String profileImagePath;

    @NonNull
    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(@NonNull long movieId) {
        this.movieId = movieId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }
}
