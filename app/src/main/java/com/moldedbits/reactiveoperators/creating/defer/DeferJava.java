package com.moldedbits.reactiveoperators.creating.defer;

import android.util.Log;

import com.moldedbits.reactiveoperators.model.Polygon;

import io.reactivex.Observable;

public class DeferJava {

    private static final String TAG = "DeferJava";

    public static void test() {
        Polygon polygon = new Polygon(3);
        Observable<Integer> createObservable = polygon.createObservable();
        Observable<Integer> deferObservable = polygon.deferObservable();
        polygon.setSides(4);
        createObservable.subscribe(i -> Log.d(TAG, String.format("with create, sides are %d", i)));
        deferObservable.subscribe(i -> Log.d(TAG, String.format("with defer, sides are %d", i)));
    }
}
