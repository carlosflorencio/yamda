package isel.pdm.yamda.data.entity.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import isel.pdm.yamda.data.entity.ConfigurationDTO;
import isel.pdm.yamda.data.entity.MovieDTO;
import isel.pdm.yamda.data.entity.MovieListDTO;
import isel.pdm.yamda.data.entity.MovieListingDTO;
import isel.pdm.yamda.model.entity.Configuration;
import isel.pdm.yamda.model.entity.Movie;

/**
 * Created by Nuno on 31/10/2015.
 */
public class MovieDataMapper {

    public Movie transform(MovieDTO dto) {
        return new Movie(dto.getTitle(),
                transformToString(dto.getGenres()),
                dto.getRelease_date(),
                dto.getStatus(),
                dto.getPoster_path());
    }

    public List<Movie> transform(MovieListingDTO dto) {
        List<Movie> movies = new ArrayList<>();
        List<MovieListDTO> dtoList = dto.getResults();
        for (MovieListDTO movie : dtoList) {
            movies.add(transform(movie));
        }
        return movies;
    }

    public Movie transform(MovieListDTO dto) {
        return new Movie(dto.getTitle(), null, dto.getRelease_date(), null, dto.getPoster_path());
    }

    private String[] transformToString(MovieDTO.GenreDTO[] genres) {
        String[] str = new String[genres.length];
        for (int i = 0; i < genres.length; i++) {
            str[i] = genres[i].getName();
        }
        return str;
    }

    public Configuration transform(ConfigurationDTO apiConfiguration) {
        return new Configuration(apiConfiguration.getBaseUrl(), apiConfiguration.getPosterSizes());
    }

    public HashMap<String, List<Movie>> transform(HashMap<String, MovieListingDTO> listings) {
        HashMap<String, List<Movie>> map = new HashMap<>();
        for (String str : listings.keySet()) {
            map.put(str, this.transform(listings.get(str)));
        }
        return map;
    }
}
