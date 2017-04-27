package topzme.couseandroid;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/**
 * Created by weerapon on 9/16/16.
 */
public class BundleSavedState extends View.BaseSavedState {

    private Bundle bundle;

    public BundleSavedState(Parcel source) {
        super(source);
        setBundle(source.readBundle());
    }

    @TargetApi(Build.VERSION_CODES.N)
    public BundleSavedState(Parcel source, ClassLoader loader) {
        super(source, loader);
    }

    public BundleSavedState(Parcelable superState) {
        super(superState);
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeBundle(getBundle());
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel parcel) {
            return new BundleSavedState(parcel);
        }

        @Override
        public Object[] newArray(int i) {
            return new BundleSavedState[i];
        }
    };

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
}
