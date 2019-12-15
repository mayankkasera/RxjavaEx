package com.example.rxjavaexample.operator.create;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.example.rxjavaexample.utils.TAG;

public class CreateOperator {

    Observable<String> observable;

    Observer<String> observer;

    public CreateOperator() {
        observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                //Do Your Custom Calls to observables
                emitter.onNext("This is first Value");

                emitter.onNext("This is second Value");

                emitter.onNext("This is third Value");

                emitter.onNext("This is fourth Value");

                emitter.onNext("This is fifth Value");

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
                Log.i(TAG, "onNext: "+s);
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

    public Observable<String> getObservable() {
        return observable;
    }

    public Observer<String> getObserver() {
        return observer;
    }
}
