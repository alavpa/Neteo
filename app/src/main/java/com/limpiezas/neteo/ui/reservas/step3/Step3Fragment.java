package com.limpiezas.neteo.ui.reservas.step3;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;

import com.limpiezas.neteo.R;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alavpa on 5/8/16.
 */
public class Step3Fragment extends Fragment implements Step3View{

    @BindView(R.id.lv_payment)
    ListView lv_payment;

    @BindView(R.id.et_comments)
    AppCompatEditText et_comments;

    @BindView(R.id.et_date)
    AppCompatEditText et_date;

    @BindView(R.id.et_time)
    AppCompatEditText et_time;

    @BindView(R.id.btn_reservar)
    AppCompatTextView btn_reservar;

    @BindView(R.id.btn_prev)
    AppCompatTextView btn_prev;

    Step3Presenter presenter;
    Step3ParentView parent;

    DatePickerDialog dateDialog;
    TimePickerDialog timeDialog;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parent = (Step3ParentView)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        parent = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_step3,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateDialog.show();
            }
        });

        et_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeDialog.show();
            }
        });

        lv_payment.setAdapter(new ArrayAdapter<>(getActivity(),
                R.layout.item_selectable,
                android.R.id.text1,
                getResources().getStringArray(R.array.payment_type)));

        btn_reservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.reservar();
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onPrev();
            }
        });

        presenter = new Step3Presenter(this,parent);
        presenter.init();

    }

    @Override
    public void setTime(String time) {
        et_time.setText(time);
    }

    @Override
    public void setDate(String date) {
        et_date.setText(date);
    }

    @Override
    public int getPayment() {
        return lv_payment.getCheckedItemPosition();
    }

    @Override
    public void setPayment(int payment) {
        lv_payment.setSelection(payment);
        lv_payment.setItemChecked(payment,true);
    }

    @Override
    public void initDateDialogs(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        dateDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                presenter.setDate(year,month,day);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        timeDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                presenter.setTime(hour,minute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
    }

    @Override
    public String getComments() {
        return et_comments.getText().toString();
    }

    @Override
    public void setComments(String comments) {
        et_comments.setText(comments);
    }

}
