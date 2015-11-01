package isel.pdm.yamda.presentation.presenter;

import isel.pdm.yamda.model.repository.IMovieRepository;
import isel.pdm.yamda.presentation.creator.DataFactory;
import isel.pdm.yamda.presentation.presenter.base.MoviesListViewPresenter;

public class TopMoviesListPresenter extends MoviesListViewPresenter {

    @Override
    public void initialize() {
        //TODO: Change this to DI
        DataFactory factory = new DataFactory();
        IMovieRepository repo = factory.getMoviesRepository();

        this.showLoading();
        repo.setTopMovies(this, 1);
    }
}
