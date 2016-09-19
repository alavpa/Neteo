package com.limpiezas.neteo.interactors;

import android.util.Log;

import com.limpiezas.neteo.data.model.Form;
import com.limpiezas.neteo.interactors.errors.NoPaymentSelectedException;
import com.limpiezas.neteo.interactors.errors.NoValidDateException;

import java.util.Calendar;

import rx.Observable;
import rx.functions.Func0;

/**
 * Created by alavpa on 4/8/16.
 */
public class ValidateStep3 {

    Form form;

    public ValidateStep3(Form form){
        this.form = form;
    }

    public Observable<Void> execute(){
        return Observable
                .defer(new Func0<Observable<Void>>() {
                    @Override
                    public Observable<Void> call() {

                        Log.d("Save","validate3");
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.HOUR,24);

                        if(form.getDate().getTime()<calendar.getTimeInMillis()){
                            return Observable.error(new NoValidDateException());
                        }

                        if(form.getPayment()<0){
                            return Observable.error(new NoPaymentSelectedException());
                        }

                        return Observable.empty();
                    }
                });

    }
}
