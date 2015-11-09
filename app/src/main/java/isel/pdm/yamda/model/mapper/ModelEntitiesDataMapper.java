package isel.pdm.yamda.model.mapper;

import java.util.ArrayList;
import java.util.List;

import isel.pdm.yamda.data.entity.IConfiguration;
import isel.pdm.yamda.data.entity.TMDbConfiguration;
import isel.pdm.yamda.data.entity.tmdb.MovieDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieListDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieListingDTO;
import isel.pdm.yamda.model.entity.Actor;
import isel.pdm.yamda.model.entity.Crew;
import isel.pdm.yamda.model.entity.Genre;
import isel.pdm.yamda.model.entity.MovieDetails;
import isel.pdm.yamda.model.entity.MovieListDetails;

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
    public MovieDetails transform(MovieDTO dto) {
        Genre[] genres = this.createGenres(dto.getGenres());
        Actor[] actors = this.createActors(dto.getCredits().getActors());
        Crew[] crew = this.createCrew(dto.getCredits().getCrew());

        return new MovieDetails(dto.getId(),
                                dto.getTitle(),
                                dto.getReleaseDate(),
                                dto.getVoteAverage(),
                                dto.getOverview(),
                                createPosterLink(dto.getPosterPath()),
                                genres,
                                dto.getHomepage(),
                                dto.getOriginalLanguage(),
                                dto.getOriginalTitle(),
                                crew,
                                actors
        );
    }

    /**
     * Transform a movie list details data entity to a business details model
     * @param dto
     * @return
     */
    public MovieListDetails transform(MovieListDTO dto) {
        Genre[] genres = this.createGenres(dto.getGenreIds());

        return new MovieListDetails(dto.getId(),
                                    dto.getTitle(),
                                    dto.getReleaseDate(),
                                    dto.getOverview(),
                                    createPosterLink(dto.getPosterPath()),
                                    genres,
                                    dto.getVoteAverage()
                                    );
    }

    /**
     * Transform a movie list data entity to a business list model
     * @param dto
     * @return
     */
    public List<MovieListDetails> transform(MovieListingDTO dto) {
        List<MovieListDetails> movies = new ArrayList<>();
        List<MovieListDTO> dtoList = dto.getResults();
        for (MovieListDTO movie : dtoList) {
            movies.add(transform(movie));
        }
        return movies;
    }

    /**
     * Transform a CrewDTO to a Crew model
     * @param crew
     * @return
     */
    private Crew[] createCrew(MovieDTO.CrewDTO[] crew) {
        Crew[] res = new Crew[crew.length];

        for (int i = 0; i < crew.length; i++) {
            res[i] = new Crew(crew[i].getCreditId(),
                               crew[i].getDepartment(),
                               crew[i].getId(),
                               crew[i].getJob(),
                               crew[i].getName(),
                               crew[i].getProfilePath()
            );
        }

        return res;
    }

    /**
     * Transform a ActorsDTO to a Actor model
     * @param actors
     * @return
     */
    private Actor[] createActors(MovieDTO.ActorDTO[] actors) {
        Actor[] res = new Actor[actors.length];

        for (int i = 0; i < actors.length; i++) {
            res[i] = new Actor(actors[i].getCast_id(),
                               actors[i].getCharacter(),
                               actors[i].getId(),
                               actors[i].getName(),
                               actors[i].getProfilePath(),
                               actors[i].getOrder()
            );
        }

        return res;
    }

    /**
     * Transform a Genres DTO to a Genre model
     * @param genres
     * @return
     */
    private Genre[] createGenres(MovieDTO.GenreDTO[] genres) {
        Genre[] res = new Genre[genres.length];

        for (int i = 0; i < genres.length; i++) {
            res[i] = new Genre(genres[i].getId(), genres[i].getName());
        }

        return res;
    }

    /**
     * Create a genres array from a configuration
     * @param genreIds
     * @return
     */
    private Genre[] createGenres(int[] genreIds) {
        Genre[] res = new Genre[genreIds.length];

        for (int i = 0; i < genreIds.length; i++) {
            res[i] = new Genre(genreIds[i], this.configuration.getGenre(genreIds[i]));
        }

        return res;
    }


    /**
     * Transform a relative path to a complete URI poster image
     * @param path
     * @return
     */
    private String createPosterLink(String path) {
        if(path == null) return null;

        StringBuffer string = new StringBuffer();

        string.append(this.configuration.getImagesURI());
        string.append(this.configuration.getListPosterSize());
        string.append(path);

        return string.toString();
    }
}
