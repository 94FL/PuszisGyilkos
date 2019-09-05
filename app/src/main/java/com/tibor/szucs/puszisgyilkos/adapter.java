package com.tibor.szucs.puszisgyilkos;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class adapter extends BaseAdapter {
    ArrayList<nev> nevList;
    Context context;
    public adapter(Context context, ArrayList<nev> nev) {
        this.nevList = nev;
        this.context = context;
    }

    public class ViewHolder {
        public TextInputEditText txt;
        public ImageButton del;
    }

    @Override
    public int getCount() {
        return nevList.size();
    }

    @Override
    public nev getItem(int position) {
        return nevList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void updateList(ArrayList<nev> list) {
        nevList = list;
        notifyDataSetChanged();
    }
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // Get the data item for this position
        ViewHolder viewHolder = null;
        final nev neve = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        LayoutInflater ly = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = ly.inflate(R.layout.resztvevo_row, parent, false);
            viewHolder.txt = (TextInputEditText) convertView.findViewById(R.id.txt);
            viewHolder.del = (ImageButton) convertView.findViewById(R.id.deleteButton);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nevList.remove(position);
                notifyDataSetChanged();

            }
        });
        viewHolder.txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                neve.setNev(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        // Populate the data into the template view using the data object
        // Return the completed view to render on screen
        return convertView;
    }
}