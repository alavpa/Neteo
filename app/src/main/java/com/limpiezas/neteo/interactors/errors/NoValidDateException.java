package com.limpiezas.neteo.interactors.errors;

import com.limpiezas.neteo.R;
import com.limpiezas.neteo.ui.App;

/**
 * Created by alavpa on 4/8/16.
 */
public class NoValidDateException extends Exception {

    public NoValidDateException(){
        super(App.get().getString(R.string.exception_no_valid_date));
    }
}
