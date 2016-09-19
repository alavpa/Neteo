package com.limpiezas.neteo.ui.reservas;

import com.limpiezas.neteo.ui.BaseView;

/**
 * Created by alavpa on 5/8/16.
 */
public interface Reservas2View extends BaseView{

    void initView();
    void setTitle(int stringId);
    void moveToPage(int page);
    int getCurrentPage();
    void hideKeyboard();

}
