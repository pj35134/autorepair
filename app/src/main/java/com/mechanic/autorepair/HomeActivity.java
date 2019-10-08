package com.mechanic.autorepair;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    private static final String URL_READ ="http://192.168.1.69/android_register_login/read_detail.php" ;
    private static final String URL_EDIT = "http://192.168.1.69/android_register_login/edit_detail.php";
    private static final String URL_UPLOAD ="http://192.168.1.69/android_register_login/upload.php" ;
    TextView name ,email;
Button logout,edit,editpic;
EditText editname,editemail;
ImageView pp;
Bitmap bitmap;
SharedPreferences preferences;
    String getid;
    Button upload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        logout=findViewById(R.id.logout);
        editname=findViewById(R.id.editname);
        editemail=findViewById(R.id.editemail);
        editpic=findViewById(R.id.editpic);
        pp = findViewById(R.id.pp);
        edit=findViewById(R.id.edit);
        upload = findViewById(R.id.upload);
        Intent intent = getIntent();
        preferences = getSharedPreferences("Userinfo", MODE_PRIVATE);
        String extraname = intent.getStringExtra("name");
        String extraemail = intent.getStringExtra("email");



        String getname = preferences.getString("username","");
        String getemail = preferences.getString("email","");
        getid = preferences.getString("id","");
        name.setText(getname);
        email.setText(getemail);

        getUserDetail();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.edit().putBoolean("rememberme", false).apply();
                finish();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosedata();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserDetail();
            }
        });

        editpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }



    private void getUserDetail(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");
                            
                            if(success.equals("1")){
                                for(int i=0;i<jsonArray.length();i++){
                                    
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    
                                    String strName = object.getString("name").trim();
                                    String strEmail = object.getString("email").trim();
                                    
                                    editname.setText(strName);
                                    editemail.setText(strEmail);
                                    
                                    
                                    
                                }
                                
                                
                                
                                
                                
                                
                            }
                            
                            
                            
                            
                            
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(HomeActivity.this, "error"+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HomeActivity.this, "error"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

        )
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id",getid);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    public void saveUserDetail(){
        final String getname = editname.getText().toString().trim();
        final String getemail = editemail.getText().toString().trim();
        final String idz = getid;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_EDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");


                            if(success.equals("1")){
                                        Toast.makeText(HomeActivity.this, "success", Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("username", getname);
                                editor.putString("email", getemail);
                                editor.putString("id", idz);
                                editor.apply();




                            }





                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(HomeActivity.this, "error"+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HomeActivity.this, "error"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

        )
        {



            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name",getname);
                params.put("email",getemail);
                params.put("id",idz);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);






    }

    private void choosedata(){



        Intent intent = new Intent();
        intent.setType("iamge/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select pictures"),1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data !=null && data.getData()!=null){
            Uri filepath = data.getData();


            try {


                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                pp.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }

            UploadPicture(getid,getStringImage(bitmap));
        }



    }

    private void UploadPicture(final String id,final String photo){

StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPLOAD,
        new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if(success.equals("1")){

                        Toast.makeText(HomeActivity.this, "success", Toast.LENGTH_SHORT).show();
                    }





                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(HomeActivity.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }


)
{
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Map<String,String> params = new HashMap<>();
        params.put("id",id);
        params.put("photo",photo);
        return params;
    }
};


RequestQueue requestQueue = Volley.newRequestQueue(this);
requestQueue.add(stringRequest);








    }


    public String getStringImage(Bitmap bitmap){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageByteArray,Base64.DEFAULT);
        return encodedImage;




    }
}
