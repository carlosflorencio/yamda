package isel.pdm.yamda.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class is used for representing a Movie obtained from the data layer
 * by a movie request.
 *
 * Instances can also be used to transport information between components of the
 * application (e.g. Activities, Services). Because those interactions may cross process
 * boundaries, the class supports the {@link android.os.Parcelable} contract and conventions.
 *
 * Instances are designed to be immutable, and are therefore thread-safe.
 */
public final class MovieDetails implements Parcelable {

    /** The movie id  **/
    private final int id;
    /** The movie title **/
    private final String title;
    /** The movie release date **/
    private final String release_date;
    /** The movie rating **/
    private final float rating;
    /**
     * The movie number of votes
     **/
    private final int voteCount;
    /**
     * The movie run time
     **/
    private final int runtime;
    /** The movie overview description **/
    private final String overview;
    /** The movie poster image **/
    private final String poster;
    /** The movie genres **/
    private final Genre[] genres;
    /** The movie homepage **/
    private final String homepage;
    /** The movie original language **/
    private final String original_language;
    /** The movie original title **/
    private final String original_title;
    /** The movie crew team **/
    private final Crew[] crew;
    /** The movie Cast team **/
    private final Actor[] actors;
    /**
     * The movie backdrop
     **/
    private final String backdrop;

    private boolean isBeingFollowed;

    public MovieDetails(int id, String title, String release_date, float rating, int voteCount, int runtime, String overview,
                        String poster, String backdrop, Genre[] genres, String homepage, String original_language,
                        String original_title, Crew[] crew, Actor[] actors) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.rating = rating;
        this.voteCount = voteCount;
        this.runtime = runtime;
        this.overview = overview;
        this.poster = poster;
        this.backdrop = backdrop;
        this.genres = genres;

        this.homepage = homepage;
        this.original_language = original_language;
        this.original_title = original_title;
        this.crew = crew;
        this.actors = actors;
        this.isBeingFollowed = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public float getRating() {
        return rating;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster() {
        return poster;
    }

    public Genre[] getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public Crew[] getCrew() {
        return crew;
    }

    public Actor[] getActors() {
        return actors;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBeingFollowed(boolean beingFollowed) {
        isBeingFollowed = beingFollowed;
    }

    public boolean isBeingFollowed() {
        return isBeingFollowed;
    }

    /*
            |--------------------------------------------------------------------------
            | Parcelable methods
            |--------------------------------------------------------------------------
            */
    protected MovieDetails(Parcel in) {
        id = in.readInt();
        title = in.readString();
        release_date = in.readString();
        rating = in.readFloat();
        voteCount = in.readInt();
        runtime = in.readInt();
        overview = in.readString();
        poster = in.readString();
        backdrop = in.readString();
        genres = in.createTypedArray(Genre.CREATOR);
        homepage = in.readString();
        original_language = in.readString();
        original_title = in.readString();
        crew = in.createTypedArray(Crew.CREATOR);
        actors = in.createTypedArray(Actor.CREATOR);
        isBeingFollowed = in.readInt() == 1;
    }

    public static final Creator<MovieDetails> CREATOR = new Creator<MovieDetails>() {
        @Override
        public MovieDetails createFromParcel(Parcel in) {
            return new MovieDetails(in);
        }

        @Override
        public MovieDetails[] newArray(int size) {
            return new MovieDetails[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(release_date);
        dest.writeFloat(rating);
        dest.writeInt(voteCount);
        dest.writeInt(runtime);
        dest.writeString(overview);
        dest.writeString(poster);
        dest.writeString(backdrop);
        dest.writeTypedArray(genres, 0);
        dest.writeString(homepage);
        dest.writeString(original_language);
        dest.writeString(original_title);
        dest.writeTypedArray(crew, 0);
        dest.writeTypedArray(actors, 0);
        dest.writeInt(isBeingFollowed ? 1 : 0);
    }
}
