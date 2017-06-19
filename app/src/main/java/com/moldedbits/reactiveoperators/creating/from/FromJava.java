package com.moldedbits.reactiveoperators.creating.from;

import android.util.Log;

import org.reactivestreams.Publisher;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.reactivex.Observable;

public class FromJava {

    private static final String TAG = "FromJava";

    public static void test() {
        String[] arr = new String[] {"this", "is", "an", "array"};
        Observable.fromArray(arr).subscribe(str ->
                Log.d(TAG, String.format("Got string from array: %s", str)));

        Observable.fromCallable(new FactorialCalculator(10))
                .subscribe(aLong -> Log.d(TAG, "Got result from callable: " + aLong));

        final ExecutorService threadpool = Executors.newFixedThreadPool(3);
        FactorialCalculator task = new FactorialCalculator(10);
        Future<Long> future = threadpool.submit(task);
        Observable.fromFuture(future).subscribe(l -> Log.d(TAG, "Got result from future: " + l));

        List<String> list = Arrays.asList(arr);
        Observable.fromIterable(list).subscribe(str ->
                Log.d(TAG, String.format("Got string from iterable: %s", str)));

        Publisher<String> publisher = s -> {
            for (int i=0; i<4; i++) {
                s.onNext("String " + i);
            }
            s.onComplete();
        };

        Observable.fromPublisher(publisher).subscribe(str ->
                Log.d(TAG, String.format("Got string from publisher: %s", str)));
    }

    private static class FactorialCalculator implements Callable<Long> {

        private final int number;

        FactorialCalculator(int number) {
            this.number = number;
        }

        @Override
        public Long call() {
            long output = 0;
            try {
                output =  factorial(number);
            } catch (InterruptedException ex) {
                Log.d(TAG, null, ex);
            }

            return output;
        }

        private long factorial(int number) throws InterruptedException {
            if (number < 0) {
                throw new IllegalArgumentException("Number must be greater than zero");
            }
            long result = 1;
            while (number > 0) {
                Thread.sleep(1); // adding delay for example
                result = result * number;
                number--;
            }
            return result;
        }
    }

}
