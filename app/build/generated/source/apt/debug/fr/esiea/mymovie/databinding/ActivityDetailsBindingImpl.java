package fr.esiea.mymovie.databinding;
import fr.esiea.mymovie.R;
import fr.esiea.mymovie.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityDetailsBindingImpl extends ActivityDetailsBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(8);
        sIncludes.setIncludes(0, 
            new String[] {"item_network_state"},
            new int[] {5},
            new int[] {R.layout.item_network_state});
        sIncludes.setIncludes(3, 
            new String[] {"partial_details_info"},
            new int[] {4},
            new int[] {R.layout.partial_details_info});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.collapsing_toolbar, 6);
        sViewsWithIds.put(R.id.toolbar, 7);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityDetailsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }
    private ActivityDetailsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (com.google.android.material.appbar.AppBarLayout) bindings[1]
            , (com.google.android.material.appbar.CollapsingToolbarLayout) bindings[6]
            , (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0]
            , (android.widget.ImageView) bindings[2]
            , (androidx.core.widget.NestedScrollView) bindings[3]
            , (fr.esiea.mymovie.databinding.PartialDetailsInfoBinding) bindings[4]
            , (fr.esiea.mymovie.databinding.ItemNetworkStateBinding) bindings[5]
            , (androidx.appcompat.widget.Toolbar) bindings[7]
            );
        this.appbar.setTag(null);
        this.coordinatorLayout.setTag(null);
        this.imageMovieBackdrop.setTag(null);
        this.movieDetails.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x10L;
        }
        movieDetailsInfo.invalidateAll();
        networkState.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (movieDetailsInfo.hasPendingBindings()) {
            return true;
        }
        if (networkState.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.resource == variableId) {
            setResource((fr.esiea.mymovie.data.local.model.Resource) variable);
        }
        else if (BR.movieDetails == variableId) {
            setMovieDetails((fr.esiea.mymovie.data.local.model.MovieDetails) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setResource(@Nullable fr.esiea.mymovie.data.local.model.Resource Resource) {
        this.mResource = Resource;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.resource);
        super.requestRebind();
    }
    public void setMovieDetails(@Nullable fr.esiea.mymovie.data.local.model.MovieDetails MovieDetails) {
        this.mMovieDetails = MovieDetails;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.movieDetails);
        super.requestRebind();
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        movieDetailsInfo.setLifecycleOwner(lifecycleOwner);
        networkState.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeMovieDetailsInfo((fr.esiea.mymovie.databinding.PartialDetailsInfoBinding) object, fieldId);
            case 1 :
                return onChangeNetworkState((fr.esiea.mymovie.databinding.ItemNetworkStateBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeMovieDetailsInfo(fr.esiea.mymovie.databinding.PartialDetailsInfoBinding MovieDetailsInfo, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeNetworkState(fr.esiea.mymovie.databinding.ItemNetworkStateBinding NetworkState, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String movieDetailsMovieBackdropPath = null;
        boolean movieDetailsMovieJavaLangObjectNull = false;
        fr.esiea.mymovie.data.local.model.Movie movieDetailsMovie = null;
        fr.esiea.mymovie.data.local.model.Resource resource = mResource;
        fr.esiea.mymovie.data.local.model.MovieDetails MovieDetails1 = mMovieDetails;

        if ((dirtyFlags & 0x14L) != 0) {
        }
        if ((dirtyFlags & 0x18L) != 0) {



                if (MovieDetails1 != null) {
                    // read movieDetails.movie
                    movieDetailsMovie = MovieDetails1.movie;
                }


                if (movieDetailsMovie != null) {
                    // read movieDetails.movie.backdropPath
                    movieDetailsMovieBackdropPath = movieDetailsMovie.getBackdropPath();
                }
                // read movieDetails.movie != null
                movieDetailsMovieJavaLangObjectNull = (movieDetailsMovie) != (null);
        }
        // batch finished
        if ((dirtyFlags & 0x18L) != 0) {
            // api target 1

            fr.esiea.mymovie.ui.moviedetails.BindingAdapters.showHide(this.appbar, movieDetailsMovieJavaLangObjectNull);
            fr.esiea.mymovie.ui.moviedetails.BindingAdapters.bindImage(this.imageMovieBackdrop, movieDetailsMovieBackdropPath, true);
            fr.esiea.mymovie.ui.moviedetails.BindingAdapters.showHide(this.movieDetails, movieDetailsMovieJavaLangObjectNull);
            this.movieDetailsInfo.setMovieDetails(MovieDetails1);
        }
        if ((dirtyFlags & 0x14L) != 0) {
            // api target 1

            this.networkState.setResource(resource);
        }
        executeBindingsOn(movieDetailsInfo);
        executeBindingsOn(networkState);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): movieDetailsInfo
        flag 1 (0x2L): networkState
        flag 2 (0x3L): resource
        flag 3 (0x4L): movieDetails
        flag 4 (0x5L): null
    flag mapping end*/
    //end
}