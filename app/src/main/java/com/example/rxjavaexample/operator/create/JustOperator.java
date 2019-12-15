package com.example.rxjavaexample.operator.create;

import android.util.Log;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import static com.example.rxjavaexample.utils.TAG;


//=================>>>>> JUST OPERATOR  <<<<<=================
//=================>>>>> SINGLE OBSERVABLE<<<<<=================
//=================>>>>> SINGLE OBSERVER <<<<<=================


public class JustOperator {

    Single<String> observable;

    SingleObserver<String> Observer;

    public JustOperator() {
        observable = Single.just("Hello From Single");

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
