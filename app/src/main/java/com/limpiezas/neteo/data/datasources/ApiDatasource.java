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
    Boolean notNeedProducts();
    Date getDate();
    int getPayment();
    String getComments();

    void setName(String name);
    void setAddress1(String address1);
    void setAddress2(String address2);
    void setCP(String cp);
    void setEmail(String email);
    void setPhone(String phone);
    void setService(int service);
    void setNotNeedProducts(boolean set);
    void setDate(Date date);
    void setPayment(int payment);
    void setComments(String comments);



}
