package hu.gerlotdev.moviedbviewer.presentation.main;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.gerlotdev.moviedbviewer.R;
import hu.gerlotdev.moviedbviewer.presentation.BaseFragment;

public class MainFragment extends BaseFragment<MainFragment.MainFragmentListener> {

    public static final String TAG = MainFragment.class.getSimpleName();

    public interface MainFragmentListener {

        void setToolbarAsSupportActionBar(Toolbar toolbar);

    }

    private Toolbar toolbar;

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
    }

}
