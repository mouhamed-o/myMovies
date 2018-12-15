package fr.esiea.mymovie.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import fr.esiea.mymovie.data.MovieRepository;
import fr.esiea.mymovie.ui.moviedetails.MovieDetailsViewModel;
import fr.esiea.mymovie.ui.movieslist.discover.DiscoverMoviesViewModel;
import fr.esiea.mymovie.ui.movieslist.favorites.FavoritesViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final MovieRepository repository;

    private ViewModelFactory(MovieRepository repository) {
        this.repository = repository;
    }

    public static ViewModelFactory getInstance(MovieRepository repository) {
        return new ViewModelFactory(repository);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DiscoverMoviesViewModel.class)) {
            return (T) new DiscoverMoviesViewModel(repository);
        } else if (modelClass.isAssignableFrom(FavoritesViewModel.class)) {
            return (T) new FavoritesViewModel(repository);
        } else if (modelClass.isAssignableFrom(MovieDetailsViewModel.class)) {
            return (T) new MovieDetailsViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
