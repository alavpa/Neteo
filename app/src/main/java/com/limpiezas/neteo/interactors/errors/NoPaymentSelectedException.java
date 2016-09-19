package com.limpiezas.neteo.interactors.errors;

import com.limpiezas.neteo.R;
import com.limpiezas.neteo.ui.App;

/**
 * Created by alavpa on 4/8/16.
 */
public class NoPaymentSelectedException extends Exception {

    public NoPaymentSelectedException(){
        super(App.get().getString(R.string.exception_no_payment));
    }
}
