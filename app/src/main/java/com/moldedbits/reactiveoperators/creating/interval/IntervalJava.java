package com.moldedbits.reactiveoperators.creating.interval;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

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
