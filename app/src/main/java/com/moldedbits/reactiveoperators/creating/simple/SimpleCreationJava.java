package com.moldedbits.reactiveoperators.creating.simple;

import android.util.Log;

import io.reactivex.Observable;

/**
 * Created by anuj on 22/6/2017
 *
 * The Just operator converts an item into an Observable that emits that item.
 *
 * The Range operator emits a range of sequential integers, in order, where you select the start of
 * the range and its length.
 *
 * The Repeat operator emits an item repeatedly. Some implementations of this operator allow you to
 * repeat a sequence of items, and some permit you to limit the number of repetitions.
 */
public class SimpleCreationJava {

    private static final String TAG = "SimpleCreationJava";

    public static void test() {
        // Output
        // Received 1 from repeat
        // Received 2 from repeat
        Observable.just(1, 2)
                .subscribe(i -> Log.d(TAG, String.format("Received %d from just", i)));

        // Output
        // Received 1 from range
        // ...
        // Received 5 from range
        Observable.range(1, 5)
                .subscribe(i -> Log.d(TAG, String.format("Received %d from range", i)));

        // Output
        // Received 1 from repeat
        // Received 2 from repeat
        // Received 1 from repeat
        // Received 2 from repeat
        Observable.just(1, 2).repeat(2)
                .subscribe(i -> Log.d(TAG, String.format("Received %d from repeat", i)));
    }
}
