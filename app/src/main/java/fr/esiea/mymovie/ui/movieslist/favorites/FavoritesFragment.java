package fr.esiea.mymovie.ui.movieslist.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import fr.esiea.mymovie.R;
import fr.esiea.mymovie.data.local.model.Movie;
import fr.esiea.mymovie.databinding.FragmentFavoriteMoviesBinding;
import fr.esiea.mymovie.ui.movieslist.MoviesActivity;
import fr.esiea.mymovie.utils.Injection;
import fr.esiea.mymovie.utils.ItemOffsetDecoration;
import fr.esiea.mymovie.utils.ViewModelFactory;

public class FavoritesFragment extends Fragment {

    private FavoritesViewModel viewModel;
    private FragmentFavoriteMoviesBinding binding;

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((MoviesActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.favorites));
        viewModel = obtainViewModel(getActivity());
        setupListAdapter();
    }

    private void setupListAdapter() {
        RecyclerView recyclerView = binding.moviesList.rvMovieList;
        final FavoritesAdapter favoritesAdapter = new FavoritesAdapter();
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setAdapter(favoritesAdapter);
        recyclerView.setLayoutManager(layoutManager);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);
        viewModel.getFavoriteListLiveData().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movieList) {
                if (movieList.isEmpty()) {
                    binding.moviesList.rvMovieList.setVisibility(View.GONE);
                    binding.emptyState.setVisibility(View.VISIBLE);
                } else {
                    binding.moviesList.rvMovieList.setVisibility(View.VISIBLE);
                    binding.emptyState.setVisibility(View.GONE);
                    favoritesAdapter.submitList(movieList);
                }
            }
        });
    }

    private FavoritesViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = Injection.provideViewModelFactory(activity);
        return ViewModelProviders.of(activity, factory).get(FavoritesViewModel.class);
    }
}
