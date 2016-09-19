package com.limpiezas.neteo.ui.reservas.step3;

import java.util.Date;

/**
 * Created by alavpa on 5/8/16.
 */
public interface Step3View {
    int getCleanType();
    void setCleanType(int type);

    boolean getNotCleaningProducts();
    void setNotCleaningProducts(boolean notneed);

    void setTime(String time);
    void setDate(String date);

    int getPayment();
    void setPayment(int payment);

    void initDateDialogs(Date date);
}
