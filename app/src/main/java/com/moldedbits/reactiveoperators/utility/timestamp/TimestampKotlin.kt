package com.moldedbits.reactiveoperators.utility.timestamp

import android.util.Log
import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import java.util.concurrent.TimeUnit

/**
 * Created by abhishek
 * on 19/06/17.
 */

object TimestampKotlin {

    private val TAG: String = "TimestampKotlin"

    fun test() {
        listOf(1, 2, 3, 4, 5, 6, 7)
                .toObservable()
                .flatMap { x -> Observable.timer(50L * x, TimeUnit.MILLISECONDS).map { _ -> x } }
                .timestamp()
                .subscribe { Log.i(TAG, "Timed Integer " + it.value() + "Timestamp: " + it.time()) }
    }
}