package hu.gerlotdev.moviedbviewer.presentation;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MovieDBViewerApplication extends Application {

    public static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)	.build();
        ImageLoader.getInstance().init(config);

        applicationComponent = DaggerApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .build();

    }

}
