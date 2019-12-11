package com.example.rxjavaexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rxjavaexample.basic.CompletableHelper;
import com.example.rxjavaexample.basic.FlowableHelper;
import com.example.rxjavaexample.basic.MaybeHelper;
import com.example.rxjavaexample.basic.ObservableHelper;
import com.example.rxjavaexample.basic.SingleHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        completableEx();

    }

     /*

       Rx java having 3 heroes

       ---> OBSERVABLE
       ---> OBSERVER
       ---> OPERATOR

       OBSERVABLE ---> Which emits the value.
       OPERATOR ---> Modify the input.
       OBSERVER ---> Gets those values.

       Type of OBSERVABLE

       1 ---> Observable (Emits one or more than one value)
       2 ---> Single (Emits only one value)
       3 ---> Maybe (This may or may not emits value)
       4 ---> FlowableHelper (Emits huge amount of data)
       5 ---> Completable (Emits just success or failure)

       Type of OBSERVER

       1 ---> Observer   ( onSubscribe(),onNext(),onError,onComplete() )
       2 ---> SingleObserver   ( onSubscribe(),onSuccess(),onError() )
       3 ---> MayBeObserver  ( onSubscribe(),onSuccess(),onError(),onCompleted() )
       4 ---> CompletableObserver ( onSubscribe(),  onCompleted(), onError() )


     */

     /*

      =================> Observables Ex <=================

      This is the simplest Observable which can emit more than one value.
      Example use-case: Let’s say you are downloading a file and you
      have to push the current status of download percentage.
      Here, you will have to emit more than one value.Creating a simple Observable


    */

     void observablesEx(){
         ObservableHelper observableHelper = new ObservableHelper();
         observableHelper.getObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                 .subscribe(observableHelper.getObserver());
     }


    /*

      =================> Single Ex <=================

      Single is used when the Observable has to emit only one value
      like a response from a network call.

    */

    void singleEx(){
        SingleHelper singleHelper = new SingleHelper();
        singleHelper.getObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(singleHelper.getObserver());
    }


    /*

      =================> Maybe Ex <=================

      Maybe is used when the Observable has to emit a
      value or no value.

    */

    void maybeEx(){
        MaybeHelper maybeHelper = new MaybeHelper();
        maybeHelper.getObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(maybeHelper.getObserver());
    }


    /*

      =================> FlowAble Ex <=================

      FlowAble is typically used when an Observable is emitting huge amounts of data
      but the Observer is not able to handle this data emission. This is known as Back Pressure.
      Sample Implementation: The below sample provides a range of integers from 10 to 1000 and
      uses the reduce() operator to add the sum of the integers and emit the final sum value.

    */

    void flowAbleEx(){
        FlowableHelper flowableHelper = new FlowableHelper();

        flowableHelper.getObservable().reduce(1, new BiFunction<Integer,Integer,Integer>() {
            @Override
            public Integer apply(Integer integer1, Integer integer2) throws Exception {
                return integer1+integer2;
            }
        })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(flowableHelper.getObserver());
    }


    /*

      =================> Completable Ex <=================

      Completable is used when the Observable has to do some task
      without emitting a value.

    */

    void completableEx(){
        CompletableHelper helper = new CompletableHelper();
        helper.getObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(helper.getObserver());
    }




}
