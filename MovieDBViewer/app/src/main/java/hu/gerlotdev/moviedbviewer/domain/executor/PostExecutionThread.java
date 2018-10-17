package hu.gerlotdev.moviedbviewer.domain.executor;

import io.reactivex.Scheduler;

public interface PostExecutionThread {

	Scheduler getScheduler();

}
