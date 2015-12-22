package isel.pdm.yamda.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class is used for representing a Genre
 * <p/>
 * Instances are designed to be immutable, and are therefore thread-safe.
 */
public final class Genre implements Parcelable {

    private final int    id;
    private final String name;

    /**
     * Constructs a new Genre with the specified id and name
     *
     * @param id
     * @param name
     */
    public Genre(int id, String name) {
        this.id = id;

        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /*
    |--------------------------------------------------------------------------
    | Parcelable methods
    |--------------------------------------------------------------------------
    */
    public Genre(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    public static final Creator<Genre> CREATOR = new Creator<Genre>() {
        @Override
        public Genre createFromParcel(Parcel in) {
            return new Genre(in);
        }

        @Override
        public Genre[] newArray(int size) {
            return new Genre[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
