package fr.esiea.mymovie.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import fr.esiea.mymovie.data.local.model.Movie;

public abstract class ItemMovieBinding extends ViewDataBinding {
  @NonNull
  public final CardView cardView;

  @NonNull
  public final ImageView imageMoviePoster;

  @NonNull
  public final TextView textTitle;

  @Bindable
  protected Movie mMovie;

  protected ItemMovieBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, CardView cardView, ImageView imageMoviePoster, TextView textTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.cardView = cardView;
    this.imageMoviePoster = imageMoviePoster;
    this.textTitle = textTitle;
  }

  public abstract void setMovie(@Nullable Movie movie);

  @Nullable
  public Movie getMovie() {
    return mMovie;
  }

  @NonNull
  public static ItemMovieBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ItemMovieBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ItemMovieBinding>inflate(inflater, fr.esiea.mymovie.R.layout.item_movie, root, attachToRoot, component);
  }

  @NonNull
  public static ItemMovieBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ItemMovieBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ItemMovieBinding>inflate(inflater, fr.esiea.mymovie.R.layout.item_movie, null, false, component);
  }

  public static ItemMovieBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ItemMovieBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ItemMovieBinding)bind(component, view, fr.esiea.mymovie.R.layout.item_movie);
  }
}
