package com.limpiezas.neteo.data.model;

import java.util.Date;

/**
 * Created by alavpa on 3/8/16.
 */
public class Form {
    private String name = "";
    private String address1 = "";
    private String address2 = "";
    private String cp = "";
    private String email = "";
    private String phone = "";
    private int service = -1;
    private boolean not_products = false;
    private Date date = new Date();
    private int payment = -1;
    private String comments = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCP() {
        return cp;
    }

    public void setCP(String cp) {
        this.cp = cp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public boolean isNot_products() {
        return not_products;
    }

    public void setNot_products(boolean not_products) {
        this.not_products = not_products;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
