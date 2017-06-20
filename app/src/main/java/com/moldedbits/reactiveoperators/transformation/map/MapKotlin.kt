package com.moldedbits.reactiveoperators.transformation.map

import android.util.Log
import io.reactivex.rxkotlin.toObservable

/**
 * Created by abhishek on 20/06/17.
 * http://reactivex.io/documentation/operators/timestamp.html

 * The Map operator applies a function of your choosing to each item emitted by the source Observable,
 * and returns an Observable that emits the results of these function applications.
 */

object MapKotlin {
    private val TAG = "MapKotlin"

    fun test() {
        listOf(1, 2, 3, 4, 5, 6, 7)
                .toObservable()
                // map will transform each element
                // multiply each element by 10
                .map { x -> x!! * 10 }
                .subscribe { x -> Log.i(TAG, "New Value: " + x!!) }
    }
}