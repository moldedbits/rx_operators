package com.moldedbits.reactiveoperators.creating.defer;

import android.util.Log;

import io.reactivex.Observable;

public class DeferJava {

    private static final String TAG = "DeferJava";

    public static void deferSample() {
        Polygon polygon = new Polygon(3);
        Observable<Integer> createObservable = polygon.createObservable();
        Observable<Integer> deferObservable = polygon.deferObservable();
        polygon.sides = 4;
        createObservable.subscribe(i -> Log.d(TAG, String.format("with create, sides are %d", i)));
        deferObservable.subscribe(i -> Log.d(TAG, String.format("with defer, sides are %d", i)));
    }

    private static class Polygon {
        int sides;

        Polygon(int sides) {
            this.sides = sides;
        }

        Observable<Integer> createObservable() {
            return Observable.just(sides);
        }

        Observable<Integer> deferObservable() {
            return Observable.defer(() -> Observable.just(sides));
        }
    }
}
