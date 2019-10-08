package com.mechanic.autorepair;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import es.dmoral.toasty.Toasty;

public class Model_Info extends AppCompatActivity implements View.OnClickListener {
TextView textView,red,blue,black,white,orange,grey,silver,yellow,other,regno;
ImageView imageView;
EditText editText,plate_no;
String select,getColor,getNo;
Button add;
int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model__info);
        textView = findViewById(R.id.textViewTitle);
        imageView = findViewById(R.id.imageView);
        Intent intent =getIntent();
        String extraname = intent.getStringExtra("title");
        String extraimage = intent.getStringExtra("image");
        red = findViewById(R.id.red);
        red.setOnClickListener(this);
        blue = findViewById(R.id.blue);
        blue.setOnClickListener(this);
        black = findViewById(R.id.black);
        black.setOnClickListener(this);
        white = findViewById(R.id.white);
        white.setOnClickListener(this);
        orange = findViewById(R.id.orange);
        orange.setOnClickListener(this);
        grey = findViewById(R.id.grey);
        grey.setOnClickListener(this);
        silver = findViewById(R.id.silver);
        silver.setOnClickListener(this);
        yellow = findViewById(R.id.yellow);
        yellow.setOnClickListener(this);
        other = findViewById(R.id.other);
        other.setOnClickListener(this);
        editText = findViewById(R.id.color);
        editText.setOnClickListener(this);
        regno = findViewById(R.id.regno);
        plate_no = findViewById(R.id.plate_no);
        add = findViewById(R.id.add);

        textView.setText(extraname);
        Picasso.get().load(extraimage).into(imageView);




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getColor = editText.getText().toString();
                getNo = plate_no.getText().toString();

                if(getNo.isEmpty()){

                    Toasty.error(getApplicationContext(), "Please fill up the registration number or color", Toast.LENGTH_SHORT, true).show();

                }
                else{

                    Intent intent = new Intent(Model_Info.this,Selectionofservice.class);
                    intent.putExtra("getColor",getColor);
                    intent.putExtra("getNo",getNo);
                    intent.putExtra("select",select);

                    startActivity(intent);
                }
            }
        });










    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.red:
                // do your code
                if(i==0){

                editText.setVisibility(View.GONE);
                 select= (String) red.getText();
                 //red.setBackgroundColor(Color.RED);
                 red.setBackgroundResource(R.drawable.red);
                 red.setTextColor(Color.WHITE);
                 i=1;


}
                else if(i==1){



                    editText.setVisibility(View.GONE);

                    //red.setBackgroundColor(Color.RED);
                    red.setBackgroundResource(R.drawable.background_textview);
                    red.setTextColor(Color.BLACK);
                    i=0;

                }



                break;



            case R.id.blue:
                // do your code
                if(i==0) {
                    editText.setVisibility(View.GONE);
                    select = (String) blue.getText();
                    blue.setBackgroundResource(R.drawable.blue);
                    blue.setTextColor(Color.WHITE);
                    i = 1;
                }
                else if(i==1){

                    editText.setVisibility(View.GONE);

                    //red.setBackgroundColor(Color.RED);
                    blue.setBackgroundResource(R.drawable.background_textview);
                    blue.setTextColor(Color.BLACK);
                    i=0;

                }

                break;

            case R.id.black:
                // do your code
                if(i==0) {
                    editText.setVisibility(View.GONE);
                    select = (String) black.getText();
                    black.setBackgroundResource(R.drawable.black);
                    black.setTextColor(Color.WHITE);
                    i = 1;
                }
                else if(i==1){
                    editText.setVisibility(View.GONE);

                    //red.setBackgroundColor(Color.RED);
                    black.setBackgroundResource(R.drawable.background_textview);
                    black.setTextColor(Color.BLACK);
                    i=0;



                }

                break;
             case R.id.white:
                // do your code
                 editText.setVisibility(View.GONE);
                 select= (String) white.getText();
                break;

            case R.id.orange:
                // do your code
                if(i==0) {
                    editText.setVisibility(View.GONE);
                    select = (String) orange.getText();
                    orange.setBackgroundResource(R.drawable.orange);
                    orange.setTextColor(Color.WHITE);
                    i = 1;
                }
                else if(i==1){
                    editText.setVisibility(View.GONE);

                    //red.setBackgroundColor(Color.RED);
                    orange.setBackgroundResource(R.drawable.background_textview);
                    orange.setTextColor(Color.BLACK);
                    i=0;



                }
                break;


                case R.id.grey:
                // do your code
                    if(i==0) {
                        editText.setVisibility(View.GONE);
                        select = (String) grey.getText();
                        grey.setBackgroundResource(R.drawable.grey);
                        grey.setTextColor(Color.WHITE);
                        i = 1;
                    }
                    else if(i==1){
                        editText.setVisibility(View.GONE);

                        //red.setBackgroundColor(Color.RED);
                        grey.setBackgroundResource(R.drawable.background_textview);
                        grey.setTextColor(Color.BLACK);
                        i=0;



                    }
                break;

            case R.id.silver:
                // do your code
                if(i==0) {
                    editText.setVisibility(View.GONE);
                    select = (String) silver.getText();
                    black.setBackgroundResource(R.drawable.silver);
                    silver.setTextColor(Color.WHITE);
                    i = 1;
                }
                else if(i==1){
                    editText.setVisibility(View.GONE);

                    //red.setBackgroundColor(Color.RED);
                    silver.setBackgroundResource(R.drawable.background_textview);
                    silver.setTextColor(Color.BLACK);
                    i=0;



                }
                break;
            case R.id.yellow:
                // do your code
                if(i==0) {
                    editText.setVisibility(View.GONE);
                    select = (String) yellow.getText();
                    yellow.setBackgroundResource(R.drawable.yellow);
                    yellow.setTextColor(Color.WHITE);
                    i = 1;
                }
                else if(i==1){
                    editText.setVisibility(View.GONE);

                    //red.setBackgroundColor(Color.RED);
                    yellow.setBackgroundResource(R.drawable.background_textview);
                    yellow.setTextColor(Color.BLACK);
                    i=0;



                }
                break;


            case R.id.other:
                // do your code
                if(i==0) {
                    editText.setVisibility(View.VISIBLE);
                    select = (String) other.getText();
                    i=1;
                }
                else if(i==1){
                    editText.setVisibility(View.GONE);

                }

                break;



            default:
                break;
        }
        Log.i("select",select);
    }
}
