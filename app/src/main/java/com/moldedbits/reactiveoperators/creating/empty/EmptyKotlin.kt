package com.moldedbits.reactiveoperators.creating.empty

import android.util.Log
import io.reactivex.Observable

object EmptyKotlin {

    private val TAG_EMPTY = "EmptyKotlin"
    private val TAG_NEVER = "NeverKotlin"
    private val TAG_ERROR = "ErrorKotlin"

    fun test() {
        testEmpty()
        testError()
        testNever()
    }

    private fun testEmpty() {
        Observable.empty<Any>().subscribe(
                { Log.d(TAG_EMPTY, "onNext") },
                { Log.d(TAG_EMPTY, "onError") }
        ) { Log.d(TAG_EMPTY, "onComplete") }
    }

    private fun testNever() {
        Observable.never<Any>().subscribe(
                { Log.d(TAG_NEVER, "onNext") },
                { Log.d(TAG_NEVER, "onError") }
        ) { Log.d(TAG_NEVER, "onComplete") }
    }

    private fun testError() {
        Observable.error<Any>(Exception("Sample exception")).subscribe(
                { Log.d(TAG_ERROR, "onNext") },
                { Log.d(TAG_ERROR, "onError") }
        ) { Log.d(TAG_ERROR, "onComplete") }
    }
}