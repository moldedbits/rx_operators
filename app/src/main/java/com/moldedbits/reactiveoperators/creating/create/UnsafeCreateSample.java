package com.moldedbits.reactiveoperators.creating.create;

import android.util.Log;

import com.moldedbits.reactiveoperators.model.Polygon;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class UnsafeCreateSample {

    private static final String TAG = "UnsafeCreateSample";

    public static void test() {
        Observable.unsafeCreate(
                (ObservableSource<Polygon>) observer ->
                        Observable.just(new Polygon(3)).subscribe(observer))
                .subscribe(polygon ->
                        Log.d(TAG, String.format("New polygon with sides %d", polygon.getSides())));
    }
}
