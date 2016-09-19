package com.limpiezas.neteo.ui.conocenos;

/**
 * Created by alavpa on 3/8/16.
 */
public interface ConocenosView {

    void sendEmail(String subject, String to);
    void call(String tel);

    void openFacebook(String url);

    void openTwitter(String url);
}
