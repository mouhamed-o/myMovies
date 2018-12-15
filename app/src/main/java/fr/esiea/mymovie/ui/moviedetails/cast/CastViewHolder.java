package fr.esiea.mymovie.ui.moviedetails.cast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import fr.esiea.mymovie.R;
import fr.esiea.mymovie.data.local.model.Cast;
import fr.esiea.mymovie.databinding.ItemCastBinding;
import fr.esiea.mymovie.utils.ProjectConstants;
import fr.esiea.mymovie.utils.GlideApp;

public class CastViewHolder extends RecyclerView.ViewHolder {

    private ItemCastBinding binding;

    private Context context;

    public CastViewHolder(@NonNull ItemCastBinding binding, Context context) {
        super(binding.getRoot());

        this.binding = binding;
        this.context = context;
    }

    public static CastViewHolder create(ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemCastBinding binding = ItemCastBinding.inflate(layoutInflater, parent, false);
        return new CastViewHolder(binding, parent.getContext());
    }

    public void bindTo(final Cast cast) {
        String profileImage =
                ProjectConstants.IMAGE_BASE_URL + ProjectConstants.PROFILE_SIZE_W185 + cast.getProfileImagePath();
        GlideApp.with(context)
                .load(profileImage)
                .placeholder(R.color.md_grey_200)
                .dontAnimate()
                .into(binding.imageCastProfile);

        binding.textCastName.setText(cast.getActorName());

        binding.executePendingBindings();
    }
}
