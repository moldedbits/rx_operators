package com.moldedbits.reactiveoperators.creating.create;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class UnsafeCreateSample {

    private static final String TAG = "UnsafeCreateSample";

    public static void unsafeCreate() {
        Observable.unsafeCreate(
                (ObservableSource<Polygon>) observer ->
                        Observable.just(new Polygon(3)).subscribe(observer))
                .subscribe(polygon ->
                        Log.d(TAG, String.format("New polygon with sides %d", polygon.sides)));
    }

    private static class Polygon {
        int sides;

        Polygon(int sides) {
            this.sides = sides;
        }
    }
}
