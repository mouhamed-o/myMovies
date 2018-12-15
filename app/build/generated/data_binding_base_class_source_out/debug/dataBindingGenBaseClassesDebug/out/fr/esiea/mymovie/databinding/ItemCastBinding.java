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
import de.hdodenhof.circleimageview.CircleImageView;

public abstract class ItemCastBinding extends ViewDataBinding {
  @NonNull
  public final CircleImageView imageCastProfile;

  @NonNull
  public final TextView textCastName;

  protected ItemCastBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, CircleImageView imageCastProfile, TextView textCastName) {
    super(_bindingComponent, _root, _localFieldCount);
    this.imageCastProfile = imageCastProfile;
    this.textCastName = textCastName;
  }

  @NonNull
  public static ItemCastBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ItemCastBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ItemCastBinding>inflate(inflater, fr.esiea.mymovie.R.layout.item_cast, root, attachToRoot, component);
  }

  @NonNull
  public static ItemCastBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ItemCastBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ItemCastBinding>inflate(inflater, fr.esiea.mymovie.R.layout.item_cast, null, false, component);
  }

  public static ItemCastBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ItemCastBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
    return (ItemCastBinding)bind(component, view, fr.esiea.mymovie.R.layout.item_cast);
  }
}
