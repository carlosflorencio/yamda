package isel.pdm.yamda.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class is used for representing an Actor Member
 * <p/>
 * Instances can also be used to transport information between components of the
 * application (e.g. Activities, Services). Because those interactions may cross process
 * boundaries, the class supports the {@link Parcelable} contract and conventions.
 * <p/>
 * Instances are designed to be immutable, and are therefore thread-safe.
 */
public final class Actor implements Parcelable {

    /**
     * The actor cast id
     **/
    private final String cast_id;
    /**
     * The actor character name
     **/
    private final String character;
    /**
     * The actor id
     **/
    private final int    id;
    /**
     * The actor name
     **/
    private final String name;
    /**
     * The actor profile
     **/
    private final String profile_path;
    /**
     * The actor order
     **/
    private final int    order;

    /**
     * Create an Actor model with the given parameters
     *
     * @param cast_id
     * @param character
     * @param id
     * @param name
     * @param profile_path
     * @param order
     */
    public Actor(String cast_id, String character, int id, String name,
                 String profile_path, int order) {
        this.cast_id = cast_id;
        this.character = character;
        this.id = id;
        this.name = name;
        this.profile_path = profile_path;
        this.order = order;
    }

    public String getCastId() {
        return cast_id;
    }

    public String getCharacter() {
        return character;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProfilePath() {
        return profile_path;
    }

    public int getOrder() {
        return order;
    }

    /*
    |--------------------------------------------------------------------------
    | Parcelable methods
    |--------------------------------------------------------------------------
    */
    protected Actor(Parcel in) {
        cast_id = in.readString();
        character = in.readString();
        id = in.readInt();
        name = in.readString();
        profile_path = in.readString();
        order = in.readInt();
    }

    public static final Creator<Actor> CREATOR = new Creator<Actor>() {
        @Override
        public Actor createFromParcel(Parcel in) {
            return new Actor(in);
        }

        @Override
        public Actor[] newArray(int size) {
            return new Actor[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cast_id);
        dest.writeString(this.character);
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.profile_path);
        dest.writeInt(this.order);
    }
}
