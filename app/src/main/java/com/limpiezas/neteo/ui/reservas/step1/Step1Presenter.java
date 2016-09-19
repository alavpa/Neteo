package com.limpiezas.neteo.ui.reservas.step1;

import com.limpiezas.neteo.data.model.Form;
import com.limpiezas.neteo.interactors.SaveForm;
import com.limpiezas.neteo.interactors.ValidateStep1;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by alavpa on 5/8/16.
 */
public class Step1Presenter {
    
    Step1View view;
    Step1ParentView parent;
    Form form;
    public Step1Presenter(Step1View view, Step1ParentView parent){
        this.view = view;
        this.parent =parent;
    }
    
    public void init(){
        
        if(parent!=null){
            form = parent.getForm();
            fillData(form);
        }
        

    }
    
    private void fillData(Form form){
        this.form = form;
        if(view!=null){
            view.setName(form.getName());
            view.setAddressLine1(form.getAddress1());
            view.setAddressLine2(form.getAddress2());
            view.setCP(form.getCP());
            view.setEmail(form.getEmail());
            view.setPhone(form.getPhone());
        }
    }
    
    private void setData(){
        if(view!=null){
            form.setName(view.getName());
            form.setAddress1(view.getAddress1());
            form.setAddress2(view.getAddress2());
            form.setCP(view.getCP());
            form.setEmail(view.getEmail());
            form.setPhone(view.getPhone());
        }
    }

    private void saveData(){
        if(parent!=null){
            parent.startLoading();
        }

        Observable<Void> validate = new ValidateStep1(form).execute();
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

    public void onNext() {
        setData();
        saveData();
    }
}
