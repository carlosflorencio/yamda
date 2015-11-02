package isel.pdm.yamda.model.mapper;

import java.util.ArrayList;
import java.util.List;

import isel.pdm.yamda.data.entity.IConfiguration;
import isel.pdm.yamda.data.entity.TMDbConfiguration;
import isel.pdm.yamda.data.entity.tmdb.MovieDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieListDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieListingDTO;
import isel.pdm.yamda.model.entity.Movie;

/**
 * This class knows how to transform a data entity to a model entity
 */
public class ModelEntitiesDataMapper {

    private IConfiguration configuration;

    public ModelEntitiesDataMapper() {
        //TODO: change this!! the configuration should be requested and cached and not hard coded
        this.configuration = new TMDbConfiguration();
    }

    /**
     * Transform a movie data entity to a business model
     * @param dto
     * @return
     */
    public Movie transform(MovieDTO dto) {
        return new Movie(dto.getId(),
                dto.getTitle(),
                dto.getOverview(),
                dto.getGenres(),
                dto.getRelease_date(),
                dto.getStatus(),
                this.createDetailPosterLink(dto.getPoster_path()),
                dto.getVote_average());
    }

    /**
     * Transform a movie list data entity to a business list model
     * @param dto
     * @return
     */
    public List<Movie> transform(MovieListingDTO dto) {
        List<Movie> movies = new ArrayList<>();
        List<MovieListDTO> dtoList = dto.getResults();
        for (MovieListDTO movie : dtoList) {
            movies.add(transform(movie));
        }
        return movies;
    }

    /**
     * Transform a movie list details data entity to a business details model
     * @param dto
     * @return
     */
    public Movie transform(MovieListDTO dto) {
        return new Movie(dto.getId(),
                dto.getTitle(),
                null,
                null,
                dto.getRelease_date(),
                null,
                createListPosterLink(dto.getPoster_path()),
                dto.getVote_average());
    }


    /**
     * Transform a relative path to a complete URI poster image
     * @param path
     * @return
     */
    private String createListPosterLink(String path) {
        if(path == null) return null;

        return this.configuration.getImagesURI() + this.configuration.getListPosterSize() + path;
    }

    private String createDetailPosterLink(String path) {
        return this.configuration.getImagesURI() + this.configuration.getDetailPosterSize() + path;
    }
}
