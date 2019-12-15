package com.example.rxjavaexample.operator.filter;

import android.util.Log;

import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import static com.example.rxjavaexample.utils.TAG;

//=================>>>>> FIRST , FIRST_ELEMENT OPERATOR <<<<<=================
//=================>>>>> OBSERVER OBSERVER <<<<<=================
//=================>>>>> OBSERVABLE OBSERVABLE <<<<<=================

public class FirstOperator {

    Observable<Integer> observable;
    SingleObserver<Integer> singleObserver;
    MaybeObserver<Integer> maybeObserver;

    public FirstOperator() {
        observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                //Creating  Duplicate Elements
                emitter.onNext(20);
                emitter.onNext(30);
                emitter.onNext(45);
                emitter.onNext(100);
                emitter.onNext(33);
                emitter.onComplete();
            }
        });

        singleObserver = new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe : isDisposed : "+d.isDisposed());
            }

            @Override
            public void onSuccess(Integer s) {
                Log.d(TAG, "onSuccess : value : "+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError : "+e.getMessage());
            }
        };

        maybeObserver = new MaybeObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe : isDisposed : "+d.isDisposed());
            }

            @Override
            public void onSuccess(Integer s) {
                Log.d(TAG, "onSuccess : value : "+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError : "+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete : MayBe Complete");
            }
        };
    }

    public Observable<Integer> getObservable() {
        return observable;
    }

    public SingleObserver<Integer> getSingleObserver() {
        return singleObserver;
    }

    public MaybeObserver<Integer> getMaybeObserver() {
        return maybeObserver;
    }
}
