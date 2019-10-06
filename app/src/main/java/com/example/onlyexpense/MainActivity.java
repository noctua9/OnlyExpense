package com.example.onlyexpense;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText editText5, editText4;
    Button button_add;
    Button viewButton;
    Button delButton;
    Button open;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    ListView userlist;

    ImageView imageview;

    Bitmap bitmap;
    private static final int select_picture = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);
        imageview=(ImageView)findViewById(R.id.imageView);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        button_add = (Button) findViewById(R.id.button_add);
        open = (Button) findViewById(R.id.camera);
        viewButton = (Button) findViewById(R.id.viewButton);
        listItem = new ArrayList<>();
        

        AddData();


        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openView();
                
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent img = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        startActivityForResult(img, select_picture);
                                    }
                                }
        );
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        bitmap = (Bitmap)data.getExtras().get("data");
        imageview.setImageBitmap(bitmap);

    }


    public void openView(){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }




    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }













  

    
    
    
    

    
    




    public void AddData(){
        open.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         byte[] input;
                        ByteArrayOutputStream stream=new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                        input=stream.toByteArray();
                        Boolean isInserted = mydb.insertData(editText4.getText().toString(), editText5.getText().toString(),input);
                        if (isInserted = true)

                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();

                        else
                            Toast.makeText(MainActivity.this, "NOT inserted", Toast.LENGTH_SHORT).show();



                    }
                }



        );

    }
}
