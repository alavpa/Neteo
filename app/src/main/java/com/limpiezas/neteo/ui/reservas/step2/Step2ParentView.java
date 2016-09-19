package com.limpiezas.neteo.ui.reservas.step2;

import com.limpiezas.neteo.data.model.Form;
import com.limpiezas.neteo.ui.BaseView;

/**
 * Created by alavpa on 5/8/16.
 */
public interface Step2ParentView extends BaseView{
    Form getForm();

    void goToStep1();
    void goToStep3();
}
