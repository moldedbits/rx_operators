package com.moldedbits.reactiveoperators.utility.dooperator

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.toObservable


/**
 * Created by abhishek on 03/07/17.
 * http://reactivex.io/documentation/operators/do.html
 * register an action to take upon a variety of Observable lifecycle events

 * You can register callbacks that ReactiveX will call when certain events take place on an
 * Observable, where those callbacks will be called independently from the normal set of
 * notifications associated with an Observable cascade.
 */

object DoKotlin {
    private val TAG = "DoKotlin"

    fun test() {
        val compositeDisposable = CompositeDisposable()

        var observable = listOf(1, 2, 3, 4, 5, 6, 7, 8).toObservable()
        observable = observable
                .doOnEach { integerNotification -> Log.i(TAG, "From doOnEach: " + integerNotification.value) }
                .map { x -> x * 10 }
                // same as doOnEach accept it just accepts value than notification
                .doOnNext { integer -> Log.i(TAG, "From doOnNext: " + integer) }
                // operator registers an consumer which will be called whenever an observer
                // subscribes to the resulting Observable.
                .doOnSubscribe { Log.i(TAG, "doOnSubscribe should get called once") }
                .doOnError { Log.e(TAG, "doOnError should get called on every error") }
                .doOnComplete { Log.i(TAG, "doOnComplete should get called on completion of stream") }
                // gets called when an ongoing stream is disposed
                // does not get called if stream is disposed after terminal event
                .doOnDispose { Log.i(TAG, "doOnDispose should get called upon disposed") }
                // gets called when stream terminates either by error or completion
                .doOnTerminate { Log.i(TAG, "doOnTerminate should get called upon termination") }

        val disposableSubscription = observable
                .subscribe({ integer -> Log.i(TAG, "onNext X: " + integer) },
                            { throwable -> Log.e(TAG, "onError: " + throwable.message) },
                            { Log.i(TAG, "onComplete") },
                            { Log.i(TAG, "onSubscribe") })

        Log.i(TAG, "Disposing observable now")

        compositeDisposable.add(disposableSubscription)
        compositeDisposable.dispose()
    }
}