package com.moldedbits.reactiveoperators.transformation.buffer;

import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

// Some great examples here
// https://github.com/Froussios/Intro-To-RxJava/blob/master/Part%203%20-%20Taming%20the%20sequence/
// 5.%20Time-shifted%20sequences.md
public class BufferJava {

    public static void test() {
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(10)
                .buffer(3)
                .subscribe(list -> printList("BufferSimple", list));

        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(10)
                .buffer(Observable.interval(250, TimeUnit.MILLISECONDS))
                .subscribe(list -> printList("BufferTime", list));

        Observable.interval(100, TimeUnit.MILLISECONDS).take(10)
                .buffer(250, TimeUnit.MILLISECONDS)
                .subscribe(list -> printList("BufferBoundary", list));

        Observable.interval(100, TimeUnit.MILLISECONDS).take(10)
                .buffer(250, TimeUnit.MILLISECONDS)
                .subscribe(list -> printList("BufferBoundary", list));

        Observable.interval(100, TimeUnit.MILLISECONDS).take(10)
                .buffer(
                        Observable.interval(250, TimeUnit.MILLISECONDS),
                        i -> Observable.timer(200, TimeUnit.MILLISECONDS))
                .subscribe(list -> printList("BufferBoundary2", list));
    }

    private static void printList(String tag, List<Long> list) {
        StringBuilder builder = new StringBuilder("Items: ");
        list.forEach(integer -> builder.append(integer).append(" "));
        Log.d(tag, builder.toString());
    }
}
