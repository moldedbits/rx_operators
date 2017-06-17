package com.moldedbits.reactiveoperators.creating.create;

import android.util.Log;

import java.util.concurrent.Callable;

import io.reactivex.Emitter;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GenerateJava {

    private static final String TAG = "GenerateSample";

    public static void test() {
        new Thread(() -> {
            Log.d(TAG, "Generating with consumer");
            generateWithConsumer();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.d(TAG, "Generating with intial state and consumer");
            generateWithInitialStateAndConsumer();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.d(TAG, "Generating with intial state and bi function");
            generateWithInitialStateAndBiFunction();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.d(TAG, "Generating with initial state and bi function and disposable state");
            generateWithInitialStateAndBiFunctionAndDisposeState();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void generateWithConsumer() {
        Observable
                .generate(new Consumer<Emitter<Polygon>>() {
                    private int sides = 3;
                    @Override
                    public void accept(@NonNull Emitter<Polygon> emitter)
                            throws Exception {
                        if (sides < 6) {
                            emitter.onNext(new Polygon(sides++));
                        } else {
                            emitter.onComplete();
                        }
                    }})
                .subscribeOn(Schedulers.io())
                .subscribe(polygon ->
                        Log.d(TAG, String.format("new polygon with %d sides", polygon.sides)));
    }

    private static void generateWithInitialStateAndConsumer() {
        Callable<Integer> initialState = () -> 3;

        BiConsumer<Integer, Emitter<Polygon>> consumer =
                new BiConsumer<Integer, Emitter<Polygon>>() {

                    private int sides = -1;

                    @Override
                    public void accept(Integer integer, Emitter<Polygon> polygonEmitter) throws Exception {
                        if (sides < 0) {
                            sides = integer;
                        }

                        if (sides < 6) {
                            polygonEmitter.onNext(new Polygon(sides++));
                        } else {
                            polygonEmitter.onComplete();
                        }
                    }
                };

        Observable.generate(initialState, consumer)
                .subscribeOn(Schedulers.io())
                .subscribe(polygon ->
                        Log.d(TAG, String.format("new polygon with %d sides", polygon.sides)));
    }

    private static void generateWithInitialStateAndBiFunction() {
        Callable<Integer> initialState = () -> 3;

        BiFunction<Integer, Emitter<Polygon>, Integer> emitter = (integer, polygonEmitter) -> {
            if (integer < 6) {
                polygonEmitter.onNext(new Polygon(integer));
            } else {
                polygonEmitter.onComplete();
            }
            return ++integer;
        };

        Observable.generate(initialState, emitter)
                .subscribeOn(Schedulers.io())
                .subscribe(polygon ->
                        Log.d(TAG, String.format("new polygon with %d sides", polygon.sides)));
    }

    private static void generateWithInitialStateAndBiFunctionAndDisposeState() {
        Callable<Integer> initialState = () -> 3;

        BiFunction<Integer, Emitter<Polygon>, Integer> emitter = (integer, polygonEmitter) -> {
            if (integer < 6) {
                polygonEmitter.onNext(new Polygon(integer++));
            } else {
                polygonEmitter.onComplete();
            }
            return integer;
        };

        Consumer<Integer> disposableState = integer ->
                Log.d(TAG, String.format("Disposing with sides %d", integer));

        Observable.generate(initialState, emitter, disposableState)
                .subscribeOn(Schedulers.io())
                .subscribe(polygon ->
                        Log.d(TAG, String.format("new polygon with %d sides", polygon.sides)));
    }

    private static class Polygon {
        int sides;

        Polygon(int sides) {
            this.sides = sides;
        }
    }
}
