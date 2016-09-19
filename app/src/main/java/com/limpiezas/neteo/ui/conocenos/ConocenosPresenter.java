package com.limpiezas.neteo.ui.conocenos;

import com.limpiezas.neteo.ui.App;
import com.limpiezas.neteo.R;

/**
 * Created by alavpa on 3/8/16.
 */
public class ConocenosPresenter {

    ConocenosView view;
    public ConocenosPresenter(ConocenosView view){
        this.view = view;
    }

    public void init() {

    }

    public void onClickEmail() {

        if(view!=null){
            view.sendEmail(App.get().getString(R.string.email_subject),App.get().getString(R.string.conocenos_email));
        }
    }

    public void onClickTel() {
        if(view!=null){
            view.call(App.get().getString(R.string.conocenos_call));
        }
    }

    public void onClickFacebook() {
        view.openFacebook(App.get().getString(R.string.conocenos_facebook_url));
    }

    public void onClickTwitter() {
        view.openTwitter(App.get().getString(R.string.conocenos_twitter_url));
    }
}
