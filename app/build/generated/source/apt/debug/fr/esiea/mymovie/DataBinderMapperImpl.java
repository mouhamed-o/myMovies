package fr.esiea.mymovie;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import fr.esiea.mymovie.databinding.ActivityDetailsBindingImpl;
import fr.esiea.mymovie.databinding.FragmentDiscoverMoviesBindingImpl;
import fr.esiea.mymovie.databinding.FragmentFavoriteMoviesBindingImpl;
import fr.esiea.mymovie.databinding.ItemCastBindingImpl;
import fr.esiea.mymovie.databinding.ItemMovieBindingImpl;
import fr.esiea.mymovie.databinding.ItemNetworkStateBindingImpl;
import fr.esiea.mymovie.databinding.ItemReviewBindingImpl;
import fr.esiea.mymovie.databinding.ItemTrailerBindingImpl;
import fr.esiea.mymovie.databinding.PartialDetailsInfoBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYDETAILS = 1;

  private static final int LAYOUT_FRAGMENTDISCOVERMOVIES = 2;

  private static final int LAYOUT_FRAGMENTFAVORITEMOVIES = 3;

  private static final int LAYOUT_ITEMCAST = 4;

  private static final int LAYOUT_ITEMMOVIE = 5;

  private static final int LAYOUT_ITEMNETWORKSTATE = 6;

  private static final int LAYOUT_ITEMREVIEW = 7;

  private static final int LAYOUT_ITEMTRAILER = 8;

  private static final int LAYOUT_PARTIALDETAILSINFO = 9;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(9);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(fr.esiea.mymovie.R.layout.activity_details, LAYOUT_ACTIVITYDETAILS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fr.esiea.mymovie.R.layout.fragment_discover_movies, LAYOUT_FRAGMENTDISCOVERMOVIES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fr.esiea.mymovie.R.layout.fragment_favorite_movies, LAYOUT_FRAGMENTFAVORITEMOVIES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fr.esiea.mymovie.R.layout.item_cast, LAYOUT_ITEMCAST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fr.esiea.mymovie.R.layout.item_movie, LAYOUT_ITEMMOVIE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fr.esiea.mymovie.R.layout.item_network_state, LAYOUT_ITEMNETWORKSTATE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fr.esiea.mymovie.R.layout.item_review, LAYOUT_ITEMREVIEW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fr.esiea.mymovie.R.layout.item_trailer, LAYOUT_ITEMTRAILER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fr.esiea.mymovie.R.layout.partial_details_info, LAYOUT_PARTIALDETAILSINFO);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYDETAILS: {
          if ("layout/activity_details_0".equals(tag)) {
            return new ActivityDetailsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_details is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTDISCOVERMOVIES: {
          if ("layout/fragment_discover_movies_0".equals(tag)) {
            return new FragmentDiscoverMoviesBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_discover_movies is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTFAVORITEMOVIES: {
          if ("layout/fragment_favorite_movies_0".equals(tag)) {
            return new FragmentFavoriteMoviesBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_favorite_movies is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMCAST: {
          if ("layout/item_cast_0".equals(tag)) {
            return new ItemCastBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_cast is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMMOVIE: {
          if ("layout/item_movie_0".equals(tag)) {
            return new ItemMovieBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_movie is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMNETWORKSTATE: {
          if ("layout/item_network_state_0".equals(tag)) {
            return new ItemNetworkStateBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_network_state is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMREVIEW: {
          if ("layout/item_review_0".equals(tag)) {
            return new ItemReviewBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_review is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMTRAILER: {
          if ("layout/item_trailer_0".equals(tag)) {
            return new ItemTrailerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_trailer is invalid. Received: " + tag);
        }
        case  LAYOUT_PARTIALDETAILSINFO: {
          if ("layout/partial_details_info_0".equals(tag)) {
            return new PartialDetailsInfoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for partial_details_info is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(5);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "movieDetails");
      sKeys.put(2, "movie");
      sKeys.put(3, "resource");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(9);

    static {
      sKeys.put("layout/activity_details_0", fr.esiea.mymovie.R.layout.activity_details);
      sKeys.put("layout/fragment_discover_movies_0", fr.esiea.mymovie.R.layout.fragment_discover_movies);
      sKeys.put("layout/fragment_favorite_movies_0", fr.esiea.mymovie.R.layout.fragment_favorite_movies);
      sKeys.put("layout/item_cast_0", fr.esiea.mymovie.R.layout.item_cast);
      sKeys.put("layout/item_movie_0", fr.esiea.mymovie.R.layout.item_movie);
      sKeys.put("layout/item_network_state_0", fr.esiea.mymovie.R.layout.item_network_state);
      sKeys.put("layout/item_review_0", fr.esiea.mymovie.R.layout.item_review);
      sKeys.put("layout/item_trailer_0", fr.esiea.mymovie.R.layout.item_trailer);
      sKeys.put("layout/partial_details_info_0", fr.esiea.mymovie.R.layout.partial_details_info);
    }
  }
}
