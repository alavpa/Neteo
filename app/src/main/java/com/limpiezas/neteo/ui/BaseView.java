package com.limpiezas.neteo.ui;

import rx.Subscription;

/**
 * Created by alavpa on 8/7/16.
 */
public interface BaseView {

    void showError(String message);
    void finish();
    void startLoading();
    void stopLoading();
    void addSubscription(Subscription subscription);
    void setResult(int resultCode);
}
