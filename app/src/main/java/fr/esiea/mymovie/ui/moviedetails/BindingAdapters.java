package fr.esiea.mymovie.ui.moviedetails;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

import androidx.databinding.BindingAdapter;
import fr.esiea.mymovie.R;
import fr.esiea.mymovie.data.local.model.Genre;
import fr.esiea.mymovie.utils.ProjectConstants;
import fr.esiea.mymovie.utils.GlideApp;
import fr.esiea.mymovie.utils.UiUtils;

public class BindingAdapters {

    @BindingAdapter({"imageUrl", "isBackdrop"})
    public static void bindImage(ImageView imageView, String imagePath, boolean isBackdrop) {
        String baseUrl;
        if (isBackdrop) {
            baseUrl = ProjectConstants.BACKDROP_URL;
        } else {
            baseUrl = ProjectConstants.IMAGE_URL;
        }

        GlideApp.with(imageView.getContext())
                .load(baseUrl + imagePath)
                .placeholder(R.color.md_grey_200)
                .into(imageView);
    }

    @BindingAdapter({"imageUrl"})
    public static void bindImage(ImageView imageView, String imagePath) {
        GlideApp.with(imageView.getContext())
                .load(ProjectConstants.IMAGE_URL + imagePath)
                .placeholder(R.color.md_grey_200)
                .apply(new RequestOptions().transforms(new CenterCrop(),
                        new RoundedCorners((int) UiUtils.dipToPixels(imageView.getContext(), 8))))
                .into(imageView);
    }

    @BindingAdapter("visibleGone")
    public static void showHide(View view, Boolean show) {
        if (show) view.setVisibility(View.VISIBLE);
        else view.setVisibility(View.GONE);
    }

    @BindingAdapter("items")
    public static void setItems(ChipGroup view, List<Genre> genres) {
        if (genres == null || view.getChildCount() > 0)
            return;

        Context context = view.getContext();
        for (Genre genre : genres) {
            Chip chip = new Chip(context);
            chip.setText(genre.getName());
            chip.setChipStrokeWidth(UiUtils.dipToPixels(context, 1));
            chip.setChipStrokeColor(ColorStateList.valueOf(
                    context.getResources().getColor(R.color.md_blue_grey_200)));
            chip.setChipBackgroundColorResource(android.R.color.transparent);
            view.addView(chip);
        }
    }

}
