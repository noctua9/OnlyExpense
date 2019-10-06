package com.example.onlyexpense;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.onlyexpense.R;

import java.util.ArrayList;
import java.util.List;


public class ListActivity extends AppCompatActivity {



    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListView listView = (ListView) findViewById(R.id.listView);
        mydb = new DatabaseHelper(this);


        ArrayList<String> theList = new ArrayList<>();
        Cursor cursor = mydb.getAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data to Show", Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor.moveToNext()) {
                theList.add(cursor.getString(1));
                theList.add(cursor.getString(2));
            }
            ListAdapter listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, theList);
            listView.setAdapter(listAdapter);
        }




    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
