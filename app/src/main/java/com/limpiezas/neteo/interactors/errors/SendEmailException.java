package com.limpiezas.neteo.interactors.errors;

import com.limpiezas.neteo.R;
import com.limpiezas.neteo.ui.App;

/**
 * Created by alavpa on 4/8/16.
 */
public class SendEmailException extends Exception {

    public SendEmailException(){
        super(App.get().getString(R.string.exception_send_email));
    }
}
