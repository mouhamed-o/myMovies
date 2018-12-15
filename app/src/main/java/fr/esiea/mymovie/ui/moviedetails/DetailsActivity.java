package fr.esiea.mymovie.ui.moviedetails;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import fr.esiea.mymovie.R;
import fr.esiea.mymovie.data.local.model.MovieDetails;
import fr.esiea.mymovie.data.local.model.Resource;
import fr.esiea.mymovie.databinding.ActivityDetailsBinding;
import fr.esiea.mymovie.ui.moviedetails.cast.CastAdapter;
import fr.esiea.mymovie.ui.moviedetails.reviews.ReviewsAdapter;
import fr.esiea.mymovie.ui.moviedetails.trailers.TrailersAdapter;
import fr.esiea.mymovie.utils.ProjectConstants;
import fr.esiea.mymovie.utils.Injection;
import fr.esiea.mymovie.utils.UiUtils;
import fr.esiea.mymovie.utils.ViewModelFactory;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE_ID = "extra_movie_id";

    private static final int DEFAULT_ID = -1;

    private ActivityDetailsBinding mBinding;

    private MovieDetailsViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeLight);
        super.onCreate(savedInstanceState);
        final long movieId = getIntent().getLongExtra(EXTRA_MOVIE_ID, DEFAULT_ID);
        if (movieId == DEFAULT_ID) {
            closeOnError();
            return;
        }

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        mBinding.setLifecycleOwner(this);

        mViewModel = obtainViewModel();
        mViewModel.init(movieId);
        setupToolbar();
        setupTrailersAdapter();
        setupCastAdapter();
        setupReviewsAdapter();
        mViewModel.getResult().observe(this, new Observer<Resource<MovieDetails>>() {
            @Override
            public void onChanged(Resource<MovieDetails> resource) {
                if (resource.data != null &&
                        resource.data.movie != null) {
                    mViewModel.setFavorite(resource.data.movie.isFavorite());
                    invalidateOptionsMenu();
                }
                mBinding.setResource(resource);
                mBinding.setMovieDetails(resource.data);
            }
        });
        mBinding.networkState.retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.retry(movieId);
            }
        });
        mViewModel.getSnackbarMessage().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer message) {
                Snackbar.make(mBinding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = mBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            handleCollapsedToolbarTitle();
        }
    }

    private void setupTrailersAdapter() {
        RecyclerView listTrailers = mBinding.movieDetailsInfo.listTrailers;
        listTrailers.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        listTrailers.setHasFixedSize(true);
        listTrailers.setAdapter(new TrailersAdapter());
        ViewCompat.setNestedScrollingEnabled(listTrailers, false);
    }

    private void setupCastAdapter() {
        RecyclerView listCast = mBinding.movieDetailsInfo.listCast;
        listCast.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        listCast.setAdapter(new CastAdapter());
        ViewCompat.setNestedScrollingEnabled(listCast, false);
    }

    private void setupReviewsAdapter() {
        RecyclerView listReviews = mBinding.movieDetailsInfo.listReviews;
        listReviews.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        listReviews.setAdapter(new ReviewsAdapter());
        ViewCompat.setNestedScrollingEnabled(listReviews, false);
    }

    private MovieDetailsViewModel obtainViewModel() {
        ViewModelFactory factory = Injection.provideViewModelFactory(this);
        return ViewModelProviders.of(this, factory).get(MovieDetailsViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_details, menu);
        UiUtils.tintMenuIcon(this, menu.findItem(R.id.action_share), R.color.md_white_1000);

        MenuItem favoriteItem = menu.findItem(R.id.action_favorite);
        if (mViewModel.isFavorite()) {
            favoriteItem.setIcon(R.drawable.ic_favorite_black_24dp)
                    .setTitle(R.string.action_remove_from_favorites);
        } else {
            favoriteItem.setIcon(R.drawable.ic_favorite_border_black_24dp)
                    .setTitle(R.string.action_favorite);
        }
        UiUtils.tintMenuIcon(this, favoriteItem, R.color.md_white_1000);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share: {
                MovieDetails movieDetails = mViewModel.getResult().getValue().data;
                Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                        .setType("text/plain")
                        .setSubject(movieDetails.movie.getTitle() + " movie trailer")
                        .setText("Check out " + movieDetails.movie.getTitle() + " movie trailer at " +
                                Uri.parse(ProjectConstants.YOUTUBE_WEB_URL +
                                        movieDetails.trailers.get(0).getKey())
                        )
                        .createChooserIntent();

                int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
                if (Build.VERSION.SDK_INT >= 21)
                    flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;

                shareIntent.addFlags(flags);
                if (shareIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(shareIntent);
                }
                return true;
            }
            case R.id.action_favorite: {
                mViewModel.onFavoriteClicked();
                invalidateOptionsMenu();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void closeOnError() {
        throw new IllegalArgumentException("Access denied.");
    }

    private void handleCollapsedToolbarTitle() {
        mBinding.appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    mBinding.collapsingToolbar.setTitle(
                            mViewModel.getResult().getValue().data.movie.getTitle());
                    isShow = true;
                } else if (isShow) {
                    mBinding.collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
}
