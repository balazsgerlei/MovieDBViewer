package hu.gerlotdev.moviedbviewer.data.entity;

public class Movie implements Entity {

    private long id;

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }
}
