package com.example.rxjavaexample.basic;

import android.util.Log;

import io.reactivex.Flowable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import static com.example.rxjavaexample.utils.TAG;

//=================>>>>>FLOWABLE OBSERVABLE <<<<<=================
//=================>>>>> SINGLE OBSERVER <<<<<=================
//=================>>>>> RANGE OPERATOR <<<<<=================

public class FlowableHelper {

    Flowable observable;
    SingleObserver<Integer> observer;

    public FlowableHelper() {
        observable = Flowable.range(10,1000);

        observer = new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe : isDisposed : "+d.isDisposed());
            }

            @Override
            public void onSuccess(Integer integer) {
                Log.d(TAG, "onSuccess : value : "+integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError : "+e.getMessage());
            }
        };
    }

    public Flowable getObservable() {
        return observable;
    }

    public SingleObserver<Integer> getObserver() {
        return observer;
    }
}
