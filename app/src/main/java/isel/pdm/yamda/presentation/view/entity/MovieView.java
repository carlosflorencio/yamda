package isel.pdm.yamda.presentation.view.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nuno on 31/10/2015.
 */
public class MovieView implements Parcelable {

    private int id;

    private String title;

    private String status;

    private String release_date;

    private float rating;

    private String overview;

    private String poster;

    private String[] genres;

    public MovieView(int id, String title, String status, String release_date, String overview, String poster, String[] genres, float rating) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.release_date = release_date;
        this.overview = overview;
        this.poster = poster;
        this.genres = genres;
        this.rating = rating;
    }

    protected MovieView(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.status = in.readString();
        this.release_date = in.readString();
        this.rating = in.readFloat();
        this.overview = in.readString();
        this.poster = in.readString();
        this.genres = in.createStringArray();
    }

    public static final Creator<MovieView> CREATOR = new Creator<MovieView>() {
        @Override
        public MovieView createFromParcel(Parcel in) {
            return new MovieView(in);
        }

        @Override
        public MovieView[] newArray(int size) {
            return new MovieView[size];
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
        dest.writeString(status);
        dest.writeString(release_date);
        dest.writeFloat(rating);
        dest.writeString(overview);
        dest.writeString(poster);
        dest.writeStringArray(genres);
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getGenres() {
        StringBuffer stringBuffer = new StringBuffer();

        for (String genre : genres) {
            stringBuffer.append(genre + ", ");
        }

        return stringBuffer.substring(0, stringBuffer.length() - 2).toString();
    }

    public float getRating() {
        return rating;
    }

    public String getPoster() {
        return poster;
    }

    public int getId() {
        return id;
    }
}
