package topzme.couseandroid;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/**
 * Created by weerapon on 9/16/16.
 */
public class CustomViewSavedState extends View.BaseSavedState {

    private boolean blue;

    public CustomViewSavedState(Parcel source) {
        super(source);
    }

    @TargetApi(Build.VERSION_CODES.N)
    public CustomViewSavedState(Parcel source, ClassLoader loader) {
        super(source, loader);
    }

    public CustomViewSavedState(Parcelable superState) {
        super(superState);
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeInt(isBlue() ? 1 : 0);
    }

    public static final Creator CREATER = new Creator() {
        @Override
        public Object createFromParcel(Parcel parcel) {
            return new CustomViewSavedState(parcel);
        }

        @Override
        public Object[] newArray(int i) {
            return new CustomViewSavedState[i];
        }
    };

    public boolean isBlue() {
        return blue;
    }

    public void setBlue(boolean blue) {
        this.blue = blue;
    }
}
