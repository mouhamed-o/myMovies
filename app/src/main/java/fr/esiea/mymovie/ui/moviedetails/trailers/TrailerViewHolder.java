package fr.esiea.mymovie.ui.moviedetails.trailers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import fr.esiea.mymovie.R;
import fr.esiea.mymovie.data.local.model.Trailer;
import fr.esiea.mymovie.databinding.ItemTrailerBinding;
import fr.esiea.mymovie.utils.ProjectConstants;
import fr.esiea.mymovie.utils.GlideApp;

public class TrailerViewHolder extends RecyclerView.ViewHolder {

    private ItemTrailerBinding binding;

    private Context context;

    public TrailerViewHolder(@NonNull ItemTrailerBinding binding, Context context) {
        super(binding.getRoot());

        this.binding = binding;
        this.context = context;
    }

    public static TrailerViewHolder create(ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemTrailerBinding binding = ItemTrailerBinding.inflate(layoutInflater, parent, false);
        return new TrailerViewHolder(binding, parent.getContext());
    }

    public void bindTo(final Trailer trailer) {
        String thumbnail = "https://img.youtube.com/vi/" + trailer.getKey() + "/hqdefault.jpg";
        GlideApp.with(context).load(thumbnail).placeholder(R.color.md_grey_200).into(binding.imageTrailer);

        binding.trailerName.setText(trailer.getTitle());

        binding.cardTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("vnd.youtube:" + trailer.getKey()));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(ProjectConstants.YOUTUBE_WEB_URL + trailer.getKey()));
                if (appIntent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(appIntent);
                } else {
                    context.startActivity(webIntent);
                }
            }
        });
        binding.executePendingBindings();
    }
}
