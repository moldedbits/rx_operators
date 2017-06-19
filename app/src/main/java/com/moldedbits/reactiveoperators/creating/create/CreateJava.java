package com.moldedbits.reactiveoperators.creating.create;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class CreateJava {

    private static final String TAG = "RxSamples";

    public static void test(View view, TextView output) {

        final ClickProvider clickProvider = new ClickProvider(view);

        Observable<View> observable = Observable.create(emitter -> {

            Callback callback = new Callback() {
                @Override
                public void onEvent(View view) {
                    emitter.onNext(view);
                }

                @Override
                public void onFailure(Exception e) {
                    emitter.onError(e);
                }
            };

            AutoCloseable c = clickProvider.listen(callback);

            emitter.setCancellable(c::close);
        });

        observable.subscribe(new Observer<View>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(@NonNull View o) {
                output.setText(String.format("%s\n%s", output.getText(), "Click detected from JAVA"));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError: ", e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });
    }

    static class ClickProvider implements AutoCloseable {

        private View view;

        private View.OnClickListener onClickListener;

        ClickProvider(View view) {
            this.view = view;
        }

        ClickProvider listen(Callback callback) {
            onClickListener = callback::onEvent;
            view.setOnClickListener(onClickListener);
            return this;
        }

        @Override
        public void close() throws Exception {
            view.setOnClickListener(null);
        }
    }

    interface Callback {
        void onEvent(View view);
        void onFailure(Exception e);
    }
}
