package isel.pdm.yamda.presentation.presenter;

import isel.pdm.yamda.model.repository.IMovieRepository;
import isel.pdm.yamda.presentation.creator.DataFactory;
import isel.pdm.yamda.presentation.presenter.common.MovieListablePresenter;
import isel.pdm.yamda.presentation.view.fragment.SoonMoviesListFragment;

public class SoonMoviesListPresenter extends MovieListablePresenter {

    public SoonMoviesListPresenter(SoonMoviesListFragment fragment) {
        super(fragment.getActivity(), fragment.getListView());

        this.askForData();
    }

    private void askForData() {
        DataFactory factory = new DataFactory();
        IMovieRepository repo = factory.getMoviesRepository();

        this.showLoading();
        repo.setSoonMovies(this, 1);
    }
}
