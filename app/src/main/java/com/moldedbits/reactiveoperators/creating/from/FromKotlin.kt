package com.moldedbits.reactiveoperators.creating.from

import android.util.Log
import io.reactivex.rxkotlin.toObservable

object FromKotlin {

    private val TAG: String = "FromKotlin"

    fun test() {
        listOf("this", "is", "a", "string").toObservable()
                .subscribe { Log.d(TAG, "got string from list: $it") }
    }
}