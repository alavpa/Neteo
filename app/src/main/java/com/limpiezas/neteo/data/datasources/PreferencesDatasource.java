package com.limpiezas.neteo.data.datasources;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.limpiezas.neteo.ui.App;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by alavpa on 3/8/16.
 */
public class PreferencesDatasource implements ApiDatasource{

    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.get());

    private String getString(String key, String defaultValue){
        return  sharedPreferences.getString(key,defaultValue);
    }

    private int getInt(String key, int defaultValue){
        return  sharedPreferences.getInt(key,defaultValue);
    }

    private boolean getBoolean(String key, boolean defaultValue){
        return  sharedPreferences.getBoolean(key,defaultValue);
    }

    private long getLong(String key, long defaultValue){
        return  sharedPreferences.getLong(key,defaultValue);
    }

    private void setString(String key, String value){
        sharedPreferences.edit().putString(key,value).apply();
    }

    private void setInt(String key, int value){
        sharedPreferences.edit().putInt(key,value).apply();
    }

    private void setBoolean(String key, boolean value){
        sharedPreferences.edit().putBoolean(key,value).apply();
    }

    private void setLong(String key, long value){
        sharedPreferences.edit().putLong(key,value).apply();
    }


    @Override
    public String getName() {
        return getString("name","");
    }

    @Override
    public String getAddress1() {
        return getString("address1","");
    }

    @Override
    public String getAddress2() {
        return getString("address2","");
    }

    @Override
    public String getCP() {
        return getString("cp","");
    }

    @Override
    public String getEmail() {
        return getString("email","");
    }

    @Override
    public String getPhone() {
        return getString("phone","");
    }

    @Override
    public int getService() {
        return getInt("service",-1);
    }

    @Override
    public Boolean notNeedProducts() {
        return getBoolean("notNeedProducts",false);
    }

    @Override
    public Date getDate() {
        Calendar calDefault = Calendar.getInstance();
        calDefault.add(Calendar.HOUR,25);
        //long current = getLong("date",calDefault.getTimeInMillis());
        //Calendar calendar = Calendar.getInstance();
        //calendar.setTimeInMillis(current);
        return calDefault.getTime();
    }

    @Override
    public int getPayment() {
        return getInt("payment",-1);
    }

    @Override
    public String getComments() {
        return getString("comments","");
    }

    @Override
    public void setName(String name) {
        setString("name",name);
    }

    @Override
    public void setAddress1(String address1) {
        setString("address1",address1);
    }

    @Override
    public void setAddress2(String address2) {

        setString("address2",address2);
    }

    @Override
    public void setCP(String cp) {

        setString("cp",cp);
    }

    @Override
    public void setEmail(String email) {

        setString("email",email);
    }

    @Override
    public void setPhone(String phone) {

        setString("phone",phone);
    }

    @Override
    public void setService(int service) {

        setInt("service",service);
    }

    @Override
    public void setNotNeedProducts(boolean set) {
        setBoolean("notNeedProducts",set);
    }

    @Override
    public void setDate(Date date) {

        setLong("date",date.getTime());
    }

    @Override
    public void setPayment(int payment) {

        setInt("payment",payment);
    }

    @Override
    public void setComments(String comments) {
        setString("comments",comments);
    }
}
