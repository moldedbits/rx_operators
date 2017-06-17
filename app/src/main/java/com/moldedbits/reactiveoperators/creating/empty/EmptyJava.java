package com.moldedbits.reactiveoperators.creating.empty;

import android.util.Log;

import io.reactivex.Observable;

public class EmptyJava {

    private static final String TAG_EMPTY = "EmptyJava";
    private static final String TAG_NEVER = "NeverJava";
    private static final String TAG_ERROR = "ErrorJava";

    public static void test() {
        testEmpty();
        testError();
        testNever();
    }

    private static void testEmpty() {
        Observable.empty().subscribe(
                o -> Log.d(TAG_EMPTY, "onNext"),
                o -> Log.d(TAG_EMPTY, "onError"),
                () -> Log.d(TAG_EMPTY, "onComplete"));
    }

    private static void testNever() {
        Observable.never().subscribe(
                o -> Log.d(TAG_NEVER, "onNext"),
                o -> Log.d(TAG_NEVER, "onError"),
                () -> Log.d(TAG_NEVER, "onComplete"));
    }

    private static void testError() {
        Observable.error(new Exception("Sample exception")).subscribe(
                o -> Log.d(TAG_ERROR, "onNext"),
                o -> Log.d(TAG_ERROR, "onError"),
                () -> Log.d(TAG_ERROR, "onComplete"));
    }
}
