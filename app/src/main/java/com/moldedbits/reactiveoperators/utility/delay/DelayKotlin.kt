package com.moldedbits.reactiveoperators.utility.delay

import android.util.Log
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by abhishek on 19/06/17.
 *
 * The Delay operator modifies its source Observable by pausing for a particular increment of
 * time (that you specify) before emitting each of the source Observable’s items. This has the
 * effect of shifting the entire sequence of items emitted by the Observable forward in time by
 * that specified increment.
 */


object DelayKotlin {
    private val TAG = "DelayKotlin"

    fun test() {
        val y = 1
        Observable.just(y)
                .map { x ->
                    Log.i(TAG, "delay Current Time: " + System.currentTimeMillis())
                    x!! + 2
                }
                // delays emission of previous observable by specified amount of time
                .delay(100, TimeUnit.MILLISECONDS)
                .subscribe { x -> Log.i(TAG, "delay After Delay: " + System.currentTimeMillis() + " Final X: " + x) }

        val abc = Observable.just(y)
                .map { x ->
                    Log.i(TAG, "delaySubscription Current Time: " + System.currentTimeMillis())
                    x!! + 2
                }

        abc.subscribe { x ->
            Log.i(TAG, "delaySubscription Without Delay: " + System.currentTimeMillis()
                    + " Final X: " + x)
        }

        // delay the subscription to the source Observable
        // note that whole operations are shifted in time including above map operator
        abc.delaySubscription(100, TimeUnit.MILLISECONDS)
                .subscribe { x ->
                    Log.i(TAG, "delaySubscription After Delay: " + System.currentTimeMillis()
                            + " Final X: " + x)
                }
    }
}
