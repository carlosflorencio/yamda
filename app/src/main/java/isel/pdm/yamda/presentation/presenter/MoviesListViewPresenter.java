package isel.pdm.yamda.presentation.presenter;

import java.io.IOException;
import java.util.ArrayList;

import isel.pdm.yamda.data.repository.MovieRepository;
import isel.pdm.yamda.model.entity.mapper.MovieModelMapper;
import isel.pdm.yamda.model.repository.IMovieRepository;
import isel.pdm.yamda.presentation.view.activity.contract.IMoviesListView;
import isel.pdm.yamda.presentation.view.entity.MovieView;

public class MoviesListViewPresenter implements IPresenter {

    private IMoviesListView view;

    private MovieModelMapper mapper;

    private IMovieRepository repository;

    public MoviesListViewPresenter(IMoviesListView v, MovieModelMapper mapper, IMovieRepository repository, String type) {
        this.view = v;
        this.mapper = mapper;
        this.repository = repository;
        this.view.setItems(createList(type));
    }

    /** {@inheritDoc} **/
    @Override
    public void onResume() {
        this.view.showProgress();
    }

    /** {@inheritDoc} **/
    @Override
    public void onPause() {

    }

    /** {@inheritDoc} **/
    @Override
    public void onDestroy() {

    }

    private ArrayList<MovieView> createList(String type) {
        try {
            return (ArrayList<MovieView>) mapper.transform(repository.getListing(type));
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException();
    }

    public static MoviesListViewPresenter create(IMoviesListView v, String type) {
        return new MoviesListViewPresenter(v, new MovieModelMapper(), MovieRepository.create(), type);
    }
}
