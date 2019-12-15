package com.example.rxjavaexample.operator.filter;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.example.rxjavaexample.utils.TAG;

//=================>>>>> TAKE, TAKE_LAST OPERATOR <<<<<=================
//=================>>>>> OBSERVER OBSERVER <<<<<=================
//=================>>>>> OBSERVABLE OBSERVABLE <<<<<=================

public class TakeOperator {
    Observable<Integer> observable;
    Observer<Integer> tackObserver;
    Observer<Integer> takeLastObserver;

    public TakeOperator() {

        observable = Observable.just(1, 2, 3, 4, 6, 7);

        tackObserver = new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe : isDisposed : "+d.isDisposed());
            }

            @Override
            public void onNext(Integer aLong) {
                Log.d(TAG, "onNext tack : value : "+aLong);
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

        takeLastObserver = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe : isDisposed : "+d.isDisposed());
            }

            @Override
            public void onNext(Integer aLong) {
                Log.d(TAG, "onNext tack : value : "+aLong);
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

    public Observable<Integer> getObservable() {
        return observable;
    }

    public Observer<Integer> getTackObserver() {
        return tackObserver;
    }

    public Observer<Integer> getTakeLastObserver() {
        return takeLastObserver;
    }
}
