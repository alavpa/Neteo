package com.limpiezas.neteo.interactors;

import android.text.TextUtils;
import android.util.Log;

import com.limpiezas.neteo.data.model.Form;
import com.limpiezas.neteo.interactors.errors.NoCPException;
import com.limpiezas.neteo.interactors.errors.NoContactDataException;

import rx.Observable;
import rx.functions.Func0;

/**
 * Created by alavpa on 4/8/16.
 */
public class ValidateStep1 {

    Form form;

    public ValidateStep1(Form form){
        this.form = form;
    }

    public Observable<Void> execute(){
        return Observable
                .defer(new Func0<Observable<Void>>() {
                    @Override
                    public Observable<Void> call() {

                        Log.d("Save","validate1");
                        if(TextUtils.isEmpty(form.getCP())){
                            return Observable.error(new NoCPException());
                        }

                        if(!isValidEmail(form.getEmail())){
                            return Observable.error(new NoContactDataException());
                        }
                        return Observable.empty();
                    }
                });

    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
