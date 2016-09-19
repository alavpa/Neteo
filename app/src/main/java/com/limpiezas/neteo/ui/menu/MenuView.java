package com.limpiezas.neteo.ui.menu;

/**
 * Created by alavpa on 3/8/16.
 */
public interface MenuView {
    void hideMenu();
    void loadItems(String[] items);

    void startInicio();

    void startServicios();

    void startEco();

    void startConocenos();

    int getMenuSelected();

    void startReservas2();

    void startGaleria();
}
