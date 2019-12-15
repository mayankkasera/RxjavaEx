package com.example.rxjavaexample.operator.create;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.example.rxjavaexample.utils.TAG;

//=================>>>>> RANGE OPERATOR <<<<<=================
//=================>>>>> OBSERVER OBSERVER <<<<<=================
//=================>>>>> OBSERVABLE OBSERVABLE <<<<<=================


public class RangeOperator {

    Observable<Integer> observable;
    Observer<Integer> observer;

    public RangeOperator() {

        observable = Observable.range(1, 10);

        observer = new Observer<Integer>() {

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext : DownloadInProgress : "+integer);
            }

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe : isDisposed : "+d.isDisposed());
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
