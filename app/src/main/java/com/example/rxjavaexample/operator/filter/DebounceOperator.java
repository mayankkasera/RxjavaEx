package com.example.rxjavaexample.operator.filter;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.example.rxjavaexample.utils.TAG;

//=================>>>>> DEBOUNCE OPERATOR <<<<<=================
//=================>>>>> OBSERVER OBSERVER <<<<<=================
//=================>>>>> OBSERVABLE OBSERVABLE <<<<<=================

public class DebounceOperator {
    Observable<String> observable;
    Observer<String> observer;

    public DebounceOperator() {
        observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                //Do Your Custom Calls to observables
                emitter.onNext("A");

                Thread.sleep(1500);
                emitter.onNext("B");

                Thread.sleep(500);
                emitter.onNext("C");

                Thread.sleep(250);
                emitter.onNext("D");

                Thread.sleep(2000);
                emitter.onNext("E");
                emitter.onComplete();
            }
        });

        observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe : isDisposed : "+d.isDisposed());
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext : DownloadInProgress : "+s);
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

    public io.reactivex.Observable<String> getObservable() {
        return observable;
    }

    public Observer<String> getObserver() {
        return observer;
    }
}
