package fr.esiea.mymovie.ui.movieslist.favorites;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import fr.esiea.mymovie.data.MovieRepository;
import fr.esiea.mymovie.data.local.model.Movie;

public class FavoritesViewModel extends ViewModel {

    private LiveData<List<Movie>> favoriteListLiveData;

    public FavoritesViewModel(MovieRepository repository) {
        favoriteListLiveData = repository.getAllFavoriteMovies();
    }

    public LiveData<List<Movie>> getFavoriteListLiveData() {
        return favoriteListLiveData;
    }
}
