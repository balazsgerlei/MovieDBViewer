package hu.gerlotdev.moviedbviewer.presentation.main;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import hu.gerlotdev.moviedbviewer.R;
import hu.gerlotdev.moviedbviewer.data.model.MoviePage;
import hu.gerlotdev.moviedbviewer.data.network.MovieApi;
import hu.gerlotdev.moviedbviewer.data.network.NetworkManager;
import hu.gerlotdev.moviedbviewer.domain.usecase.GetMoviesUseCase;
import hu.gerlotdev.moviedbviewer.presentation.BaseFragment;
import hu.gerlotdev.moviedbviewer.presentation.UIThread;
import io.reactivex.observers.DisposableSingleObserver;

public class MainFragment extends BaseFragment<MainFragment.MainFragmentListener> {

    public static final String TAG = MainFragment.class.getSimpleName();

    public interface MainFragmentListener {

        void setToolbarAsSupportActionBar(Toolbar toolbar);

    }

    private GetMoviesUseCase getMoviesUseCase;

    private Toolbar toolbar;
    private EditText etSearch;
    private Button btnSearch;
    private RecyclerView rvMovies;

    private MovieListAdapter rvMoviesListAdapter;
    private RecyclerView.LayoutManager rvMoviesLayoutManager;

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

    private void initViews(View view) {
        toolbar = view.findViewById(R.id.toolbar);

        if (listener != null) {
            listener.setToolbarAsSupportActionBar(toolbar);
        }

        etSearch = view.findViewById(R.id.etSearch);
        btnSearch = view.findViewById(R.id.btnSearch);
        rvMovies = view.findViewById(R.id.rvMovies);

        rvMovies.setHasFixedSize(true);
        rvMoviesLayoutManager = new LinearLayoutManager(getActivity());
        rvMovies.setLayoutManager(rvMoviesLayoutManager);

        rvMoviesListAdapter = new MovieListAdapter();
        rvMovies.setAdapter(rvMoviesListAdapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etSearch.getText() != null && etSearch.getText().toString() != null && !etSearch.getText().toString().isEmpty()) {
                    getMoviesUseCase = new GetMoviesUseCase(UIThread.getInstance(),
                            NetworkManager.getInstance(getActivity())
                                    .provideRetrofit(NetworkManager.getInstance(getActivity()).provideOkHttpClient(getActivity()))
                                    .create(MovieApi.class), etSearch.getText().toString());
                    getMoviesUseCase.execute(new GetMoviesObserver());
                }
            }
        });
    }

    private final class GetMoviesObserver extends DisposableSingleObserver<MoviePage> {

        @Override
        public void onSuccess(MoviePage moviePage) {
            if (moviePage.getResults() != null) {
                rvMoviesListAdapter.setMovies(moviePage.getResults());
            }
        }

        @Override
        public void onError(Throwable e) {
            // TODO correct error display
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
