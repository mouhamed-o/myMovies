package fr.esiea.mymovie.databinding;
import fr.esiea.mymovie.R;
import fr.esiea.mymovie.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemNetworkStateBindingImpl extends ItemNetworkStateBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemNetworkStateBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private ItemNetworkStateBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[1]
            , (android.widget.ProgressBar) bindings[2]
            , (android.widget.Button) bindings[3]
            );
        this.errorMsg.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progressBar.setTag(null);
        this.retryButton.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.resource == variableId) {
            setResource((fr.esiea.mymovie.data.local.model.Resource) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setResource(@Nullable fr.esiea.mymovie.data.local.model.Resource Resource) {
        this.mResource = Resource;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.resource);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
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
        java.lang.String resourceMessage = null;
        boolean resourceMessageIsEmpty = false;
        boolean resourceStatusStatusLOADING = false;
        fr.esiea.mymovie.data.local.model.Resource resource = mResource;
        fr.esiea.mymovie.data.local.model.Resource.Status resourceStatus = null;
        boolean resourceStatusStatusERROR = false;
        java.lang.String resourceMessageIsEmptyErrorMsgAndroidStringUnknownErrorResourceMessage = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (resource != null) {
                    // read resource.message
                    resourceMessage = resource.message;
                    // read resource.status
                    resourceStatus = resource.status;
                }


                if (resourceMessage != null) {
                    // read resource.message.isEmpty()
                    resourceMessageIsEmpty = resourceMessage.isEmpty();
                }
            if((dirtyFlags & 0x3L) != 0) {
                if(resourceMessageIsEmpty) {
                        dirtyFlags |= 0x8L;
                }
                else {
                        dirtyFlags |= 0x4L;
                }
            }
                // read resource.status == Status.LOADING
                resourceStatusStatusLOADING = (resourceStatus) == (fr.esiea.mymovie.data.local.model.Resource.Status.LOADING);
                // read resource.status == Status.ERROR
                resourceStatusStatusERROR = (resourceStatus) == (fr.esiea.mymovie.data.local.model.Resource.Status.ERROR);
        }
        // batch finished

        if ((dirtyFlags & 0x3L) != 0) {

                // read resource.message.isEmpty() ? @android:string/unknown_error : resource.message
                resourceMessageIsEmptyErrorMsgAndroidStringUnknownErrorResourceMessage = ((resourceMessageIsEmpty) ? (errorMsg.getResources().getString(R.string.unknown_error)) : (resourceMessage));
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.errorMsg, resourceMessageIsEmptyErrorMsgAndroidStringUnknownErrorResourceMessage);
            fr.esiea.mymovie.ui.moviedetails.BindingAdapters.showHide(this.errorMsg, resourceStatusStatusERROR);
            fr.esiea.mymovie.ui.moviedetails.BindingAdapters.showHide(this.progressBar, resourceStatusStatusLOADING);
            fr.esiea.mymovie.ui.moviedetails.BindingAdapters.showHide(this.retryButton, resourceStatusStatusERROR);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): resource
        flag 1 (0x2L): null
        flag 2 (0x3L): resource.message.isEmpty() ? @android:string/unknown_error : resource.message
        flag 3 (0x4L): resource.message.isEmpty() ? @android:string/unknown_error : resource.message
    flag mapping end*/
    //end
}