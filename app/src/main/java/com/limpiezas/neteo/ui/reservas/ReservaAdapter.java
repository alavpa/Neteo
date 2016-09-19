package com.limpiezas.neteo.ui.reservas;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.limpiezas.neteo.ui.reservas.step1.Step1Fragment;
import com.limpiezas.neteo.ui.reservas.step2.Step2Fragment;
import com.limpiezas.neteo.ui.reservas.step3.Step3Fragment;

/**
 * Created by alavpa on 8/8/16.
 */
public class ReservaAdapter extends FragmentStatePagerAdapter {

    Step1Fragment step1;
    Step2Fragment step2;
    Step3Fragment step3;
    public ReservaAdapter(FragmentManager fm, Step1Fragment step1, Step2Fragment step2, Step3Fragment step3) {
        super(fm);
        this.step1 = step1;
        this.step2 = step2;
        this.step3 = step3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return step1;
            case 1:
                return step2;
            case 2:
                return step3;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
