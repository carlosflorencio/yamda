package isel.pdm.yamda.data.repository.base;


import android.content.Context;

import isel.pdm.yamda.data.api.TMDbApiSync;
import isel.pdm.yamda.data.mapper.CursorModelEntitiesDataMapper;
import isel.pdm.yamda.data.mapper.DTOModelEntitiesDataMapper;
import isel.pdm.yamda.data.repository.LocalMovieRepository;
import isel.pdm.yamda.data.repository.TMDbMovieRepository;

/**
 * This class knows how to construct each type of the repositories
 */
public class MovieRepositoryFactory {

    /**
     * Create and return an instance of a TMDbMovieRepository
     * @return
     */
    public static ICloudMovieRepository getCloudRepository() {
        return new TMDbMovieRepository(new TMDbApiSync(), new DTOModelEntitiesDataMapper());
    }

    /**
     * Create and return an instance of a LocalMovieRepository
     * @param ctx
     * @return
     */
    public static ILocalMovieRepository getLocalRepository(Context ctx) {
        return new LocalMovieRepository(ctx, new CursorModelEntitiesDataMapper());
    }

}
