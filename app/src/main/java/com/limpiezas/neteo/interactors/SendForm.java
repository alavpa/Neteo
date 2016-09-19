package com.limpiezas.neteo.interactors;

import android.text.TextUtils;
import android.util.Log;

import com.limpiezas.neteo.BuildConfig;
import com.limpiezas.neteo.data.model.Form;
import com.limpiezas.neteo.interactors.errors.SendEmailException;
import com.limpiezas.neteo.interactors.utils.FormMailFormatter;
import com.limpiezas.neteo.data.model.Mail;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by alavpa on 11/8/16.
 */
public class SendForm {

    Form form;

    public SendForm(Form form){
        this.form = form;
    }

    public Observable<Void> execute(){

        Log.d("Send", "saving");


        return new FormMailFormatter(form)
                .execute()
                .flatMap(new Func1<String, Observable<Void>>() {
                    @Override
                    public Observable<Void> call(String body) {
                        try {
                            Mail mail = new Mail(BuildConfig.EMAIL_USERNAME,BuildConfig.EMAIL_PASSWORD);
                            mail.set_from("limpiezasneteo@gmail.com");
                            mail.set_subject("Limpiezas Néteo Reservas");
                            mail.setBody(body);
                            if(!TextUtils.isEmpty(form.getEmail().trim())){
                                mail.set_to(new String[]{form.getEmail()});
                                if(!mail.send()){
                                    return Observable.error(new SendEmailException());
                                }
                            }

                            mail.set_to(new String[]{"limpiezasneteo@gmail.com"});
                            if(!mail.send()){
                                return Observable.error(new SendEmailException());
                            }

                            return Observable.empty();

                        } catch (Throwable t) {
                            return Observable.error(new SendEmailException());
                        }

                    }
                });


    }
}
