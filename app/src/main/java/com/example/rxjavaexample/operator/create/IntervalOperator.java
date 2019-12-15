package com.example.rxjavaexample.operator.create;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.example.rxjavaexample.utils.TAG;

//=================>>>>> INTERVAL OPERATOR <<<<<=================
//=================>>>>> OBSERVER OBSERVER <<<<<=================
//=================>>>>> OBSERVABLE OBSERVABLE <<<<<=================

public class IntervalOperator {

    Observable<Long> observable;

    Observer<Long> observer;


    public IntervalOperator() {
        observable = Observable.interval(1, TimeUnit.SECONDS);

        observer = new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe : isDisposed : "+d.isDisposed());
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "onNext : DownloadInProgress : "+aLong);
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

    public Observable<Long> getObservable() {
        return observable;
    }

    public Observer<Long> getObserver() {
        return observer;
    }
}
