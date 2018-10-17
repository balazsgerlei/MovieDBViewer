package hu.gerlotdev.moviedbviewer.domain.usecase;

import hu.gerlotdev.moviedbviewer.domain.executor.PostExecutionThread;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCase<T> {

    private final PostExecutionThread postExecutionThread;

    private CompositeDisposable disposables;

    public UseCase(PostExecutionThread postExecutionThread) {
        this.postExecutionThread = postExecutionThread;
        this.disposables = new CompositeDisposable();
    }

    protected abstract Single<T> buildUseCaseObservable();

    public void execute(DisposableSingleObserver<T> useCaseObserver) {
        disposables.add(this.buildUseCaseObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.getScheduler())
                .subscribeWith(useCaseObserver));
    }

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

}
