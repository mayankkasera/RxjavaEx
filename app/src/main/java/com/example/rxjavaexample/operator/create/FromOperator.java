package com.example.rxjavaexample.operator.create;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

import static com.example.rxjavaexample.utils.TAG;

//=================>>>>> FROM OPERATOR <<<<<=================
//=================>>>>> SINGLE OBSERVER <<<<<=================
//=================>>>>> SINGLE OBSERVABLE <<<<<=================

public class FromOperator {

    Single<String> observable;

    SingleObserver<String> observer;

    public FromOperator() {
        Observable obs = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                //Do Your Custom Calls to observables
                emitter.onNext("This is first Value of Observable");

                emitter.onNext("This is second Value of Observable");

                emitter.onNext("This is third Value of Observable");

                emitter.onNext("This is fourth Value of Observable");

                emitter.onNext("This is fifth Value of Observable");

                emitter.onNext("This will print.");

                emitter.onComplete();
            }
        })
        .filter(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Exception {
                return s=="This will print.";
            }
        });

        observable = Single.fromObservable(obs);

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
