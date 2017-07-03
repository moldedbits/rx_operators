package com.moldedbits.reactiveoperators.transformation.flatmap;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class FlatmapJava {

    public static void test() {
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(3)
                .concatMap(new Function<Long, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull Long aLong) throws Exception {
                        return Observable.interval(100, TimeUnit.MILLISECONDS).take(3)
                                .map(bLong -> aLong + " :: " + bLong);
                    }
                })
                .subscribe(str -> Log.d("ConcatMap", str));

        // The exception will be thrown after all the observables have finished
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(3)
                .concatMapDelayError(new Function<Long, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull Long aLong) throws Exception {
                        if (aLong == 1) {
                            return Observable.error(new Exception("Test exception"));
                        }
                        return Observable.interval(100, TimeUnit.MILLISECONDS).take(3)
                                .map(bLong -> aLong + " :: " + bLong);
                    }
                })
                .subscribe(
                        str -> Log.d("ConcatMapDelayError", str),
                        e -> Log.d("ConcatMapDelayError", "Exception: " + e.getMessage()));

        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(3)
                .concatMapEager(new Function<Long, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull Long aLong) throws Exception {
                        return Observable.interval(100, TimeUnit.MILLISECONDS).take(3)
                                .map(bLong -> aLong + " :: " + bLong);
                    }
                })
                .subscribe(str -> Log.d("ConcatMapEager", str));

        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(3)
                .concatMapIterable(new Function<Long, Iterable<String>>() {
                    @Override
                    public Iterable<String> apply(@NonNull Long aLong) throws Exception {
                        List<String> strs = new ArrayList<>();
                        strs.add(String.valueOf(aLong));
                        strs.add(String.valueOf(aLong));
                        return strs;
                    }
                })
                .subscribe(str -> Log.d("ConcatMapIterable", str));

        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(3)
                .flatMap(new Function<Long, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull Long aLong) throws Exception {
                        return Observable.interval(100, TimeUnit.MILLISECONDS).take(3)
                                .map(bLong -> aLong + " :: " + bLong);
                    }
                })
                .subscribe(str -> Log.d("flatMap", str));

        Observable.just(1, 2, 3)
                .take(3)
                .flatMapIterable(new Function<Integer, Iterable<String>>() {
                    @Override
                    public Iterable<String> apply(@NonNull Integer i) throws Exception {
                        List<String> strs = new ArrayList<>();
                        strs.add(String.valueOf(i));
                        strs.add(String.valueOf(i));
                        return strs;
                    }
                })
                .subscribe(str -> Log.d("FlatMapIterable", str));

        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(3)
                .switchMap(new Function<Long, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull Long aLong) throws Exception {
                        return Observable.interval(50, TimeUnit.MILLISECONDS).take(3)
                                .map(bLong -> aLong + " :: " + bLong);
                    }
                })
                .subscribe(str -> Log.d("SwitchMap", str));

        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(3)
                .switchMapDelayError(new Function<Long, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull Long aLong) throws Exception {
                        if (aLong == 1) {
                            return Observable.error(new Exception("SwitchMap exception"));
                        }
                        return Observable.interval(50, TimeUnit.MILLISECONDS).take(3)
                                .map(bLong -> aLong + " :: " + bLong);
                    }
                })
                .subscribe(str -> Log.d("SwitchMapDelayError", str),
                        e -> Log.d("SwitchMapDelayError", "Exception: " + e.getMessage()));
    }
}
