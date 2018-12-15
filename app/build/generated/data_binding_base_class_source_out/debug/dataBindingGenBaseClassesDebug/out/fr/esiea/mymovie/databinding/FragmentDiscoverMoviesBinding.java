package fr.esiea.mymovie.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class FragmentDiscoverMoviesBinding extends ViewDataBinding {
  @NonNull
  public final RecyclerView rvMovieList;

  protected FragmentDiscoverMoviesBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, RecyclerView rvMovieList) {
    super(_bindingComponent, _root, _localFieldCount);
    this.rvMovieList = rvMovieList;
  }

  @NonNull
  public static FragmentDiscoverMoviesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentDiscoverMoviesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentDiscoverMoviesBinding>inflate(inflater, fr.esiea.mymovie.R.layout.fragment_discover_movies, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentDiscoverMoviesBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentDiscoverMoviesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentDiscoverMoviesBinding>inflate(inflater, fr.esiea.mymovie.R.layout.fragment_discover_movies, null, false, component);
  }

  public static FragmentDiscoverMoviesBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentDiscoverMoviesBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentDiscoverMoviesBinding)bind(component, view, fr.esiea.mymovie.R.layout.fragment_discover_movies);
  }
}
