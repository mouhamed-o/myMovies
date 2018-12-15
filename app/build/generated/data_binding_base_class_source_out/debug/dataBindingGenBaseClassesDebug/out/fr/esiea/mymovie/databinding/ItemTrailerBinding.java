package fr.esiea.mymovie.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class ItemTrailerBinding extends ViewDataBinding {
  @NonNull
  public final CardView cardTrailer;

  @NonNull
  public final ImageView imageTrailer;

  @NonNull
  public final TextView trailerName;

  protected ItemTrailerBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, CardView cardTrailer, ImageView imageTrailer, TextView trailerName) {
    super(_bindingComponent, _root, _localFieldCount);
    this.cardTrailer = cardTrailer;
    this.imageTrailer = imageTrailer;
    this.trailerName = trailerName;
  }

  @NonNull
  public static ItemTrailerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ItemTrailerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ItemTrailerBinding>inflate(inflater, fr.esiea.mymovie.R.layout.item_trailer, root, attachToRoot, component);
  }

  @NonNull
  public static ItemTrailerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ItemTrailerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ItemTrailerBinding>inflate(inflater, fr.esiea.mymovie.R.layout.item_trailer, null, false, component);
  }

  public static ItemTrailerBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ItemTrailerBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ItemTrailerBinding)bind(component, view, fr.esiea.mymovie.R.layout.item_trailer);
  }
}
