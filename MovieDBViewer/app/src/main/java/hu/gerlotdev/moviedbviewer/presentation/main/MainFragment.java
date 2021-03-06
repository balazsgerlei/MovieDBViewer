package hu.gerlotdev.moviedbviewer.presentation.main;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;


import java.util.List;

import javax.inject.Inject;

import hu.gerlotdev.moviedbviewer.R;
import hu.gerlotdev.moviedbviewer.data.model.Movie;
import hu.gerlotdev.moviedbviewer.data.model.MoviePage;
import hu.gerlotdev.moviedbviewer.data.network.MovieApi;
import hu.gerlotdev.moviedbviewer.data.network.NetworkManager;
import hu.gerlotdev.moviedbviewer.domain.usecase.GetMoviesUseCase;
import hu.gerlotdev.moviedbviewer.presentation.BaseFragment;
import hu.gerlotdev.moviedbviewer.presentation.MovieDBViewerApplication;
import hu.gerlotdev.moviedbviewer.presentation.UIThread;
import io.reactivex.observers.DisposableSingleObserver;

public class MainFragment extends BaseFragment<MainFragment.MainFragmentListener> {

    public static final String TAG = MainFragment.class.getSimpleName();

    public interface MainFragmentListener {

        void setToolbarAsSupportActionBar(Toolbar toolbar);

    }

    private static final int ERROR_OR_EMPTY_VIEW = 0;
    private static final int LOADING_VIEW = 1;
    private static final int CONTENT_VIEW = 2;

    @Inject
    GetMoviesUseCase getMoviesUseCase;

    private Toolbar toolbar;
    private TextInputLayout tilSearch;
    private TextInputEditText etSearch;
    private Button btnSearch;
    private ViewFlipper vfThreeState;
    private TextView tvError;
    private RecyclerView rvMovies;

    private MovieListAdapter rvMoviesListAdapter;
    private RecyclerView.LayoutManager rvMoviesLayoutManager;

    public MainFragment() {
        MovieDBViewerApplication.applicationComponent.inject(this);
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        tvError.setText(getResources().getString(R.string.search_for_a_keyword));
        vfThreeState.setDisplayedChild(ERROR_OR_EMPTY_VIEW);
    }

    private void initViews(View view) {
        toolbar = view.findViewById(R.id.toolbar);

        if (listener != null) {
            listener.setToolbarAsSupportActionBar(toolbar);
        }

        tilSearch = view.findViewById(R.id.tilSearch);
        etSearch = view.findViewById(R.id.etSearch);
        btnSearch = view.findViewById(R.id.btnSearch);
        vfThreeState = view.findViewById(R.id.vfThreeState);
        tvError = view.findViewById(R.id.tvError);
        rvMovies = view.findViewById(R.id.rvMovies);

        rvMovies.setHasFixedSize(true);
        rvMoviesLayoutManager = new LinearLayoutManager(getActivity());
        rvMovies.setLayoutManager(rvMoviesLayoutManager);

        rvMoviesListAdapter = new MovieListAdapter();
        rvMovies.setAdapter(rvMoviesListAdapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etSearch.getText() != null
                        && etSearch.getText().toString() != null
                        && getMoviesUseCase != null) {
                    if (etSearch.getText().toString().length() < 2) {
                        tilSearch.setError(getResources().getString(R.string.at_least_two));
                    } else {
                        tilSearch.setError(null);

                        // Hide the keyboard
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);

                        vfThreeState.setDisplayedChild(LOADING_VIEW);
                        getMoviesUseCase.setQuery(etSearch.getText().toString());
                        getMoviesUseCase.execute(new GetMoviesObserver());
                    }
                }
            }
        });
    }

    private final class GetMoviesObserver extends DisposableSingleObserver<List<Movie>> {

        @Override
        public void onSuccess(List<Movie> moviePage) {
            if (moviePage != null) {
                rvMoviesListAdapter.setMovies(moviePage);
                vfThreeState.setDisplayedChild(CONTENT_VIEW);
            }
        }

        @Override
        public void onError(Throwable e) {
            tvError.setText(e.getMessage());
            vfThreeState.setDisplayedChild(ERROR_OR_EMPTY_VIEW);
        }
    }

}
