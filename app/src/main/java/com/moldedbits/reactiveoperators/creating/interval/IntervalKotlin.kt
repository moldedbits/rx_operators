package com.moldedbits.reactiveoperators.creating.interval

import android.util.Log
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

object IntervalKotlin {

    val TAG = "IntervalKotlin"

    fun test() {
        Observable.interval(100, 1000, TimeUnit.MILLISECONDS, Schedulers.io())
                .take(5)
                .subscribe { aLong ->
                    Log.d(TAG, String.format("Found %d at %d",
                            aLong, System.currentTimeMillis()))
                }
    }
}