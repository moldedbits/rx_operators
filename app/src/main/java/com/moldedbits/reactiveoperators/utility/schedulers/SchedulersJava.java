package com.moldedbits.reactiveoperators.utility.schedulers;

import android.os.Process;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by abhishek on 19/06/17.
 * http://reactivex.io/documentation/operators/observeon.html
 * http://reactivex.io/documentation/operators/subscribeon.html
 * <p>
 * the SubscribeOn operator designates which thread the Observable will begin operating on,
 * no matter at what point in the chain of operators that operator is called. ObserveOn, on the
 * other hand, affects the thread that the Observable will use below where that operator appears.
 * For this reason, you may call ObserveOn multiple times at various points during the chain of
 * Observable operators in order to change on which threads certain of those operators operate.
 */

public class SchedulersJava {
    private static final String TAG = "SchedulersJava";

    public static void test() {
        Integer y = 1;
        Observable.just(y)
                .map(x -> {
                    Log.i(TAG, "SubscribeOn Thread Id: " + Process.myTid());
                    return x + 2;
                })
                .map(x -> {
                    Log.i(TAG, "SubscribeOn Thread Id: " + Process.myTid());
                    return x * 3;
                })
                .observeOn(Schedulers.newThread())
                .map(x -> {
                    Log.i(TAG, "New Thread Id: " + Process.myTid());
                    return x + 1;
                })
                .observeOn(Schedulers.computation())
                .map(x -> {
                    Log.i(TAG, "Computation Thread Id: " + Process.myTid());
                    return x * 10;
                })
                .observeOn(Schedulers.newThread())
                .map(x -> {
                    Log.i(TAG, "Yet another Thread Id: " + Process.myTid());
                    return x * 5;
                })
                .observeOn(Schedulers.computation())
                .map(x -> {
                    Log.i(TAG, "Another Computation Thread Id: " + Process.myTid());
                    return x * 10;
                })
                // no matter where we call below statement stream origin will always be
                // determined with this. Only first call to this method is considered, rest calls
                // will be ignored
                .subscribeOn(Schedulers.io())
                .subscribe(x ->
                        Log.i(TAG, "Thread Id:" + Process.myTid() + " Final X: "
                                + x + " Should be similar to last ObserveOn Thread"));
    }
}
