package com.limpiezas.neteo.ui.reservas.step1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.limpiezas.neteo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alavpa on 5/8/16.
 */
public class Step1Fragment extends Fragment implements Step1View{

    @BindView(R.id.et_name)
    AppCompatEditText et_name;

    @BindView(R.id.et_address_line1)
    AppCompatEditText et_address_line1;

    @BindView(R.id.et_address_line2)
    AppCompatEditText et_address_line2;

    @BindView(R.id.et_cp)
    AppCompatEditText et_cp;

    @BindView(R.id.et_contact)
    AppCompatEditText et_contact;

    @BindView(R.id.sp_contact)
    Spinner sp_contact;

    @BindView(R.id.btn_next)
    AppCompatTextView btn_next;

    Step1Presenter presenter;
    Step1ParentView parent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parent = (Step1ParentView)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        parent = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_step1,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        presenter = new Step1Presenter(this,parent);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,
                android.R.id.text1,
                getActivity().getResources().getStringArray(R.array.contact_type));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_contact.setAdapter(adapter);

        sp_contact.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.onContactChange(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        et_contact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                presenter.onContactValueChanged(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onNext();
            }
        });
        presenter.init();


    }

    @Override
    public void setName(String name) {
        et_name.setText(name);
    }

    @Override
    public void setAddressLine1(String address1) {
        et_address_line1.setText(address1);
    }

    @Override
    public void setAddressLine2(String address2) {
        et_address_line2.setText(address2);
    }

    @Override
    public void setCP(String cp) {
        et_cp.setText(cp);
    }

    @Override
    public void setContact(int pos, String contact) {
        sp_contact.setSelection(pos);
        et_contact.setText(contact);
    }

    @Override
    public String getName() {
        return et_name.getText().toString();
    }

    @Override
    public String getAddress1() {
        return et_address_line1.getText().toString();
    }

    @Override
    public String getAddress2() {
        return et_address_line2.getText().toString();
    }

    @Override
    public String getCP() {
        return et_cp.getText().toString();
    }

    @Override
    public String getContact() {
        return et_contact.getText().toString();
    }

    @Override
    public void setContactHintInputTypeValue(String hint,int inputType, String value) {
        et_contact.setHint(hint + "*");
        et_contact.setInputType(inputType);
        et_contact.setText(value);
    }

    @Override
    public int getContactSelected() {
        return sp_contact.getSelectedItemPosition();
    }
}
