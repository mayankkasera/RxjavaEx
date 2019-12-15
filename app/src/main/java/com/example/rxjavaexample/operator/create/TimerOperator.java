package com.example.rxjavaexample.operator.create;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import static com.example.rxjavaexample.utils.TAG;

//=================>>>>> TIMER OPERATOR  <<<<<=================
//=================>>>>> OBSERVER OBSERVER <<<<<=================
//=================>>>>> OBSERVABLE OBSERVABLE<<<<<=================

public class TimerOperator {

    Single<Long> observable;
    SingleObserver<Long> observer;

    public TimerOperator() {
        observable = Single.timer(5, TimeUnit.SECONDS);

        observer = new SingleObserver<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe : isDisposed : "+d.isDisposed());
            }

            @Override
            public void onSuccess(Long aLong) {
                Log.d(TAG, "onSuccess : value : "+aLong);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError : "+e.getMessage());
            }
        };

    }

    public Single<Long> getObservable() {
        return observable;
    }

    public SingleObserver<Long> getObserver() {
        return observer;
    }
}
