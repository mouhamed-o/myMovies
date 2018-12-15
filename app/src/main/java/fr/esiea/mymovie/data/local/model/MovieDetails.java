package fr.esiea.mymovie.data.local.model;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class MovieDetails {

    @Embedded
    public Movie movie = null;

    @Relation(parentColumn = "id", entityColumn = "movie_id")
    public List<Trailer> trailers = new ArrayList<>();

    @Relation(parentColumn = "id", entityColumn = "movie_id")
    public List<Cast> castList = new ArrayList<>();

    @Relation(parentColumn = "id", entityColumn = "movie_id")
    public List<Review> reviews = new ArrayList<>();
}
