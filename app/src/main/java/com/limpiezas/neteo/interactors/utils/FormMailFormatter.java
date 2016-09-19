package com.limpiezas.neteo.interactors.utils;

import android.text.TextUtils;

import com.limpiezas.neteo.R;
import com.limpiezas.neteo.data.model.Form;
import com.limpiezas.neteo.ui.App;

import java.text.SimpleDateFormat;
import java.util.Locale;

import rx.Observable;
import rx.functions.Func0;

/**
 * Created by alavpa on 17/8/16.
 */
public class FormMailFormatter {

    Form form;

    public FormMailFormatter(Form form) {
        this.form = form;
    }

    public Observable<String> execute(){

        return Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {

                try {
                    SimpleDateFormat df =
                            new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy ' a las ' HH:mm", new Locale("es","ES"));

                    StringBuilder builder = new StringBuilder();
                    builder.append("RESERVA");
                    builder.append("\n\n");

                    if (!TextUtils.isEmpty(form.getName().trim())) {
                        builder.append("Nombre: ");
                        builder.append("\n");
                        builder.append(form.getName());
                        builder.append("\n");
                        builder.append("\n");
                    }

                    if (!TextUtils.isEmpty(form.getAddress1().trim())) {
                        builder.append("Dirección linea 1: ");
                        builder.append("\n");
                        builder.append(form.getAddress1());
                        builder.append("\n");
                        builder.append("\n");
                    }

                    if (!TextUtils.isEmpty(form.getAddress2().trim())) {
                        builder.append("Dirección linea 2: ");
                        builder.append("\n");
                        builder.append(form.getAddress2());
                        builder.append("\n");
                        builder.append("\n");
                    }

                    builder.append("Código Postal: ");
                    builder.append("\n");
                    builder.append(form.getCP());
                    builder.append("\n");
                    builder.append("\n");

                    if (!TextUtils.isEmpty(form.getEmail().trim())) {
                        builder.append("Correo Electrónico: ");
                        builder.append("\n");
                        builder.append(form.getEmail());
                        builder.append("\n");
                        builder.append("\n");
                    }

                    if (!TextUtils.isEmpty(form.getPhone().trim())) {
                        builder.append("Teléfono: ");
                        builder.append("\n");
                        builder.append(form.getPhone());
                        builder.append("\n");
                        builder.append("\n");
                    }

                    builder.append("Servicio: ");
                    builder.append("\n");
                    builder.append(App.get().getResources().getStringArray(R.array.clean_service_type)[form.getService()]);
                    builder.append("\n");
                    builder.append("\n");

                    builder.append("Tipo: ");
                    builder.append("\n");
                    builder.append(App.get().getResources().getStringArray(R.array.clean_type)[form.getType()]);
                    builder.append("\n");
                    builder.append("\n");

                    builder.append("¿Necesita productos de limpieza?");
                    builder.append("\n");
                    if (form.isNot_products()) {
                        builder.append("No");
                    } else {
                        builder.append("Sí");
                    }
                    builder.append("\n");
                    builder.append("\n");

                    builder.append("Fecha: ");
                    builder.append("\n");
                    builder.append(df.format(form.getDate()));
                    builder.append("\n");
                    builder.append("\n");

                    builder.append("Método de pago: ");
                    builder.append("\n");
                    builder.append(App.get().getResources().getStringArray(R.array.payment_type)[form.getPayment()]);
                    builder.append("\n");
                    builder.append("\n");

                    builder.append("\n");
                    builder.append("\n");
                    builder.append("Un saludo.");
                    builder.append("\n");
                    builder.append("\n");
                    builder.append("Limpiezas Néteo");
                    builder.append("\n");
                    builder.append("Email: limpiezasneteo@gmail.com");
                    builder.append("\n");
                    builder.append("Tlfno.: 625 55 29 77");
                    builder.append("\n");
                    builder.append("\n");

                    return Observable.just(builder.toString());

                }catch (Throwable e){
                    return Observable.error(e);
                }



            }
        });
    }
}
