package com.example.rxjavaexample.operator.filter;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.example.rxjavaexample.utils.TAG;

//=================>>>>> COMBINE_LATEST OPERATOR <<<<<=================
//=================>>>>> OBSERVER OBSERVER <<<<<=================
//=================>>>>> OBSERVABLE OBSERVABLE <<<<<=================

public class CombineLatestOperator {

    Observable<Integer> firstObservable;
    Observable<Integer> secondObservable;
    Observer<String> observer;

    public CombineLatestOperator() {

        firstObservable = Observable.just(1, 3, 4);
        secondObservable = Observable.just(10, 20, 30);

        observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe : isDisposed : "+d.isDisposed());
            }

            @Override
            public void onNext(String aLong) {
                Log.d(TAG, "onNext : value : "+aLong);
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

    public Observable<Integer> getFirstObservable() {
        return firstObservable;
    }

    public Observable<Integer> getSecondObservable() {
        return secondObservable;
    }

    public Observer<String> getObserver() {
        return observer;
    }
}
