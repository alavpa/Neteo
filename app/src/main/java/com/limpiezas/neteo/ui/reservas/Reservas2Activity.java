package com.limpiezas.neteo.ui.reservas;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.limpiezas.neteo.R;
import com.limpiezas.neteo.data.model.Form;
import com.limpiezas.neteo.ui.BaseActivity;
import com.limpiezas.neteo.ui.reservas.step1.Step1Fragment;
import com.limpiezas.neteo.ui.reservas.step1.Step1ParentView;
import com.limpiezas.neteo.ui.reservas.step2.Step2Fragment;
import com.limpiezas.neteo.ui.reservas.step2.Step2ParentView;
import com.limpiezas.neteo.ui.reservas.step3.Step3Fragment;
import com.limpiezas.neteo.ui.reservas.step3.Step3ParentView;

import butterknife.BindView;

/**
 * Created by alavpa on 5/8/16.
 */
public class Reservas2Activity extends BaseActivity implements Reservas2View,
        Step1ParentView,
        Step2ParentView,
        Step3ParentView{


    Step1Fragment step1 = new Step1Fragment();
    Step2Fragment step2 = new Step2Fragment();
    Step3Fragment step3 = new Step3Fragment();

    Reservas2Presenter presenter;
    ReservaAdapter adapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.pager)
    ViewPager pager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas2);

        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                presenter.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        presenter = new Reservas2Presenter(this,
                step1,
                step2,
                step3);

        presenter.init();
    }



    public void initView(){

        adapter = new ReservaAdapter(getSupportFragmentManager(),step1,step2,step3);
        pager.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(pager.getCurrentItem()==0) {
            super.onBackPressed();
        }else{
            pager.setCurrentItem(pager.getCurrentItem()-1,true);
        }
    }

    @Override
    public void moveToPage(int page) {
        pager.setCurrentItem(page,true);
    }

    @Override
    public int getCurrentPage() {
        return pager.getCurrentItem();
    }

    @Override
    public void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public Form getForm() {
        return presenter.getForm();
    }

    @Override
    public void goToStep1() {
        pager.setCurrentItem(0);
    }

    @Override
    public void goToStep3() {
        pager.setCurrentItem(2);
    }

    @Override
    public void goToStep2() {
        pager.setCurrentItem(1);
    }


}
