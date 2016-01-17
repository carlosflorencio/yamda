package isel.pdm.yamda.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import isel.pdm.yamda.R;
import isel.pdm.yamda.Utils;
import isel.pdm.yamda.model.MovieCredits;
import isel.pdm.yamda.ui.activity.MovieCreditsActivity;
import isel.pdm.yamda.ui.fragment.base.LoadDataFragment;
import isel.pdm.yamda.ui.presenter.MovieCreditsPresenter;
import isel.pdm.yamda.ui.presenter.base.IPresenter;

public class MovieCreditsFragment extends LoadDataFragment<MovieCredits> {

    private MovieCreditsPresenter creditsPresenter;
    private ListView actors_list;
    private ListView crew_list;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View viewContainer = super.onCreateView(inflater, container, savedInstanceState);

        this.creditsPresenter = (MovieCreditsPresenter) this.presenter;

        actors_list = (ListView) this.mainView.findViewById(R.id.actors_list);
        crew_list = (ListView) this.mainView.findViewById(R.id.crew_list);

        if (!getArguments().isEmpty()) {
            int id = getArguments().getInt(MovieCreditsActivity.ID_TAG);
            this.creditsPresenter.setMovieId(id);
            this.creditsPresenter.execute();
        }

        return viewContainer;
    }

    @Override
    protected int getLayout() {
        return R.layout.movie_credits;
    }

    @Override
    public void setData(MovieCredits data) {
        this.showResults();

        String[] actors = new String[data.getActors().length];
        for (int i = 0; i < data.getActors().length; i++) {
            actors[i] = data.getActors()[i].getName();
        }

        String[] crew = new String[data.getCrew().length];
        for (int i = 0; i < data.getCrew().length; i++) {
            crew[i] = data.getCrew()[i].getName();
        }

        actors_list.setAdapter(
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                                         actors));
        crew_list.setAdapter(
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, crew));

        Utils.setDynamicHeight(actors_list);
        Utils.setDynamicHeight(crew_list);
    }

    @Override
    protected IPresenter createPresenter() {
        return new MovieCreditsPresenter(this);
    }
}
