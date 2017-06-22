package com.moldedbits.reactiveoperators.creating.interval;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by anuj on 22/06/17.
 * http://reactivex.io/documentation/operators/interval.html
 * The Interval operator returns an Observable that emits an infinite sequence of ascending
 * integers, with a constant interval of time of your choosing between emissions.
 */

public class IntervalJava {

    private static final String TAG = "IntervalJava";

    public static void test() {
        Observable.interval(100, 1000, TimeUnit.MILLISECONDS, Schedulers.io())
                .take(5)
                .subscribe(aLong ->
                        Log.d(TAG, String.format("Found %d at %d",
                                aLong, System.currentTimeMillis())));
    }
}
