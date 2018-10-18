package hu.gerlotdev.moviedbviewer.data.network;

import android.content.Context;

public class NetworkManager {

    public static final String API_URL = "https://api.themoviedb.org/3/";
    public static final String IMAGE_API_URL = "https://image.tmdb.org/t/p/w185";
    public static final String API_KEY = "43a7ea280d085bd0376e108680615c7f";

    private Context context;

    private static NetworkManager instance;

    public static NetworkManager getInstance(Context context) {
        if (instance == null) {
            instance = new NetworkManager(context);
        }
        return instance;
    }

    private NetworkManager(Context context){
        this.context = context;
    }

}
