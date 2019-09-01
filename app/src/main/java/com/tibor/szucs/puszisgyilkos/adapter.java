package com.tibor.szucs.puszisgyilkos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class adapter extends ArrayAdapter<nev> {
    ArrayList<nev> nev;
    Context context;
    TextInputEditText txt;
    ImageButton del;
    public adapter(Context context, ArrayList<nev> nev) {
        super(context, 0, nev);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // Get the data item for this position
        ViewHolder viewHolder;
        final nev nev = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder((ImageButton)del);
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.resztvevo_row, parent, false);
        }

        TextInputEditText txt = (TextInputEditText) convertView.findViewById(R.id.txt);
        ImageButton del = (ImageButton) convertView.findViewById(R.id.deleteButton);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(position);
                notifyDataSetChanged();
            }
        });
        // Populate the data into the template view using the data object
        nev.setNev(txt.getText());
        // Return the completed view to render on screen
        return convertView;
    }
    @Override
    public nev getItem(int position) {
        return nev.get(position);
    }
}