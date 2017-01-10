package com.limpiezas.neteo.ui.reservas.step3;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.limpiezas.neteo.data.model.Form;
import com.limpiezas.neteo.interactors.SaveForm;
import com.limpiezas.neteo.interactors.SendForm;
import com.limpiezas.neteo.interactors.ValidateStep1;
import com.limpiezas.neteo.interactors.ValidateStep2;
import com.limpiezas.neteo.interactors.ValidateStep3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by alavpa on 5/8/16.
 */
public class Step3Presenter {

    Step3View view;
    Step3ParentView parent;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdt = new SimpleDateFormat("HH:mm");
    Form form;

    public Step3Presenter(Step3View view, Step3ParentView parent){
        this.view = view;
        this.parent = parent;
    }

    private void showTime(){

        String time = sdt.format(calendar.getTime());
        view.setTime(time);
    }

    private void showDate(){

        String date = DateFormat.getDateInstance().format(calendar.getTime());
        view.setDate(date);
    }

    public void setDate(int year, int month, int day){
        calendar.set(year,month,day);
        showDate();

    }

    public void setTime(int hour, int minute){
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        showTime();
    }

    public void init() {

        form = parent.getForm();
        fillData(form);
    }

    private void fillData(Form form) {
        if(view!=null){
            view.setComments(form.getComments());
            calendar.setTime(form.getDate());
            view.initDateDialogs(calendar.getTime());
            showDate();
            showTime();
            view.setPayment(form.getPayment());
        }
    }

    private void setData(){
        if(view!=null){
            form.setDate(calendar.getTime());
            form.setPayment(view.getPayment());
            form.setComments(view.getComments());
        }
    }

    private void saveData(){
        if(parent!=null){
            parent.startLoading();
        }

        Observable<Void> validate = new ValidateStep3(form).execute();
        Observable<Void> save = new SaveForm(form).execute();

        Subscription subscription = Observable.concat(validate,save)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        if(parent!=null){
                            parent.stopLoading();
                            parent.goToStep2();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(parent!=null){
                            parent.stopLoading();
                            parent.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(Void vVoid) {

                    }
                });

        if(parent!=null) {
            parent.addSubscription(subscription);
        }
    }

    public void onPrev(){
        setData();
        saveData();
    }

    public void reservar(){
        if(parent!=null){
            parent.startLoading();
        }

        setData();

        Observable<Void> validate1 = new ValidateStep1(form).execute();
        Observable<Void> validate2 = new ValidateStep2(form).execute();
        Observable<Void> validate3 = new ValidateStep3(form).execute();
        Observable<Void> save = new SaveForm(form).execute();
        Observable<Void> send = new SendForm(form).execute();

        Subscription subscription = Observable.concat(validate1,validate2,validate3,save,send)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        if(parent!=null){
                            parent.stopLoading();
                            parent.setResult(AppCompatActivity.RESULT_OK);
                            parent.finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(parent!=null){
                            parent.stopLoading();
                            parent.showError(e.getMessage());
                            Log.d("SendForm",e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(Void vVoid) {

                    }
                });

        if(parent!=null) {
            parent.addSubscription(subscription);
        }
    }
}
