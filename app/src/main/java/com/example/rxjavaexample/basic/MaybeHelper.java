package com.example.rxjavaexample.basic;

import android.util.Log;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import static com.example.rxjavaexample.utils.TAG;


//=================>>>>>MAYBE OBSERVABLE <<<<<=================
//=================>>>>>MAYBE OBSERVER <<<<<=================
//=================>>>>> CREATE OPERATOR <<<<<=================



public class MaybeHelper {

    Maybe<String> observable;
    MaybeObserver<String> observer;


    public MaybeHelper() {

        observable = Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> emitter) throws Exception {
                emitter.onSuccess("Hello this is coming from MayBe Observable");
                emitter.onComplete();
            }
        });

        observer = new MaybeObserver<String>() {
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

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete : MayBe Complete");
            }
        };
    }

    public Maybe<String> getObservable() {
        return observable;
    }

    public MaybeObserver<String> getObserver() {
        return observer;
    }
}
