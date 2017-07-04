package com.moldedbits.reactiveoperators.transformation.groupby;

import android.util.Log;

import io.reactivex.Observable;

/**
 * Created by anuj on 23/06/17.
 * http://reactivex.io/documentation/operators/groupby.html
 *
 * The GroupBy operator divides an Observable that emits items into an Observable that emits
 * Observables, each one of which emits some subset of the items from the original source
 * Observable. Which items end up on which Observable is typically decided by a discriminating
 * function that evaluates each item and assigns it a key. All items with the same key are emitted
 * by the same Observable.
 */
public class GroupByJava {

    private static final String TAG = "GroupByJava";

    public static void test() {

        // Group all even items and all even items
        Observable.range(1, 10)
                // The key is a Boolean, True for even and False for odd items
                .groupBy(integer -> (integer % 2) == 0)
                .subscribe(observable -> {
                    if (observable.getKey()) {
                        Log.d(TAG, "Subscribing to new observable for True");
                        observable.subscribe(i -> Log.d(TAG, "True: " + i));
                    } else {
                        Log.d(TAG, "Subscribing to new observable for False");
                        observable.subscribe(i -> Log.d(TAG, "False: " + i));
                    }
                });
    }
}
