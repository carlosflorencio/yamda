package isel.pdm.yamda.data.mapper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import isel.pdm.yamda.data.api.ITMDbServiceAPI;
import isel.pdm.yamda.data.api.entity.CreditsListingDTO;
import isel.pdm.yamda.data.api.entity.GenreListingDTO;
import isel.pdm.yamda.data.api.entity.MovieDTO;
import isel.pdm.yamda.data.api.entity.MovieListingDTO;
import isel.pdm.yamda.model.Actor;
import isel.pdm.yamda.model.Crew;
import isel.pdm.yamda.model.Genre;
import isel.pdm.yamda.model.Movie;
import isel.pdm.yamda.model.MovieCredits;
import isel.pdm.yamda.model.MovieDetails;

/**
 * This class knows how to transform a data entity to a model entity
 */
public class DTOModelEntitiesDataMapper {


    /**
     * Transform a movie data entity to a business model
     *
     * @param dto
     * @return
     */
    public MovieDetails transform(MovieDTO dto) {
        Genre[] genres = this.createGenres(dto.getGenres());

        return new MovieDetails(dto.getId(),
                                dto.getTitle(),
                                dto.getOriginalTitle(),
                                dto.getReleaseDate(),
                                createPosterLink(dto.getPosterPath()),
                                createBackdropLink(dto.getBackdropPath()),
                                dto.getVoteAverage(),
                                dto.getPopularity(),
                                dto.getRuntime(),
                                dto.getOverview(),
                                genres
        );
    }

    /**
     * Transform a movie list details data entity to a business details model
     *
     * @param dto
     * @return
     */
    public Movie transform(MovieListingDTO.MovieListDTO dto) {
        return new Movie(dto.getId(),
                         dto.getTitle(),
                         dto.getOriginalTitle(),
                         dto.getReleaseDate(),
                         createPosterLink(dto.getPosterPath()),
                         dto.getVoteAverage(),
                         dto.getPopularity()
        );
    }

    /**
     * Transform a movie list data entity to a business list model
     *
     * @param dto
     * @return
     */
    public List<Movie> transform(MovieListingDTO dto) {
        List<Movie> movies = new ArrayList<>();
        List<MovieListingDTO.MovieListDTO> dtoList = dto.getResults();
        for (MovieListingDTO.MovieListDTO movie : dtoList) {
            movies.add(transform(movie));
        }
        return movies;
    }

    /**
     * Transform a credits list data entity to a business model
     * @param data
     * @return
     */
    public MovieCredits transform(CreditsListingDTO data) {
        Actor[] actors = createActors(data.getActors());
        Crew[] crew = createCrew(data.getCrew());
        return new MovieCredits(data.getMovieId(), crew, actors);
    }

    /**
     * Transform a genres list data entity to a business list model
     *
     * @param dto
     * @return
     */
    public List<Genre> transform(GenreListingDTO dto) {
        List<Genre> genres = new LinkedList<>();
        List<GenreListingDTO.GenreDTO> dtoList = dto.getGenres();
        for (GenreListingDTO.GenreDTO genreDto : dtoList) {
            genres.add(new Genre(genreDto.getId(), genreDto.getName()));
        }
        return genres;
    }

    /**
     * Transform a CrewDTO to a Crew model
     *
     * @param crew
     * @return
     */
    private Crew[] createCrew(CreditsListingDTO.CrewDTO[] crew) {
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
     *
     * @param actors
     * @return
     */
    private Actor[] createActors(CreditsListingDTO.ActorDTO[] actors) {
        Actor[] res = new Actor[actors.length];

        for (int i = 0; i < actors.length; i++) {
            res[i] = new Actor(actors[i].getCastId(),
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
     *
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
     * Transform a relative path to a complete URI poster image
     *
     * @param path
     * @return
     */
    private String createPosterLink(String path) {
        if (path == null) return null;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(ITMDbServiceAPI.BASE_IMAGES_URL);
        stringBuilder.append(ITMDbServiceAPI.POSTER_SIZE);
        stringBuilder.append(path);

        return stringBuilder.toString();
    }

    /**
     * Transform a relative backdrop path to a complete URI
     *
     * @param path
     * @return
     */
    private String createBackdropLink(String path) {
        if (path == null) return null;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(ITMDbServiceAPI.BASE_IMAGES_URL);
        stringBuilder.append(ITMDbServiceAPI.BACKDROP_SIZE);
        stringBuilder.append(path);

        return stringBuilder.toString();
    }
}
