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

public abstract class ItemReviewBinding extends ViewDataBinding {
  @NonNull
  public final CardView frame;

  @NonNull
  public final ImageView imageAuthor;

  @NonNull
  public final TextView textAuthor;

  @NonNull
  public final TextView textContent;

  protected ItemReviewBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, CardView frame, ImageView imageAuthor, TextView textAuthor,
      TextView textContent) {
    super(_bindingComponent, _root, _localFieldCount);
    this.frame = frame;
    this.imageAuthor = imageAuthor;
    this.textAuthor = textAuthor;
    this.textContent = textContent;
  }

  @NonNull
  public static ItemReviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ItemReviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ItemReviewBinding>inflate(inflater, fr.esiea.mymovie.R.layout.item_review, root, attachToRoot, component);
  }

  @NonNull
  public static ItemReviewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ItemReviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ItemReviewBinding>inflate(inflater, fr.esiea.mymovie.R.layout.item_review, null, false, component);
  }

  public static ItemReviewBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ItemReviewBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ItemReviewBinding)bind(component, view, fr.esiea.mymovie.R.layout.item_review);
  }
}
