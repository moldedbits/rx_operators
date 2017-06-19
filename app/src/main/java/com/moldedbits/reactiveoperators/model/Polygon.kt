package com.moldedbits.reactiveoperators.model

import io.reactivex.Observable

class Polygon(var sides: Int) {

    fun createObservable(): Observable<Int> {
        return Observable.just(sides)
    }

    fun deferObservable(): Observable<Int> {
        return Observable.defer { Observable.just(sides) }
    }
}
