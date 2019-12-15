package com.example.rxjavaexample.operator.filter;

import android.util.Log;

import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.example.rxjavaexample.utils.TAG;

//=================>>>>> IGNORE_ELEMENTS OPERATOR <<<<<=================
//=================>>>>> MAYBE OBSERVER <<<<<=================
//=================>>>>> OBSERVABLE OBSERVABLE <<<<<=================

public class IgnoreElementsOperator {

    Observable<Integer> observable;
    CompletableObserver observer;

    public IgnoreElementsOperator() {
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

        observer = new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe : isDisposed : "+d.isDisposed());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete : Completable Complete");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError : "+e.getMessage());
            }
        };
    }



    public Observable<Integer> getObservable() {
        return observable;
    }

    public CompletableObserver getObserver() {
        return observer;
    }
}
