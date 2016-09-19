package com.limpiezas.neteo.ui.menu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.limpiezas.neteo.ui.Navigator;
import com.limpiezas.neteo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alavpa on 3/8/16.
 */
public class MenuFragment extends Fragment implements MenuView{

    public static final String KEY_CURRENT_POSITION = "currentPosition";
    public static final int MENU_INICIO = 0;
    public static final int MENU_RESERVA = 1;
    public static final int MENU_SERVICIOS = 2;
    public static final int MENU_ECO = 3;
    public static final int MENU_GALERIA = 4;
    public static final int MENU_CONOCENOS = 5;



    @BindView(R.id.ll_items)
    public LinearLayout ll_items;

    private int mCurrentSelectedPosition;

    Navigator navigator;
    MenuPresenter presenter;

    MenuParentView parent;

    public static MenuFragment getInstance(int position){

        MenuFragment menuFragment = new MenuFragment();

        Bundle args = new Bundle();
        args.putInt(KEY_CURRENT_POSITION,position);
        menuFragment.setArguments(args);

        return menuFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parent = (MenuParentView) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        parent = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);

        mCurrentSelectedPosition = getArguments().getInt(KEY_CURRENT_POSITION);
        navigator = new Navigator();
        presenter = new MenuPresenter(this);
        presenter.init();
    }

    @Override
    public void loadItems(String[] items) {
        ll_items.removeAllViews();

        for(int i=0;i<items.length;i++){
            View v;
            switch (i){
                case MENU_INICIO:
                    v = createMenuItem(items[i],R.drawable.item_inicio,i == mCurrentSelectedPosition);
                    v.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            presenter.onClickInicio();
                        }
                    });
                    break;
                case MENU_RESERVA:
                    v = createMenuItem(items[i],R.drawable.item_reserva,i == mCurrentSelectedPosition);
                    v.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            presenter.onClickForm2();
                        }
                    });
                    break;
                case MENU_SERVICIOS:
                    v = createMenuItem(items[i],R.drawable.item_servicios,i == mCurrentSelectedPosition);
                    v.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            presenter.onClickServicios();
                        }
                    });
                    break;

                case MENU_ECO:
                    v = createMenuItem(items[i],R.drawable.item_eco,i == mCurrentSelectedPosition);
                    v.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            presenter.onClickEco();
                        }
                    });
                    break;

                case MENU_CONOCENOS:
                    v = createMenuItem(items[i],R.drawable.item_conocenos,i == mCurrentSelectedPosition);
                    v.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            presenter.onClickConocenos();
                        }
                    });
                    break;

                case MENU_GALERIA:
                    v = createMenuItem(items[i],R.drawable.item_galeria,i == mCurrentSelectedPosition);
                    v.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            presenter.onClickGaleria();
                        }
                    });
                    break;
            }
        }
    }

    @Override
    public void startInicio() {
        navigator.startInicio(getActivity());
    }

    @Override
    public void startServicios() {
        navigator.startServicios(getActivity());
    }

    @Override
    public void startEco() {
        navigator.startEco(getActivity());
    }

    @Override
    public void startConocenos() {
        navigator.startConocenos(getActivity());
    }

    @Override
    public int getMenuSelected() {
        return mCurrentSelectedPosition;
    }

    @Override
    public void startReservas2() {
        navigator.startReservas2(getActivity());
    }

    @Override
    public void startGaleria() {
        navigator.startGaleria(getActivity());
    }

    public View createMenuItem(String text,int drawable, boolean selected){
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.row_menuitem,ll_items,false);

        TextView tv_item = (TextView)v.findViewById(R.id.tv_item);
        tv_item.setText(text);

        if(selected){
            tv_item.setTextColor(getResources().getColor(R.color.white));
            tv_item.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }

        if(drawable>0){
            tv_item.setCompoundDrawablesWithIntrinsicBounds(drawable,0,0,0);
            tv_item.setCompoundDrawablePadding(50);
            tv_item.setGravity(Gravity.CENTER_VERTICAL);
        }

        ll_items.addView(v);

        return v;
    }

    @Override
    public void hideMenu() {
        if(parent!=null){
            parent.hideMenu();
        }
    }
}
