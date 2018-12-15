package fr.esiea.mymovie.ui.movieslist.discover;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;
import fr.esiea.mymovie.R;
import fr.esiea.mymovie.data.MovieRepository;
import fr.esiea.mymovie.data.local.model.Movie;
import fr.esiea.mymovie.data.local.model.RepoMoviesResult;
import fr.esiea.mymovie.data.local.model.Resource;
import fr.esiea.mymovie.ui.movieslist.MoviesFilterType;

public class DiscoverMoviesViewModel extends ViewModel {

    private LiveData<RepoMoviesResult> repoMoviesResult;

    private LiveData<PagedList<Movie>> pagedList;

    private LiveData<Resource> networkState;

    private MutableLiveData<Integer> currentTitle = new MutableLiveData<>();

    private MutableLiveData<MoviesFilterType> sortBy = new MutableLiveData<>();

    public DiscoverMoviesViewModel(final MovieRepository movieRepository) {
        sortBy.setValue(MoviesFilterType.POPULAR);
        currentTitle.setValue(R.string.action_popular);

        repoMoviesResult = Transformations.map(sortBy, new Function<MoviesFilterType, RepoMoviesResult>() {
            @Override
            public RepoMoviesResult apply(MoviesFilterType sort) {
                return movieRepository.loadMoviesFilteredBy(sort);
            }
        });
        pagedList = Transformations.switchMap(repoMoviesResult,
                new Function<RepoMoviesResult, LiveData<PagedList<Movie>>>() {
                    @Override
                    public LiveData<PagedList<Movie>> apply(RepoMoviesResult input) {
                        return input.data;
                    }
                });

        networkState = Transformations.switchMap(repoMoviesResult, new Function<RepoMoviesResult, LiveData<Resource>>() {
            @Override
            public LiveData<Resource> apply(RepoMoviesResult input) {
                return input.resource;
            }
        });
    }

    public LiveData<PagedList<Movie>> getPagedList() {
        return pagedList;
    }

    public LiveData<Resource> getNetworkState() {
        return networkState;
    }

    public MoviesFilterType getCurrentSorting() {
        return sortBy.getValue();
    }

    public LiveData<Integer> getCurrentTitle() {
        return currentTitle;
    }

    public void setSortMoviesBy(int id) {
        MoviesFilterType filterType = null;
        Integer title = null;
        switch (id) {
            case R.id.action_popular_movies: {
                if (sortBy.getValue() == MoviesFilterType.POPULAR)
                    return;

                filterType = MoviesFilterType.POPULAR;
                title = R.string.action_popular;
                break;
            }
            case R.id.action_top_rated: {
                if (sortBy.getValue() == MoviesFilterType.TOP_RATED)
                    return;

                filterType = MoviesFilterType.TOP_RATED;
                title = R.string.action_top_rated;
                break;
            }
        }
        sortBy.setValue(filterType);
        currentTitle.setValue(title);
    }
    public void retry() {
        repoMoviesResult.getValue().sourceLiveData.getValue().retryCallback.invoke();
    }
}