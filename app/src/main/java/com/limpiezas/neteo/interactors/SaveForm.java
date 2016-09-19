package com.limpiezas.neteo.interactors;

import android.util.Log;

import com.limpiezas.neteo.data.FormRepository;
import com.limpiezas.neteo.data.datasources.PreferencesDatasource;
import com.limpiezas.neteo.data.model.Form;

import rx.Observable;
import rx.functions.Func0;

/**
 * Created by alavpa on 3/8/16.
 */
public class SaveForm {
    FormRepository repository = new FormRepository(new PreferencesDatasource());
    Form form;

    public SaveForm(Form form){
        this.form = form;
    }

    public Observable<Void> execute(){

        return Observable.defer(new Func0<Observable<Void>>() {
            @Override
            public Observable<Void> call() {
                Log.d("Save","saving");
                try {
                    repository.saveForm(form);
                }catch (Throwable t){
                    return Observable.error(t);
                }
                return Observable.empty();
            }
        });

    }

}
