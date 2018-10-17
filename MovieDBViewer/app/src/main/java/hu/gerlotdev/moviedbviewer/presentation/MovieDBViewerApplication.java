package hu.gerlotdev.moviedbviewer.presentation;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class MovieDBViewerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
    }

}
