package fr.esiea.mymovie.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import fr.esiea.mymovie.data.local.model.Resource;

public abstract class ItemNetworkStateBinding extends ViewDataBinding {
  @NonNull
  public final TextView errorMsg;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final Button retryButton;

  @Bindable
  protected Resource mResource;

  protected ItemNetworkStateBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, TextView errorMsg, ProgressBar progressBar, Button retryButton) {
    super(_bindingComponent, _root, _localFieldCount);
    this.errorMsg = errorMsg;
    this.progressBar = progressBar;
    this.retryButton = retryButton;
  }

  public abstract void setResource(@Nullable Resource resource);

  @Nullable
  public Resource getResource() {
    return mResource;
  }

  @NonNull
  public static ItemNetworkStateBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ItemNetworkStateBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ItemNetworkStateBinding>inflate(inflater, fr.esiea.mymovie.R.layout.item_network_state, root, attachToRoot, component);
  }

  @NonNull
  public static ItemNetworkStateBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ItemNetworkStateBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ItemNetworkStateBinding>inflate(inflater, fr.esiea.mymovie.R.layout.item_network_state, null, false, component);
  }

  public static ItemNetworkStateBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ItemNetworkStateBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ItemNetworkStateBinding)bind(component, view, fr.esiea.mymovie.R.layout.item_network_state);
  }
}
