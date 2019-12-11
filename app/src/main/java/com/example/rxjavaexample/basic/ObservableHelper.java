package com.example.rxjavaexample.basic;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.example.rxjavaexample.utils.TAG;

// =================>>>>> OBSERVABLE <<<<<=================
// =================>>>>> OBSERVER <<<<<=================
// =================>>>>> CREATE OPERATOR <<<<<=================


public class ObservableHelper {

    private Observable<Long> observable;
    private Observer<Long> observer;

    public ObservableHelper() {
        observable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {

                //Start downloading

                //Downloading 10%
                if (!emitter.isDisposed())
                    emitter.onNext(10l);

                //Downloading 20%
                if (!emitter.isDisposed())
                    emitter.onNext(20l);

                //Downloading 40%
                if (!emitter.isDisposed())
                    emitter.onNext(40l);

                //Downloading 80%
                if (!emitter.isDisposed())
                    emitter.onNext(80l);

                //Downloading 100%
                if (!emitter.isDisposed()) {
                    emitter.onNext(100l);
                    //Downloading Completed
                    emitter.onComplete();

                }
            }
        });

        observer = new Observer<Long>(){
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe : isDisposed : "+d.isDisposed());
            }

            @Override
            public void onNext(Long aLong) {
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

    public Observable<Long> getObservable() {
        return observable;
    }


    public Observer<Long> getObserver(){
        return observer;
    }




}

