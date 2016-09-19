package com.limpiezas.neteo.ui.reservas.step2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.limpiezas.neteo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alavpa on 5/8/16.
 */
public class Step2Fragment extends Fragment implements Step2View{

    Step2Presenter presenter;
    Step2ParentView parent;

    @BindView(R.id.chk_clean_products)
    AppCompatCheckBox chk_clean_products;

    @BindView(R.id.lv_servicios)
    ListView lv_servicios;

    @BindView(R.id.btn_next)
    AppCompatTextView btn_next;

    @BindView(R.id.btn_prev)
    AppCompatTextView btn_prev;

    ServicesAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parent = (Step2ParentView)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        parent = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_step2,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        presenter = new Step2Presenter(this,parent);
        presenter.init();

        chk_clean_products.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                presenter.onCheckedChanged(b);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onNext();
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onPrev();
            }
        });

    }


    @Override
    public int getServiceSelected() {
        return lv_servicios.getCheckedItemPosition();
    }

    @Override
    public void selectService(int service) {

        if(service<getResources().getStringArray(R.array.servicios).length) {
            lv_servicios.setSelection(service);
            lv_servicios.setItemChecked(service, true);
        }
    }

    @Override
    public boolean getNotCleaningProducts() {
        return chk_clean_products.isChecked();
    }

    @Override
    public void setNotCleaningProducts(boolean notneed) {
        chk_clean_products.setChecked(notneed);
    }

    @Override
    public void setList(boolean notneed) {

        if(adapter==null) {
            if (notneed) {
                adapter = new ServicesAdapter(getActivity(),
                        getResources().getStringArray(R.array.servicios),
                        getResources().getStringArray(R.array.precios_con_producto));
            } else {
                adapter = new ServicesAdapter(getActivity(),
                        getResources().getStringArray(R.array.servicios),
                        getResources().getStringArray(R.array.precios_sin_producto));
            }
            lv_servicios.setAdapter(adapter);
        }else{
            if(notneed){
                adapter.setPrices(getResources().getStringArray(R.array.precios_con_producto));
            }else{
                adapter.setPrices(getResources().getStringArray(R.array.precios_sin_producto));
            }
            adapter.notifyDataSetChanged();
        }




    }
}
