package com.example.rxjavaexample.basic;

import android.util.Log;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;

import static com.example.rxjavaexample.utils.TAG;

//=================>>>>>SINGLE OBSERVABLE <<<<<===============
//=================>>>>>SINGLE OBSERVER <<<<<=================
//=================>>>>>CREATE OPERATOR <<<<<=================

public class SingleHelper {

    Single<String> observable;
    SingleObserver<String> observer;

    public SingleHelper() {

        observable = Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> emitter) throws Exception {
                emitter.onSuccess("Hello Coming from Single Observable");
            }
        });

        observer = new SingleObserver<String>() {
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
        return observer;
    }
}
