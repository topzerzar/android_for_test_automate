package topzme.couseandroid;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by weerapon on 9/16/16.
 */
public class CoordinateParcelable implements Parcelable {

    public int x;
    public int y;
    public int z;


    // Empty
    public CoordinateParcelable() {

    }

    protected CoordinateParcelable(Parcel in) {
        x = in.readInt();
        y = in.readInt();
        z = in.readInt();
    }

    public static final Creator<CoordinateParcelable> CREATOR = new Creator<CoordinateParcelable>() {
        @Override
        public CoordinateParcelable createFromParcel(Parcel in) {
            return new CoordinateParcelable(in);
        }

        @Override
        public CoordinateParcelable[] newArray(int size) {
            return new CoordinateParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(x);
        parcel.writeInt(y);
        parcel.writeInt(z);
    }
}
