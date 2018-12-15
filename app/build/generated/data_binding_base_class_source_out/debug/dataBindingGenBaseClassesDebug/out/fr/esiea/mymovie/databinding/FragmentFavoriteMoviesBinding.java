package fr.esiea.mymovie.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class FragmentFavoriteMoviesBinding extends ViewDataBinding {
  @NonNull
  public final TextView emptyState;

  @NonNull
  public final FragmentDiscoverMoviesBinding moviesList;

  protected FragmentFavoriteMoviesBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, TextView emptyState, FragmentDiscoverMoviesBinding moviesList) {
    super(_bindingComponent, _root, _localFieldCount);
    this.emptyState = emptyState;
    this.moviesList = moviesList;
    setContainedBinding(this.moviesList);;
  }

  @NonNull
  public static FragmentFavoriteMoviesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentFavoriteMoviesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentFavoriteMoviesBinding>inflate(inflater, fr.esiea.mymovie.R.layout.fragment_favorite_movies, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentFavoriteMoviesBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentFavoriteMoviesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentFavoriteMoviesBinding>inflate(inflater, fr.esiea.mymovie.R.layout.fragment_favorite_movies, null, false, component);
  }

  public static FragmentFavoriteMoviesBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentFavoriteMoviesBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentFavoriteMoviesBinding)bind(component, view, fr.esiea.mymovie.R.layout.fragment_favorite_movies);
  }
}
