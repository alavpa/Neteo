package com.limpiezas.neteo.ui.reservas.step2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.limpiezas.neteo.R;
import com.limpiezas.neteo.ui.App;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alavpa on 19/9/16.
 */
public class ServicesAdapter extends BaseAdapter {
    private String[] services;
    private String[] prices;
    public ServicesAdapter(Context context, String[] services, String[] prices) {

        this.services =services;
        this.prices = prices;
    }

    @Override
    public int getCount() {
        return services.length;
    }

    @Override
    public Object getItem(int i) {
        return services[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ServiceViewHolder holder;
        if(convertView==null) {
            convertView = LayoutInflater.from(App.get())
                    .inflate(R.layout.item2_selectable, parent, false);
            holder = new ServiceViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder = (ServiceViewHolder) convertView.getTag();
        holder.bind(services[position],prices[position]);

        return convertView;
    }

    public String[] getServices() {
        return services;
    }

    public void setServices(String[] services) {
        this.services = services;
    }

    public String[] getPrices() {
        return prices;
    }

    public void setPrices(String[] prices) {
        this.prices = prices;
    }

    public static class ServiceViewHolder{
        @BindView(android.R.id.text1)
        TextView text1;
        @BindView(android.R.id.text2)
        TextView text2;

        public ServiceViewHolder(View view){
            ButterKnife.bind(this,view);
        }

        public void bind(String service, String price){
            text1.setText(service);
            text2.setText(price);
        }
    }
}
