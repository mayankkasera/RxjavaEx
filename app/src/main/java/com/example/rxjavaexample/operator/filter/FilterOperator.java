package com.example.rxjavaexample.operator.filter;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.example.rxjavaexample.utils.TAG;

//=================>>>>> FILTER OPERATOR <<<<<=================
//=================>>>>> OBSERVER OBSERVER <<<<<=================
//=================>>>>> OBSERVABLE OBSERVABLE <<<<<=================

public class FilterOperator {

    Observable<Integer> observable;
    Observer<Integer> observer;


    public FilterOperator() {
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

        observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe : isDisposed : "+d.isDisposed());
            }

            @Override
            public void onNext(Integer aLong) {
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

    public Observable<Integer> getObservable() {
        return observable;
    }

    public Observer<Integer> getObserver() {
        return observer;
    }
}
