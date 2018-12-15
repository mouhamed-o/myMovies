package fr.esiea.mymovie.ui.movieslist.discover;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import fr.esiea.mymovie.data.local.model.Resource;
import fr.esiea.mymovie.databinding.ItemNetworkStateBinding;

public class NetworkStateViewHolder extends RecyclerView.ViewHolder {

    private ItemNetworkStateBinding binding;

    public NetworkStateViewHolder(@NonNull ItemNetworkStateBinding binding, final DiscoverMoviesViewModel viewModel) {
        super(binding.getRoot());
        this.binding = binding;
        binding.retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.retry();
            }
        });
    }

    public static NetworkStateViewHolder create(ViewGroup parent, DiscoverMoviesViewModel viewModel) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemNetworkStateBinding binding = ItemNetworkStateBinding.inflate(layoutInflater, parent, false);
        return new NetworkStateViewHolder(binding, viewModel);
    }

    public void bindTo(Resource resource) {
        binding.setResource(resource);
        binding.executePendingBindings();
    }
}
