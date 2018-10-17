package hu.gerlotdev.moviedbviewer.presentation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import hu.gerlotdev.moviedbviewer.R;


public class SingleFragmentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
    }

    protected void attachFragment(Fragment fragment, String tag) {
        attachFragment(fragment, tag, false);
    }

    protected void attachFragment(Fragment fragment, String tag, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.replace(R.id.fragment_container, fragment, tag);
        fragmentTransaction.commit();
    }

    protected void detachFragment(String tag) {
        detachFragment(tag, false);
    }

    protected void detachFragment(String tag, boolean removeFromBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = findFragmentByTag(tag);
        if (fragment != null) {
            if (removeFromBackStack) {
                fragmentManager.popBackStackImmediate();
            } else {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(fragmentManager.findFragmentByTag(tag));
                fragmentTransaction.commit();
            }
        }
    }

    protected Fragment findFragmentByTag(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

}
