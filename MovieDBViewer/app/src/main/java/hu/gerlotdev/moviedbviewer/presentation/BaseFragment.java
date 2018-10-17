package hu.gerlotdev.moviedbviewer.presentation;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class BaseFragment<L> extends Fragment {

    protected L listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (L) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}
