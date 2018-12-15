package fr.esiea.mymovie.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.ChipGroup;
import fr.esiea.mymovie.data.local.model.MovieDetails;

public abstract class PartialDetailsInfoBinding extends ViewDataBinding {
  @NonNull
  public final Barrier barrier2;

  @NonNull
  public final CardView cardPoster;

  @NonNull
  public final ChipGroup chipGroup;

  @NonNull
  public final Guideline guideline;

  @NonNull
  public final ImageView imagePoster;

  @NonNull
  public final TextView labelCast;

  @NonNull
  public final TextView labelLanguage;

  @NonNull
  public final TextView labelOverview;

  @NonNull
  public final TextView labelReleaseDate;

  @NonNull
  public final TextView labelReviews;

  @NonNull
  public final TextView labelTrailers;

  @NonNull
  public final TextView labelVote;

  @NonNull
  public final RecyclerView listCast;

  @NonNull
  public final RecyclerView listReviews;

  @NonNull
  public final RecyclerView listTrailers;

  @NonNull
  public final TextView textLanguage;

  @NonNull
  public final TextView textOverview;

  @NonNull
  public final TextView textReleaseDate;

  @NonNull
  public final TextView textTitle;

  @NonNull
  public final TextView textVote;

  @NonNull
  public final View viewDivider;

  @NonNull
  public final View viewDividerBottom;

  @Bindable
  protected MovieDetails mMovieDetails;

  protected PartialDetailsInfoBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, Barrier barrier2, CardView cardPoster, ChipGroup chipGroup,
      Guideline guideline, ImageView imagePoster, TextView labelCast, TextView labelLanguage,
      TextView labelOverview, TextView labelReleaseDate, TextView labelReviews,
      TextView labelTrailers, TextView labelVote, RecyclerView listCast, RecyclerView listReviews,
      RecyclerView listTrailers, TextView textLanguage, TextView textOverview,
      TextView textReleaseDate, TextView textTitle, TextView textVote, View viewDivider,
      View viewDividerBottom) {
    super(_bindingComponent, _root, _localFieldCount);
    this.barrier2 = barrier2;
    this.cardPoster = cardPoster;
    this.chipGroup = chipGroup;
    this.guideline = guideline;
    this.imagePoster = imagePoster;
    this.labelCast = labelCast;
    this.labelLanguage = labelLanguage;
    this.labelOverview = labelOverview;
    this.labelReleaseDate = labelReleaseDate;
    this.labelReviews = labelReviews;
    this.labelTrailers = labelTrailers;
    this.labelVote = labelVote;
    this.listCast = listCast;
    this.listReviews = listReviews;
    this.listTrailers = listTrailers;
    this.textLanguage = textLanguage;
    this.textOverview = textOverview;
    this.textReleaseDate = textReleaseDate;
    this.textTitle = textTitle;
    this.textVote = textVote;
    this.viewDivider = viewDivider;
    this.viewDividerBottom = viewDividerBottom;
  }

  public abstract void setMovieDetails(@Nullable MovieDetails movieDetails);

  @Nullable
  public MovieDetails getMovieDetails() {
    return mMovieDetails;
  }

  @NonNull
  public static PartialDetailsInfoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static PartialDetailsInfoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<PartialDetailsInfoBinding>inflate(inflater, fr.esiea.mymovie.R.layout.partial_details_info, root, attachToRoot, component);
  }

  @NonNull
  public static PartialDetailsInfoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static PartialDetailsInfoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<PartialDetailsInfoBinding>inflate(inflater, fr.esiea.mymovie.R.layout.partial_details_info, null, false, component);
  }

  public static PartialDetailsInfoBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static PartialDetailsInfoBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (PartialDetailsInfoBinding)bind(component, view, fr.esiea.mymovie.R.layout.partial_details_info);
  }
}
