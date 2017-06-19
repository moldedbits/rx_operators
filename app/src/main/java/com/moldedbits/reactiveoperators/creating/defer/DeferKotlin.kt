package com.moldedbits.reactiveoperators.creating.defer

import android.util.Log
import com.moldedbits.reactiveoperators.model.Polygon

object DeferKotlin {

    val TAG = "DeferKotlin"

    fun test() {
        val polygon: Polygon = Polygon(3)
        val createObservable = polygon.createObservable()
        val deferObservable = polygon.deferObservable()
        polygon.sides = 4

        createObservable.subscribe { Log.d(TAG, "with createObservable sides are $it") }
        deferObservable.subscribe { Log.d(TAG, "with createObservable sides are $it") }
    }
}
