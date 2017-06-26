package com.moldedbits.reactiveoperators.utility.dooperator;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by abhishek on 19/06/17.
 * http://reactivex.io/documentation/operators/do.html
 * register an action to take upon a variety of Observable lifecycle events
 *
 * You can register callbacks that ReactiveX will call when certain events take place on an
 * Observable, where those callbacks will be called independently from the normal set of
 * notifications associated with an Observable cascade.
 */

public class DoJava {
    private static final String TAG = "DoJava";

    public static void test() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        CompositeDisposable compositeDisposable = new CompositeDisposable();

        Observable<Integer> observable = Observable.fromArray(arr);
        observable = observable
                .doOnEach(integerNotification
                -> Log.i(TAG, "From doOnEach: " + integerNotification.getValue()))
                .map(x -> x * 10)
                // same as doOnEach accept it just accepts value than notification
                .doOnNext(integer -> Log.i(TAG, "From doOnNext: " + integer))
                // operator registers an consumer which will be called whenever an observer
                // subscribes to the resulting Observable.
                .doOnSubscribe(disposable -> Log.i(TAG, "doOnSubscribe should get called once"))
                .doOnError(throwable -> Log.e(TAG, "doOnError should get called on every error"))
                .doOnComplete(() -> Log.i(TAG, "doOnComplete should get called on completion of stream"))
                // gets called when an ongoing stream is disposed
                // does not get called if stream is disposed after terminal event
                .doOnDispose(() -> Log.i(TAG, "doOnDispose should get called upon disposed"))
                // gets called when stream terminates either by error or completion
                .doOnTerminate(() -> Log.i(TAG, "doOnTerminate should get called upon termination"));

        Disposable disposableSubscription
                = observable.subscribe(integer -> Log.i(TAG, "onNext X: " + integer),
                throwable -> Log.e(TAG, "onError: " + throwable.getMessage()),
                () -> Log.i(TAG, "onComplete"),
                disposable -> Log.i(TAG, "onSubscribe"));

        Log.i(TAG, "Disposing observable now");

        compositeDisposable.add(disposableSubscription);
        compositeDisposable.dispose();
    }
}
