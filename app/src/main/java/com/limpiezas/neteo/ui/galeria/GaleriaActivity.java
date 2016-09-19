package com.limpiezas.neteo.ui.galeria;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.limpiezas.neteo.R;
import com.limpiezas.neteo.ui.BaseToolbarActivity;
import com.limpiezas.neteo.ui.menu.MenuFragment;

/**
 * Created by alavpa on 3/8/16.
 */
public class GaleriaActivity extends BaseToolbarActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        initMenu(MenuFragment.MENU_GALERIA);
    }
}
