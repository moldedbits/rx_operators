package com.moldedbits.reactiveoperators.creating.timer;

import android.os.Process;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by anuj on 23/06/17.
 * http://reactivex.io/documentation/operators/timer.html
 *
 * The Timer operator creates an Observable that emits one particular item after a span of time
 * that you specify.
 */

public class TimerJava {

    private static final String TAG = "TimerJava";

    public static void test() {
        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .subscribe(aLong -> Log.d(TAG, String.format(
                        "Received from Default Timer value %d on thread %d",
                        aLong, Process.myTid())));

        Observable.timer(1000, TimeUnit.MILLISECONDS, Schedulers.io())
                .subscribe(aLong -> Log.d(TAG, String.format(
                        "Received from Timer with scheduler value %d on thread %d",
                        aLong, Process.myTid())));
    }
}
