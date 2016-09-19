package com.limpiezas.neteo.interactors;


import com.limpiezas.neteo.data.FormRepository;
import com.limpiezas.neteo.data.datasources.PreferencesDatasource;
import com.limpiezas.neteo.data.model.Form;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by alavpa on 4/8/16.
 */
public class GetForm {

    FormRepository formRepository = new FormRepository(new PreferencesDatasource());

    public Observable<Form> execute(){
        return Observable
                .defer(new Func0<Observable<Form>>() {
                    @Override
                    public Observable<Form> call() {
                        return Observable.just(formRepository.getForm());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }
}
