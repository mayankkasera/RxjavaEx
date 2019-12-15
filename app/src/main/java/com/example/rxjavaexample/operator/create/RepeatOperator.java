package com.example.rxjavaexample.operator.create;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.example.rxjavaexample.utils.TAG;

public class RepeatOperator {

    Observable<String> observable;
    Observer<String> observer;

    public RepeatOperator() {

        observable = Observable.just("first value", "second value").repeat(5);
        observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe : isDisposed : "+d.isDisposed());
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext : DownloadInProgress : "+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError : "+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete : Download Complete");
            }

        };

    }

    public Observable<String> getObservable() {
        return observable;
    }

    public Observer<String> getObserver() {
        return observer;
    }
}
