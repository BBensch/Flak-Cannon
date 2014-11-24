package kamikaze.team.flakcannon;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evan on 10/5/2014.
 */
public class fireWorks implements Parcelable {

    public int duration;
    public int pattern;
    public String color;

    public fireWorks(int duration, int pattern, String color ){
        this.duration = duration;
        this.pattern = pattern;
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(duration);
        parcel.writeInt(pattern);
        parcel.writeString(color);
    }

    public static final Parcelable.Creator<fireWorks> CREATOR
            = new Parcelable.Creator<fireWorks>() {
        public fireWorks createFromParcel(Parcel in) {
            return new fireWorks(in);
        }

        public fireWorks[] newArray(int size) {
            return new fireWorks[size];
        }
    };

    private fireWorks(Parcel in) {
        duration = in.readInt();
        pattern = in.readInt();
        color = in.readString();
    }

}
