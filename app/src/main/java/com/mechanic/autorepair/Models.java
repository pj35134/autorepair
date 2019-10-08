package com.mechanic.autorepair;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Models extends AppCompatActivity {
    ArrayList<Model> modelList = new ArrayList<Model>();
    private static final String PRODUCT_URL ="http://192.168.1.69/MyApi/models.php" ;
    //the recyclerviewr
    RecyclerView recyclerView;
    ModelsAdapter adapter;
    String image,model;
    private GridLayoutManager lLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_models);
        Intent intent = getIntent();
        model = intent.getStringExtra("title");
         image = intent.getStringExtra("image");
       // lLayout = new GridLayoutManager(Models.this, 4);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         new Models.LongOperation().execute("");


    }

    private void loadProducts(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, PRODUCT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray products = new JSONArray(response);
                            for(int i =0;i<products.length();i++){
                                JSONObject productObject = products.getJSONObject(i);
                                int id = productObject.getInt("id");

                                String modelTitle = productObject.getString("title");
                                /*String shortdesc =productObject.getString("shortdesc");
                                double price = productObject.getDouble("price");
                                double rating = productObject.getDouble("rating");*/
                              //  String image = productObject.getString("image").toString();


                                Model model = new Model(id,modelTitle,image);
                                modelList.add(model);
                                Log.i("tagy", modelTitle);
                                Log.i("response", response);



                            }
                            adapter = new ModelsAdapter(Models.this,modelList);
                            recyclerView.setAdapter(adapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Models.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Models.this, ""+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }


        )
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("model",model);
                return params;


            }
        };
        Volley.newRequestQueue(this).add(stringRequest);








    }


    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            loadProducts();
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {

            Log.i("hello",result);
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

}
