package com.limpiezas.neteo.ui;

import android.content.Context;
import android.content.Intent;

import com.limpiezas.neteo.ui.conocenos.ConocenosActivity;
import com.limpiezas.neteo.ui.eco.EcoActivity;
import com.limpiezas.neteo.ui.galeria.GaleriaActivity;
import com.limpiezas.neteo.ui.inicio.InicioActivity;

import com.limpiezas.neteo.ui.reservas.Reservas2Activity;
import com.limpiezas.neteo.ui.servicios.ServiciosActivity;

/**
 * Created by alavpa on 3/8/16.
 */
public class Navigator {

    public Intent getIntent(Context ctx,Class classe){
        return new Intent(ctx,classe);
    }

    public void startInicio(Context ctx){
        if(ctx!=null) {
            ctx.startActivity(getIntent(ctx, InicioActivity.class));
        }
    }


    public void startServicios(Context ctx){
        if(ctx!=null) {
            ctx.startActivity(getIntent(ctx, ServiciosActivity.class));
        }
    }

    public void startEco(Context ctx){
        if(ctx!=null) {
            ctx.startActivity(getIntent(ctx, EcoActivity.class));
        }
    }

    public void startConocenos(Context ctx){
        if(ctx!=null) {
            ctx.startActivity(getIntent(ctx, ConocenosActivity.class));
        }
    }

    public void startReservas2(Context ctx) {
        if(ctx!=null) {
            ctx.startActivity(getIntent(ctx, Reservas2Activity.class));
        }
    }

    public void startGaleria(Context ctx) {
        if(ctx!=null) {
            ctx.startActivity(getIntent(ctx, GaleriaActivity.class));
        }
    }
}
