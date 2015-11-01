package isel.pdm.yamda.presentation.creator;

import isel.pdm.yamda.data.repository.MovieDataRepositorySetter;
import isel.pdm.yamda.data.repository.datasource.MovieDataStoreFactory;
import isel.pdm.yamda.model.mapper.ModelEntitiesDataMapper;
import isel.pdm.yamda.model.repository.IMovieRepository;

/**
 * This class knows how to construct the data layer
 */
public class DataFactory {

    /**
     * Construct the movies repository
     * @return
     */
    public IMovieRepository getMoviesRepository() {
        ModelEntitiesDataMapper dataMapper = new ModelEntitiesDataMapper();
        MovieDataStoreFactory dataFactory = new MovieDataStoreFactory();

        return new MovieDataRepositorySetter(dataFactory, dataMapper);
    }
}
