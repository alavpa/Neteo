package com.limpiezas.neteo.ui;

import android.app.ProgressDialog;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.limpiezas.neteo.R;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by alavpa on 8/7/16.
 */
public class BaseActivity extends AppCompatActivity implements BaseView {

    CompositeSubscription subscriptions = new CompositeSubscription();
    ProgressDialog progressDialog = null;

    @Override
    public void showError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void startLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.cargando));
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }
    }

    @Override
    public void stopLoading() {
        if(progressDialog!=null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void addSubscription(Subscription subscription){
        subscriptions.add(subscription);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscriptions.unsubscribe();
    }
}
