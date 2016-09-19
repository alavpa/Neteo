package com.limpiezas.neteo.data;

import com.limpiezas.neteo.data.datasources.ApiDatasource;
import com.limpiezas.neteo.data.model.Form;

import java.util.Date;

/**
 * Created by alavpa on 3/8/16.
 */
public class FormRepository {

    ApiDatasource datasource;

    public FormRepository(ApiDatasource datasource){
        this.datasource = datasource;
    }

    public Form getForm(){
        Form form = new Form();
        form.setName(datasource.getName());
        form.setAddress1(datasource.getAddress1());
        form.setAddress2(datasource.getAddress2());
        form.setCP(datasource.getCP());
        form.setEmail(datasource.getEmail());
        form.setPhone(datasource.getPhone());
        form.setService(datasource.getService());
        form.setNot_products(datasource.notNeedProducts());
        form.setDate(datasource.getDate());
        form.setPayment(datasource.getPayment());
        form.setComments(datasource.getComments());
        return form;
    }

    public void saveForm(Form form){
        saveForm(form.getName(),
                form.getAddress1(),
                form.getAddress2(),
                form.getCP(),
                form.getEmail(),
                form.getPhone(),
                form.getService(),
                form.isNot_products(),
                form.getDate(),
                form.getPayment(),
                form.getComments());
    }

    public void saveForm(String name,
                        String address1,
                        String address2,
                        String cp,
                        String email,
                        String phone,
                        int service,
                        boolean notNeedProducts,
                        Date date,
                        int payment,
                         String comments){
        datasource.setName(name);
        datasource.setAddress1(address1);
        datasource.setAddress2(address2);
        datasource.setCP(cp);
        datasource.setEmail(email);
        datasource.setPhone(phone);
        datasource.setService(service);
        datasource.setNotNeedProducts(notNeedProducts);
        datasource.setDate(date);
        datasource.setPayment(payment);
        datasource.setComments(comments);
    }
}
