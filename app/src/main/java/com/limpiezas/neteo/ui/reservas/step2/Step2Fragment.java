package com.limpiezas.neteo.ui.reservas.step2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

    @BindView(R.id.lv_servicios)
    ListView lv_servicios;

    @BindView(R.id.btn_next)
    AppCompatTextView btn_next;

    @BindView(R.id.btn_prev)
    AppCompatTextView btn_prev;

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

        lv_servicios.setAdapter(new ArrayAdapter<>(getActivity(),
                R.layout.item_selectable,
                //android.R.layout.simple_list_item_single_choice,
                android.R.id.text1,
                getResources().getStringArray(R.array.clean_service_type)));


        presenter = new Step2Presenter(this,parent);
        presenter.init();

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
        lv_servicios.setSelection(service);
        lv_servicios.setItemChecked(service,true);
    }
}
