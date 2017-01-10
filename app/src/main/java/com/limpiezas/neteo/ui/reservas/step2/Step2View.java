package com.limpiezas.neteo.ui.reservas.step2;

/**
 * Created by alavpa on 5/8/16.
 */
public interface Step2View {

    int getServiceSelected();
    void selectService(int service);
    boolean getNotCleaningProducts();
    void setNotCleaningProducts(boolean notneed);
    void setList(boolean notCleaning);

}
