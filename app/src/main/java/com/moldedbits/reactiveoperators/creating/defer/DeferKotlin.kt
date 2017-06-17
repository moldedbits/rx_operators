package com.moldedbits.reactiveoperators.creating.defer

import android.util.Log
import io.reactivex.Observable

object DeferKotlin {

    val TAG = "DeferKotlin"

    fun deferSample() {
        val polygon: Polygon = Polygon(3)
        val createObservable = polygon.createObservable()
        val deferObservable = polygon.deferObservable()
        polygon.sides = 4

        createObservable.subscribe { Log.d(TAG, "with createObservable sides are $it") }
        deferObservable.subscribe { Log.d(TAG, "with createObservable sides are $it") }
    }

    class Polygon(var sides: Int) {

        fun createObservable(): Observable<Int> {
            return Observable.just(sides)
        }

        fun deferObservable(): Observable<Int> {
            return Observable.defer { Observable.just(sides) }
        }
    }
}
