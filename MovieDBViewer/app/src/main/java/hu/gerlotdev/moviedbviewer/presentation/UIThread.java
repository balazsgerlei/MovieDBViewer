package hu.gerlotdev.moviedbviewer.presentation;

import hu.gerlotdev.moviedbviewer.domain.executor.PostExecutionThread;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class UIThread implements PostExecutionThread {

	private static UIThread instance = new UIThread();

	public static UIThread getInstance() {
		return instance;
	}

	private UIThread() {
	}

	@Override
	public Scheduler getScheduler() {
		return AndroidSchedulers.mainThread();
	}

}
