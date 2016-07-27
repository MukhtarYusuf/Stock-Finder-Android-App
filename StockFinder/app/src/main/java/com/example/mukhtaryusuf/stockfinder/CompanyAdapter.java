package com.example.mukhtaryusuf.stockfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mukhtaryusuf on 7/13/16.
 */

public class CompanyAdapter extends ArrayAdapter<Company> {

    Context context;
    int resource;
    ArrayList<Company> data = new ArrayList<>();

    public CompanyAdapter(Context context, int resource, int textViewResourceId, ArrayList<Company> data) {
        super(context, resource, textViewResourceId, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    //    public CompanyAdapter(Context context, int resource, ArrayList<Company> data) {
//        super(context, resource, data);
//
//        this.context = context;
//        this.resource = resource;
//        this.data = data;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        super.getView(position, convertView, parent);

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.company_row_test1, parent, false);

        TextView cSymbolTextView = (TextView) rowView.findViewById(R.id.company_symbol);
        TextView cNameTextView = (TextView) rowView.findViewById(R.id.company_name);
        TextView sValueTextView = (TextView) rowView.findViewById(R.id.stock_value);
        TextView pChangeTextView = (TextView) rowView.findViewById(R.id.percentage_change);

        cSymbolTextView.setText(data.get(position).getSymbol());
        cNameTextView.setText(data.get(position).getName());
        sValueTextView.setText(data.get(position).getPrice());
        pChangeTextView.setText(data.get(position).getNetChange());

        if(data.get(position).getNetChange().charAt(0) == '+')
            pChangeTextView.setTextColor(context.getResources().getColor(R.color.md_green_A700));
        else if(data.get(position).getNetChange().charAt(0) == '-')
            pChangeTextView.setTextColor(context.getResources().getColor(R.color.md_red_400));

//        TextView cNameTextView = (TextView) rowView.findViewById(R.id.company_name);
//        TextView sValueTextView = (TextView) rowView.findViewById(R.id.stock_value);
//
//        //Set TextViews Here
//        cNameTextView.setText(data.get(position).getName());
//        sValueTextView.setText(data.get(position).getPrice());


        return rowView;
    }
}
