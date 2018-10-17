package hu.gerlotdev.moviedbviewer.presentation.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.gerlotdev.moviedbviewer.R;
import hu.gerlotdev.moviedbviewer.data.model.Movie;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieItemViewHolder> {

    private List<Movie> movies = new ArrayList<>();

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_movie, viewGroup, false);
        MovieItemViewHolder viewHolder = new MovieItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieItemViewHolder movieItemViewHolder, int i) {
        Movie movie = movies.get(i);
        movieItemViewHolder.setTitle(movie.getTitle());
        movieItemViewHolder.setBudget(movie.getBudget());
        movieItemViewHolder.setRating(movie.getVoteAverage());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivMovieImage;
        private TextView tvTitle;
        private TextView tvBudget;
        private TextView tvRating;

        public void setMovieImage() {
            // TODO
        }

        public void setTitle(String title) {
            tvTitle.setText(title);
        }

        public void setBudget(int budget) {
            tvBudget.setText(Integer.toString(budget));
        }

        public void setRating(double rating) {
            tvRating.setText(Double.toString(rating));
        }


        public MovieItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMovieImage = itemView.findViewById(R.id.ivMovieImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBudget = itemView.findViewById(R.id.tvBudget);
            tvRating = itemView.findViewById(R.id.tvRating);
        }

    }

}
