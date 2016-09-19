package com.limpiezas.neteo.ui.reservas;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.limpiezas.neteo.R;
import com.limpiezas.neteo.data.model.Form;
import com.limpiezas.neteo.interactors.GetForm;
import com.limpiezas.neteo.ui.App;
import com.limpiezas.neteo.ui.reservas.step1.Step1View;
import com.limpiezas.neteo.ui.reservas.step2.Step2View;
import com.limpiezas.neteo.ui.reservas.step3.Step3View;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by alavpa on 5/8/16.
 */
public class Reservas2Presenter {

    Reservas2View view;
    Form form;
    Step1View step1;
    Step2View step2;
    Step3View step3;
    public Reservas2Presenter(Reservas2View view, Step1View step1, Step2View step2, Step3View step3){
        this.view = view;
        this.step1 = step1;
        this.step2 = step2;
        this.step3 = step3;
    }

    public void init(){

        if(view!=null){
            view.startLoading();
        }

        Subscription subscription = new GetForm().execute()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Form>() {
                    @Override
                    public void onCompleted() {
                        if(view!=null){
                            view.stopLoading();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(view!=null){
                            view.stopLoading();
                            view.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(Form vform) {

                        form = vform;
                        if(view!=null){
                            view.initView();
                            populateStep1();
                        }
                    }
                });

        if(view!=null){
            view.addSubscription(subscription);
        }
    }

    public void populateStep1(){
        if(view!=null){
            view.setTitle(R.string.reservas2_step1);
        }
    }

    public void populateStep2(){

        if(view!=null){
            view.setTitle(R.string.reservas2_step2);
            view.hideKeyboard();
        }
    }

    public void populateStep3(){

        if(view!=null){
            view.setTitle(R.string.reservas2_step3);
            view.hideKeyboard();
        }
    }

    public void onPageSelected(int position) {
        if(position==0){
            populateStep1();
        }
        if(position==1){
            populateStep2();
        }
        if(position==2){
            populateStep3();
        }
    }

    public Form getForm() {
        return form;
    }

    public boolean isDeviceOnline() {
        ConnectivityManager connMgr =
                (ConnectivityManager) App.get().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }



}
