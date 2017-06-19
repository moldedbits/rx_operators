package com.moldedbits.reactiveoperators.creating.empty

import android.util.Log
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toMaybe
import io.reactivex.rxkotlin.toSingle

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
        Observable.never<Any>().subscribeBy(
                onNext = { Log.d(TAG_NEVER, "onNext") },
                onError = { Log.d(TAG_NEVER, "onError") },
                onComplete = { Log.d(TAG_NEVER, "onComplete") }
        )
    }

    private fun testError() {
        // NOTE:: In the first example, the onError will be called, whereas for the remaining two,
        // the onNext will be called

        Observable.error<Any>(Exception("Sample exception")).subscribeBy(
                onNext = { Log.d(TAG_ERROR, "onNext") },
                onError = { Log.d(TAG_ERROR, "onError") }, // will be called
                onComplete = { Log.d(TAG_ERROR, "onComplete") }
        )

        Exception("Sample exception").toSingle().subscribeBy(
                onSuccess = { Log.d(TAG_ERROR, "onNext") }, // will be called
                onError = { Log.d(TAG_ERROR, "onError") }
        )

        Exception("Sample exception").toMaybe().subscribeBy(
                onSuccess = { Log.d(TAG_ERROR, "onNext") }, // will be called
                onError = { Log.d(TAG_ERROR, "onError") }
        )
    }
}