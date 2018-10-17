package hu.gerlotdev.moviedbviewer.presentation.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import hu.gerlotdev.moviedbviewer.presentation.SingleFragmentActivity;

public class MainActivity extends SingleFragmentActivity implements MainFragment.MainFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            attachFragment(MainFragment.newInstance(), MainFragment.TAG);
        }
    }

    @Override
    public void setToolbarAsSupportActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }

}
