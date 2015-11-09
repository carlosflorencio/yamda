package isel.pdm.yamda.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class is used for representing a Crew Member like a director, producer, etc
 *
 * Instances can also be used to transport information between components of the
 * application (e.g. Activities, Services). Because those interactions may cross process
 * boundaries, the class supports the {@link android.os.Parcelable} contract and conventions.
 *
 * Instances are designed to be immutable, and are therefore thread-safe.
 */
public final class Crew implements Parcelable {

    /** The crew credit id **/
    private final String credit_id;
    /** The crew department **/
    private final String department;
    /** The crew id **/
    private final int id;
    /** The crew job **/
    private final String job;
    /** The crew name **/
    private final String name;
    /** The crew profile **/
    private final String profile_path;

    public Crew(String credit_id, String department, int id, String job, String name,
                String profile_path) {
        this.credit_id = credit_id;
        this.department = department;
        this.id = id;
        this.job = job;
        this.name = name;
        this.profile_path = profile_path;
    }

    public String getCredit_id() {
        return credit_id;
    }

    public String getDepartment() {
        return department;
    }

    public int getId() {
        return id;
    }

    public String getJob() {
        return job;
    }

    public String getName() {
        return name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    /*
    |--------------------------------------------------------------------------
    | Parcelable methods
    |--------------------------------------------------------------------------
    */
    protected Crew(Parcel in) {
        credit_id = in.readString();
        department = in.readString();
        id = in.readInt();
        job = in.readString();
        name = in.readString();
        profile_path = in.readString();
    }

    public static final Creator<Crew> CREATOR = new Creator<Crew>() {
        @Override
        public Crew createFromParcel(Parcel in) {
            return new Crew(in);
        }

        @Override
        public Crew[] newArray(int size) {
            return new Crew[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.credit_id);
        dest.writeString(this.department);
        dest.writeInt(this.id);
        dest.writeString(this.job);
        dest.writeString(this.name);
        dest.writeString(this.profile_path);
    }
}
