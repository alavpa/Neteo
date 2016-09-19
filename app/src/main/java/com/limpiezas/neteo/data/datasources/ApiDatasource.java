package com.limpiezas.neteo.data.datasources;

import java.util.Date;

/**
 * Created by alavpa on 3/8/16.
 */
public interface ApiDatasource {

    String getName();
    String getAddress1();
    String getAddress2();
    String getCP();
    String getEmail();
    String getPhone();
    int getService();
    int getType();
    Boolean notNeedProducts();
    Date getDate();
    int getPayment();

    void setName(String name);
    void setAddress1(String address1);
    void setAddress2(String address2);
    void setCP(String cp);
    void setEmail(String email);
    void setPhone(String phone);
    void setService(int service);
    void setType(int type);
    void setNotNeedProducts(boolean set);
    void setDate(Date date);
    void setPayment(int payment);



}
