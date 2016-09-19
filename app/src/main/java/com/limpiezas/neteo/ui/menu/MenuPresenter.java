package com.limpiezas.neteo.ui.menu;

import com.limpiezas.neteo.ui.App;
import com.limpiezas.neteo.R;

/**
 * Created by alavpa on 3/8/16.
 */
public class MenuPresenter {

    MenuView view;
    public MenuPresenter(MenuView view){
        this.view = view;
    }

    public void init(){
        view.loadItems(App.get().getResources().getStringArray(R.array.menu));
    }

    public void onClickInicio() {
        view.startInicio();
        view.hideMenu();
    }

    public void onClickServicios() {
        view.startServicios();
        view.hideMenu();
    }

    public void onClickEco() {
        view.startEco();
        view.hideMenu();
    }

    public void onClickConocenos() {
        view.startConocenos();
        view.hideMenu();
    }

    public void onClickForm2() {
        view.startReservas2();
        view.hideMenu();
    }

    public void onClickGaleria() {
        view.startGaleria();
        view.hideMenu();
    }
}
