package com.tibor.szucs.puszisgyilkos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter extends ArrayAdapter<nev> {
    public adapter(Context context, ArrayList<nev> nev) {
        super(context, 0, nev);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        nev nev = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.resztvevo_row, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.textViewName);
        // Populate the data into the template view using the data object
        tvName.setText(nev.getNev());
        // Return the completed view to render on screen
        return convertView;
    }
}