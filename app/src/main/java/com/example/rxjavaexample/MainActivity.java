package com.example.rxjavaexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rxjavaexample.basic.CompletableHelper;
import com.example.rxjavaexample.basic.FlowableHelper;
import com.example.rxjavaexample.basic.MaybeHelper;
import com.example.rxjavaexample.basic.ObservableHelper;
import com.example.rxjavaexample.basic.SingleHelper;
import com.example.rxjavaexample.operator.create.CreateOperator;
import com.example.rxjavaexample.operator.create.DeferOperator;
import com.example.rxjavaexample.operator.create.FromOperator;
import com.example.rxjavaexample.operator.create.IntervalOperator;
import com.example.rxjavaexample.operator.create.JustOperator;
import com.example.rxjavaexample.operator.create.RangeOperator;
import com.example.rxjavaexample.operator.create.RepeatOperator;
import com.example.rxjavaexample.operator.create.StartOperator;
import com.example.rxjavaexample.operator.create.TimerOperator;
import com.example.rxjavaexample.operator.filter.CombineLatestOperator;
import com.example.rxjavaexample.operator.filter.DebounceOperator;
import com.example.rxjavaexample.operator.filter.DistinctOperator;
import com.example.rxjavaexample.operator.filter.ElementAtOperator;
import com.example.rxjavaexample.operator.filter.FilterOperator;
import com.example.rxjavaexample.operator.filter.FirstOperator;
import com.example.rxjavaexample.operator.filter.IgnoreElementsOperator;
import com.example.rxjavaexample.operator.filter.LastElementIOperator;
import com.example.rxjavaexample.operator.filter.MergeOperator;
import com.example.rxjavaexample.operator.filter.SkipOperator;
import com.example.rxjavaexample.operator.filter.TakeOperator;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        merge();

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


//=======================> RX_JAVA OPERATORS (CREATING) <=========================//


    /*

      =================> JUST OPERATOR <=================

      The Just operator converts an item into an Observable that emits that item.
      Just is similar to From, but note that From will dive into an array or an iterable
      or something of that sort to pull out items to emit, while Just will simply emit the
      array or iterable or what-have-you as it is, unchanged, as a single item.
      Note that if you pass null to Just, it will return an Observable that emits null as an item.
      Do not make the mistake of assuming that this will return an empty Observable (one that emits no items at all).
      For that, you will need the Empty operator.


    */

    void justEx(){
        JustOperator justOperator = new JustOperator();
        justOperator.getObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(justOperator.getObserver());
    }

    /*

       =================> DEFER OPERATOR <=================

       The Defer operator waits until an observer subscribes to it, and then it generates
       an Observable, typically with an Observable factory function. It does this afresh for each
       subscriber, so although each subscriber may think it is subscribing to the same Observable,
       in fact each subscriber gets its own individual sequence.
       In some circumstances, waiting until the last minute (that is, until subscription time) to
       generate the Observable can ensure that this Observable contains the freshest data.

    */


    void deferEx(){
        DeferOperator deferOperator = new DeferOperator();

        deferOperator.getObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(deferOperator.getObserver());

        deferOperator.getObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(deferOperator.getObserver());
    }


    /*
       =================> CREATE OPERATOR <=================

       You can create an Observable from scratch by using the Create operator.
       You pass this operator a function that accepts the observer as its parameter. Write this
       function so that it behaves as an Observable — by calling the observer’s onNext, onError,
       and onCompleted methods appropriately.

       A well-formed finite Observable must attempt to call either the observer’s onCompleted method
       exactly once or its onError method exactly once, and must not thereafter attempt to call any
       of the observer’s other methods.

    */

    void create(){
        CreateOperator createOperator = new CreateOperator();
        createOperator.getObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(createOperator.getObserver());
    }

    /*
        =================> FROM OPERATOR <=================

        When you work with Observables, it can be more convenient if all of the data you mean to
        work with can be represented as Observables, rather than as a mixture of Observables and
        other types. This allows you to use a single set of operators to govern the entire lifespan
        of the data stream.

        Iterables, for example, can be thought of as a sort of synchronous Observable; Futures,
        as a sort of Observable that always emits only a single item. By explicitly converting
        such objects to Observables, you allow them to interact as peers with other Observables.

        For this reason, most ReactiveX implementations have methods that allow you to convert
        certain language-specific objects and data structures into Observables.

    */

    void from(){
        FromOperator fromOperator = new FromOperator();
        fromOperator.getObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(fromOperator.getObserver());
    }


    /*
        =================> INTERVAL OPERATOR <=================

        The Interval operator returns an Observable that emits an infinite sequence of ascending
        integers, with a constant interval of time of your choosing between emissions.

    */

    void interval(){
        IntervalOperator operator = new IntervalOperator();
        operator.getObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(operator.getObserver());
    }


    /*
        =================> RANGE OPERATOR <=================

        The Range operator emits a range of sequential integers, in order, where you select the
        start of the range and its length..

    */

    void range(){
        RangeOperator operator = new RangeOperator();
        operator.getObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(operator.getObserver());
    }


    /*

       =================> REPEAT OPERATOR <=================

       The Repeat operator emits an item repeatedly. Some implementations of this operator
       allow you to repeat a sequence of items, and some permit you to limit the number of
       repetitions.

    */

    void repeat(){
        RepeatOperator operator = new RepeatOperator();
        operator.getObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(operator.getObserver());
    }

    /*
        =================> START OPERATOR <=================

        There are a number of ways that programming languages have for obtaining values as the
        result of calculations, with names like functions, futures, actions, callables, runnables,
        and so forth. The operators grouped here under the Start operator category make these things
        behave like Observables so that they can be chained with other Observables in an Observable
        cascade

    */

    void start(){
        StartOperator startOperator = new StartOperator();
        startOperator.getObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(startOperator.getObserver());
    }

    /*

        =================> TIMER OPERATOR <=================

        create an Observable that emits a particular item after
        a given delay

    */

    void timer(){
        TimerOperator timerOperator = new TimerOperator();
        timerOperator.getObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(timerOperator.getObserver());
    }


//=======================> RX_JAVA OPERATORS (FILTERING) <=========================//

    /*

        =================> DEBOUNCE OPERATOR <=================

        only emit an item from an Observable if a particular timespan has passed without it emitting
        another itemonly emit an item from an Observable if a particular timespan has passed without
        it emitting another item

        Drops items emitted by a reactive source that are followed by newer items before the given
        timeout value expires. The timer resets on each emission.This operator keeps track of the
        most recent emitted item, and emits this item only when enough time has passed without the
        source emitting any other items.

        // Diagram:
        // -0---------1s---------2s---------3s----------4s

        // -A--------------B----C-D-------------------E-|---->
        //  a---------1s
        //                 b---------1s
        //                      c---------1s
        //                        d---------1s
        //                                            e-|---->
        // -----------A---------------------D-----------E-|-->

    */

    void debounce(){

        DebounceOperator debounceOperator = new DebounceOperator();

        debounceOperator.getObservable().debounce(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(debounceOperator.getObserver());


    }

    /*
       =================> DISTINCT,UNTILLCHANGED OPERATOR <=================

       suppress duplicate items emitted by an Observable

       The Distinct operator filters an Observable by only allowing items through that have not
       already been emitted.

       In some implementations there are variants that allow you to adjust the criteria by which
       two items are considered “distinct.” In some, there is a variant of the operator that only
       compares an item against its immediate predecessor for distinctnes

    */

    void distinct(){
        DistinctOperator distinctOperator = new DistinctOperator();

        distinctOperator.getObservable().distinct()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(distinctOperator.getObserver());

        distinctOperator.getObservable().distinctUntilChanged()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(distinctOperator.getObserver());
    }

    /*

         =================> ELEMENT AT OPERATOR <=================

         emit only item n emitted by an Observable

         The ElementAt operator pulls an item located at a specified index location in the sequence
         of items emitted by the source Observable and emits that item as its own sole emission.

    */


    void elementAt(){

        ElementAtOperator elementAtOperator = new ElementAtOperator();

        elementAtOperator.getObservable().elementAt(2)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(elementAtOperator.getObserver());
    }

    /*

       =================> FILTER OPERATOR <=================

       emit only those items from an Observable that pass a predicate test

       The Filter operator filters an Observable by only allowing items through that pass a test
       that you specify in the form of a predicate function.


    */

    void filter(){
        FilterOperator filterOperator = new FilterOperator();

        filterOperator.getObservable().filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
               return (integer>40);
            }
        })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(filterOperator.getObserver());
    }

    /*

       =================> FIRST , FIRST_ELEMENT OPERATOR <=================

       emit only the first item (or the first item that meets some condition) emitted by an Observable

       If you are only interested in the first item emitted by an Observable, or the first item
       that meets some criteria, you can filter the Observable with the First operator.

       In some implementations, First is not implemented as a filtering operator that returns an
       Observable, but as a blocking function that returns a particular item at such time as the
       source Observable emits that item. In those implementations, if you instead want a filtering
       operator, you may have better luck with Take(1) or ElementAt(0).

       In some implementations there is also a Single operator. It behaves similarly to First except
       that it waits until the source Observable terminates in order to guarantee that it only emits
       a single item (otherwise, rather than emitting that item, it terminates with an error).

       You can use this to not only take the first item from the source Observable but to also
       guarantee that there was only one item.

    */

    void first(){

        FirstOperator firstOperator = new FirstOperator();

        firstOperator.getObservable().first(100)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(firstOperator.getSingleObserver());

        firstOperator.getObservable().firstElement()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(firstOperator.getMaybeObserver());

    }

    /*

         =================> IGNORE_ELEMENTS OPERATOR <=================

         do not emit any items from an Observable but mirror its termination notification

         The IgnoreElements operator suppresses all of the items emitted by the source Observable,
         but allows its termination notification (either onError or onCompleted) to pass through unchanged.
         If you do not care about the items being emitted by an Observable, but you do want to be
         notified when it completes or when it terminates with an error, you can apply the ignoreElements
         operator to the Observable, which will ensure that it will never call its observers’
         onNext handlers

    */


    void ignoreElements(){

        IgnoreElementsOperator ignoreElementsOperator = new IgnoreElementsOperator();

        ignoreElementsOperator.getObservable().ignoreElements()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(ignoreElementsOperator.getObserver());

    }

    /*

         =================> LAST, LAST_ELEMENT OPERATOR <=================

         emit only the last item (or the last item that meets some condition) emitted by an Observable

         if you are only interested in the last item emitted by an Observable, or the last item that
         meets some criteria, you can filter the Observable with the Last operator.

         In some implementations, Last is not implemented as a filtering operator that returns
         an Observable, but as a blocking function that returns a particular item when the source
         Observable terminates. In those implementations, if you instead want a filtering operator,
         you may have better luck with TakeLast(1).

    */

    void lastElement(){

        LastElementIOperator lastElementIOperator = new LastElementIOperator();

        //filtering for getting the default value
        lastElementIOperator.getObservable().filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
              return   integer>40;
            }
        })      .last(100)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(lastElementIOperator.getSingleObserver());


        lastElementIOperator.getObservable().lastElement()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(lastElementIOperator.getMaybeObserver());

    }

    /*

         =================> LAST, LAST_ELEMENT OPERATOR <=================

         suppress the first n items emitted by an Observable

         You can ignore the first n items emitted by an Observable and attend only to those items
         that come after, by modifying the Observable with the SkipOperator operator.

    */

    void skip(){

        SkipOperator skipOperator = new SkipOperator();

        //filtering the list by skipping first 2 values
        skipOperator.getObservable().skip(3)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(skipOperator.getSkipObserver());

        //filtering the list by skipping last 2 values
        skipOperator.getObservable().skipLast(2)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(skipOperator.getSkipLastObserver());

    }

    /*

       =================> TAKE, TAKE_LAST OPERATOR <=================

       emit only the first n items emitted by an Observable

       You can emit only the first n items emitted by an Observable and then complete while
       ignoring the remainder, by modifying the Observable with the Take operator.

    */

    void tack(){

        TakeOperator takeOperator = new TakeOperator();

        //filtering the list by taking first 2 values
        takeOperator.getObservable().take(2)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(takeOperator.getTackObserver());

        //filtering the list by taking last 2 values
        takeOperator.getObservable().takeLast(2)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(takeOperator.getTakeLastObserver());

    }

    /*

          =================> COMBINE_LATEST OPERATOR <=================

          when an item is emitted by either of two Observables, combine the latest item emitted by each
          Observable via a specified function and emit items based on the results of this function

          The CombineLatest operator behaves in a similar way to Zip, but while Zip emits items only
          when each of the zipped source Observables have emitted a previously unzipped item,
          CombineLatest emits an item whenever any of the source Observables emits an item (so long as
          each of the source Observables has emitted at least one item). When any of the source
          Observables emits an item, CombineLatest combines the most recently emitted items from each
          of the other source Observables, using a function you provide, and emits the return value
          from that function.
    */

    void combineLatest(){

        CombineLatestOperator combineLatestOperator = new CombineLatestOperator();

        //Combine two observables with combineLatest
        Observable.combineLatest(combineLatestOperator.getFirstObservable()
                , combineLatestOperator.getSecondObservable()
                , new BiFunction<Integer, Integer, String>() {
                    @Override
                    public String apply(Integer integer1, Integer integer2) throws Exception {
                        return "First Observable "+integer1+ " : Second Observable "+integer2;
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(combineLatestOperator.getObserver());

    }

    /*

         =================> MERGE OPERATOR <=================

         combine multiple Observables into one by merging their emissions

         You can combine the output of multiple Observables so that they act like a single Observable
         by using the Merge operator.
         Merge may interleave the items emitted by the merged Observables (a similar operator, Concat,
         does not interleave items, but emits all of each source Observable’s items in turn before
         beginning to emit items from the next source Observable).


    */

    void merge(){

        MergeOperator mergeOperator = new MergeOperator();

        mergeOperator.getFirstObservable().mergeWith(mergeOperator.getSecondObservable())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mergeOperator.getObserver());

    }




}
