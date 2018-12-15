package fr.esiea.mymovie.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import fr.esiea.mymovie.data.local.model.MovieDetails;
import fr.esiea.mymovie.data.local.model.Resource;

public abstract class ActivityDetailsBinding extends ViewDataBinding {
  @NonNull
  public final AppBarLayout appbar;

  @NonNull
  public final CollapsingToolbarLayout collapsingToolbar;

  @NonNull
  public final CoordinatorLayout coordinatorLayout;

  @NonNull
  public final ImageView imageMovieBackdrop;

  @NonNull
  public final NestedScrollView movieDetails;

  @NonNull
  public final PartialDetailsInfoBinding movieDetailsInfo;

  @NonNull
  public final ItemNetworkStateBinding networkState;

  @NonNull
  public final Toolbar toolbar;

  @Bindable
  protected MovieDetails mMovieDetails;

  @Bindable
  protected Resource mResource;

  protected ActivityDetailsBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, AppBarLayout appbar, CollapsingToolbarLayout collapsingToolbar,
      CoordinatorLayout coordinatorLayout, ImageView imageMovieBackdrop,
      NestedScrollView movieDetails, PartialDetailsInfoBinding movieDetailsInfo,
      ItemNetworkStateBinding networkState, Toolbar toolbar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.appbar = appbar;
    this.collapsingToolbar = collapsingToolbar;
    this.coordinatorLayout = coordinatorLayout;
    this.imageMovieBackdrop = imageMovieBackdrop;
    this.movieDetails = movieDetails;
    this.movieDetailsInfo = movieDetailsInfo;
    setContainedBinding(this.movieDetailsInfo);;
    this.networkState = networkState;
    setContainedBinding(this.networkState);;
    this.toolbar = toolbar;
  }

  public abstract void setMovieDetails(@Nullable MovieDetails movieDetails);

  @Nullable
  public MovieDetails getMovieDetails() {
    return mMovieDetails;
  }

  public abstract void setResource(@Nullable Resource resource);

  @Nullable
  public Resource getResource() {
    return mResource;
  }

  @NonNull
  public static ActivityDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityDetailsBinding>inflate(inflater, fr.esiea.mymovie.R.layout.activity_details, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityDetailsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityDetailsBinding>inflate(inflater, fr.esiea.mymovie.R.layout.activity_details, null, false, component);
  }

  public static ActivityDetailsBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityDetailsBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityDetailsBinding)bind(component, view, fr.esiea.mymovie.R.layout.activity_details);
  }
}
