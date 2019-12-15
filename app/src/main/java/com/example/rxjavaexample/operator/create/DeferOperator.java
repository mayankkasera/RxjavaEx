package com.example.rxjavaexample.operator.create;

import android.util.Log;

import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;

import static com.example.rxjavaexample.utils.TAG;

//=================>>>>> DEFER OPERATOR <<<<<=================
//=================>>>>> SINGLE OBSERVER <<<<<=================
//=================>>>>> SINGLE OBSERVABLE <<<<<=================

public class DeferOperator {

    Single<String> observable;

    SingleObserver<String> Observer;


    public DeferOperator() {
        observable = Single.defer(new Callable<SingleSource<? extends String>>() {
            @Override
            public SingleSource<? extends String> call() throws Exception {
                long time = System.currentTimeMillis();
                return Single.just("Hello This is Generated from Defer Operator at time "+time);
            }
        });

        Observer = new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe : isDisposed : "+d.isDisposed());
            }

            @Override
            public void onSuccess(String s) {
                Log.d(TAG, "onSuccess : value : "+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError : "+e.getMessage());
            }
        };
    }

    public Single<String> getObservable() {
        return observable;
    }

    public SingleObserver<String> getObserver() {
        return Observer;
    }
}
