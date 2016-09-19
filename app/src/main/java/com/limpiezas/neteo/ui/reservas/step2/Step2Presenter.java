package com.limpiezas.neteo.ui.reservas.step2;

import com.limpiezas.neteo.data.model.Form;
import com.limpiezas.neteo.interactors.SaveForm;
import com.limpiezas.neteo.interactors.ValidateStep2;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by alavpa on 5/8/16.
 */
public class Step2Presenter {

    Step2View view;
    Step2ParentView parent;
    Form form;


    public Step2Presenter(Step2View view, Step2ParentView parent) {
        this.view = view;
        this.parent = parent;
    }

    public void init(){
        if(parent!=null){
            form = parent.getForm();
            fillData(form);
        }
    }

    private void fillData(Form form) {
        if(view!=null){
            view.setList(form.isNot_products());
            view.selectService(form.getService());
            view.setNotCleaningProducts(form.isNot_products());
        }
    }

    private void setData(){
        if(view!=null){
            form.setService(view.getServiceSelected());
            form.setNot_products(view.getNotCleaningProducts());
        }
    }

    private void saveData(final boolean nextPrev){
        if(parent!=null){
            parent.startLoading();
        }

        Observable<Void> validate = new ValidateStep2(form).execute();
        Observable<Void> save = new SaveForm(form).execute();

        Subscription subscription = Observable.concat(validate,save)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        if(parent!=null){
                            parent.stopLoading();

                            if(nextPrev){
                                parent.goToStep3();
                            }else{
                                parent.goToStep1();
                            }
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

    public void onNext(){
        setData();
        saveData(true);
    }

    public void onPrev(){
        setData();
        saveData(false);
    }

    public void onCheckedChanged(boolean notneeded){
        if(view!=null){
            view.setList(notneeded);
        }
    }
}
