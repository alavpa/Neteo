package com.limpiezas.neteo.ui.reservas.step3;

import java.util.Date;

/**
 * Created by alavpa on 5/8/16.
 */
public interface Step3View {

    void setTime(String time);
    void setDate(String date);

    int getPayment();
    void setPayment(int payment);

    void initDateDialogs(Date date);

    String getComments();
    void setComments(String comments);
}
