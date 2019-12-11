package com.example.rxjavaexample.basic;

import android.util.Log;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.disposables.Disposable;

import static com.example.rxjavaexample.utils.TAG;

// =================>>>>>COMPLETABLE OBSERVABLE <<<<<=================
// =================>>>>>COMPLETABLE OBSERVER <<<<<=================
// =================>>>>>CREATE OPERATOR <<<<<=================

public class CompletableHelper {

    Completable observable;
    CompletableObserver observer;

    public CompletableHelper() {
        observable = Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                emitter.onComplete();
            }
        });

        observer = new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe : isDisposed : "+d.isDisposed());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete : Completable Complete");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError : "+e.getMessage());
            }
        };
    }

    public Completable getObservable() {
        return observable;
    }

    public CompletableObserver getObserver() {
        return observer;
    }
}
