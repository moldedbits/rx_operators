package com.moldedbits.reactiveoperators.creating.defer;

import android.util.Log;

import io.reactivex.Observable;

public class DeferSample {

    private static final String TAG = "DeferSample";

    public static void deferSample() {
        Polygon polygon = new Polygon(3);
        Observable<Integer> createObservable = polygon.createObservable();
        Observable<Integer> deferObservable = polygon.deferObservable();
        polygon.sides = 4;
        createObservable.subscribe(i -> Log.d(TAG, String.format("with create, sides are %d", i)));
        deferObservable.subscribe(i -> Log.d(TAG, String.format("with defer, sides are %d", i)));
    }

    public static class Polygon {
        int sides;

        public Polygon(int sides) {
            this.sides = sides;
        }

        public Observable<Integer> createObservable() {
            return Observable.just(sides);
        }

        public Observable<Integer> deferObservable() {
            return Observable.defer(() -> Observable.just(sides));
        }
    }
}
