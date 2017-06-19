package com.moldedbits.reactiveoperators.utility.timestamp;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Created by abhishek
 * on 19/06/17.
 */

public class TimestampJava {
    private static final String TAG = "TimestampJava";

    public static void test() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7};
        Observable.fromArray(arr)
                .flatMap(x -> Observable.timer(50 * x, TimeUnit.MILLISECONDS)
                            .map(y -> x))
                .timestamp()
                .subscribe(timedIntegers ->
                        Log.i(TAG, "Timed String: "
                                + timedIntegers.value()
                                + " "
                                + timedIntegers.time()));
    }
}
