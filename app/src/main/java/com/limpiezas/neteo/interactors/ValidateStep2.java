package com.limpiezas.neteo.interactors;

import android.util.Log;

import com.limpiezas.neteo.data.model.Form;
import com.limpiezas.neteo.interactors.errors.NoServiceSelectedException;

import rx.Observable;
import rx.functions.Func0;

/**
 * Created by alavpa on 4/8/16.
 */
public class ValidateStep2 {

    Form form;

    public ValidateStep2(Form form){
        this.form = form;
    }

    public Observable<Void> execute(){
        return Observable
                .defer(new Func0<Observable<Void>>() {
                    @Override
                    public Observable<Void> call() {

                        Log.d("Save","validate2");
                        if(form.getService()<0){
                            return Observable.error(new NoServiceSelectedException());
                        }

                        return Observable.empty();
                    }
                });

    }
}
