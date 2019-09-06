package com.tibor.szucs.puszisgyilkos;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UsersActivity extends AppCompatActivity implements Serializable{
    static myDB mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        ArrayList<nev> temp = (ArrayList<nev>)getIntent().getSerializableExtra("users");
        final ArrayList<nev> arrayOfUsers = temp != null ? temp : new ArrayList<nev>();
        final adapter adapter = new adapter(this, arrayOfUsers);
        final ListView listView = (ListView) findViewById(R.id.lvItems_users);
        listView.setAdapter(adapter);
        FloatingActionButton fab = findViewById(R.id.fab_users);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nev e = new nev();
                arrayOfUsers.add(e);
                adapter.notifyDataSetChanged();
            }
        });
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
        FloatingActionButton fab2 = findViewById(R.id.fab_users2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (! arrayOfUsers.isEmpty()) {
                    Intent intent = new Intent();
                    intent.putExtra("users", (ArrayList<nev>) arrayOfUsers);
                    setResult(1, intent);
                    finish();
                } else if (arrayOfUsers.size() == new HashSet<nev>(arrayOfUsers).size()) {
                    Toast.makeText(getApplicationContext(), "Törűld mán ki a duplákat", 1000).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Adjá mán hozzá embereket", 1000).show();
                }
            }
        });
    }
}