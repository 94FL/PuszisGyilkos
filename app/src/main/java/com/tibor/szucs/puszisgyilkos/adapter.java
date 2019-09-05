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

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;
        final nev neve = getItem(position);
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
        final View convertViewF = convertView;
        viewHolder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nevList.remove(position);
                parent.removeViewInLayout(getView(position, convertViewF, parent));
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
                System.out.println(neve.getNev());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        viewHolder.txt.requestFocus();
        return convertView;
    }
}