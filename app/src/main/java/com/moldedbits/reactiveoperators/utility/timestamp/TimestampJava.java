package com.moldedbits.reactiveoperators.utility.timestamp;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Created by abhishek on 19/06/17.
 * http://reactivex.io/documentation/operators/timestamp.html
 * The Timestamp operator attaches a timestamp to each item emitted by the source Observable before
 * reemitting that item in its own sequence. The timestamp indicates at what time the item was emitted.
 *
 * This sample creates a stream of elements from an array. Then applies a variable delay to
 * emission of each of them using Timer operator. Finally it adds timestamp to each element and
 * finally prints timestamp along with value.
 */

public class TimestampJava {
    private static final String TAG = "TimestampJava";

    public static void test() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7};
        Observable.fromArray(arr)
                // timer returns a 0 value, note the nested map operator usage
                // in order to preserve original integers
                .flatMap(x -> Observable.timer(50 * x, TimeUnit.MILLISECONDS)
                            .map(y -> x))
                .timestamp()
                .subscribe(timedIntegers ->
                        Log.i(TAG, "Timed Integer: "
                                + timedIntegers.value()
                                + " "
                                + timedIntegers.time()));
    }
}
