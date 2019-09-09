package com.tibor.szucs.puszisgyilkos;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity implements Serializable {
    private Context context;
    private ArrayList<nev> intentData = new ArrayList<nev>();
    private ArrayList<nev> intentDataSize;
    private FloatingActionButton fab;
    private File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getParent() + "/BuziMappa/");
    Intent intent;
    myDB db;
    public MainActivity() {

        /*db = new myDB(this);
        Cursor cursor = db.selectRecords();
        if(intentData.size() < 1) {
            for (int i = 0; i < cursor.getCount(); i++) {
                nev test = new nev();
                test.setNev(cursor.getString(1));
                intentData.add(test);
                cursor.moveToNext();
            }
        }*/
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] perms = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
        int permsRequestCode = 200;
        requestPermissions(perms, permsRequestCode);

        intent = new Intent(this, UsersActivity.class);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        Button kezdesBtn = (Button) findViewById(R.id.kezdesButton);
        Button kepekBtn = (Button) findViewById(R.id.kepNyit);
        Button szabaly = (Button) findViewById(R.id.szabalyok);

        kepekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setDataAndType(Uri.parse(directory.getPath()), "resource/folder");
                startActivity(Intent.createChooser(intent, "Nyiss meg eggy fálylkeresőt"));
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(intent, 1);
            }
        });
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (directory.exists() && directory.isDirectory() && directory.listFiles().length > 0) {
                   for(File file : directory.listFiles()) {
                       file.delete();
                   }
                   intentData = new ArrayList<nev>();
                   intent.removeExtra("users");
                   db.removeAllRecords();
                }
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        if (intentData.size()!=0) {
            intent.putExtra("users", (ArrayList<nev>) intentData);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == 1) {
                ArrayList<nev> returnString = (ArrayList<nev>)data.getSerializableExtra("users");
                intentData = returnString;
                try {
                    for (nev retStr : returnString){
                        generateQR(retStr.getNev());
                    }
                } catch (WriterException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //renameToRandom(directory.list());
            }
        }
    }
    public void generateQR(String nev) throws WriterException, IOException {
        QRCodeWriter qrw = new QRCodeWriter();
        EnumMap<EncodeHintType, Object> hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.MARGIN, 2);
        BitMatrix bit = qrw.encode(nev, BarcodeFormat.QR_CODE, 256,256, hints);

        int height = bit.getHeight();
        int width = bit.getWidth();

        final Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                bmp.setPixel(x, y, bit.get(x,y) ? Color.BLACK : Color.WHITE);
            }
        }

        if (!directory.exists()) {
            directory.mkdirs();
        }
        File mypath = new File(directory, Normalizer.normalize(nev, Normalizer.Form.NFD)+".jpg");
        if (! mypath.exists()) {
            mypath.createNewFile();
            FileOutputStream fout = new FileOutputStream(mypath);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fout);
            fout.flush();
            fout.close();
        }
        else {
            Toast.makeText(getApplicationContext(), "Néhány fájl má létezik", 1000);
        }
    }
    public void renameToRandom(String[] oldArray) {
        int n = oldArray.length;
        System.out.println(Arrays.asList(oldArray));
        File[] fileList = directory.listFiles();
        for (int i = 0; i < oldArray.length; i++) {
            int random = i + (int) (Math.random() * (n - i));
            String randomElement = oldArray[random];
            oldArray[random] = oldArray[i];
            oldArray[i] = randomElement;
        }
        System.out.println(Arrays.asList(oldArray));
        for (int i = 0; i < oldArray.length; i++) {
            File f = new File(directory.getAbsolutePath()+"/"+oldArray[i]);
            f.renameTo(fileList[i]);
        }
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults){
        switch(permsRequestCode){
            case 200:
                boolean READ_EXTERNAL_STORAGE = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean WRITE_EXTERNAL_STORAGE = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                break;
        }
    }
}
