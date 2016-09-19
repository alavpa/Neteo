package com.limpiezas.neteo.ui.reservas.step1;

/**
 * Created by alavpa on 5/8/16.
 */
public interface Step1View {

    void setName(String name);

    void setAddressLine1(String address1);

    void setAddressLine2(String address2);

    void setCP(String cp);

    void setContact(int pos, String contact);

    String getName();

    String getAddress1();

    String getAddress2();

    String getCP();

    void setContactHintInputTypeValue(String hint, int inputType, String value);
    int getContactSelected();

    String getContact();

}
