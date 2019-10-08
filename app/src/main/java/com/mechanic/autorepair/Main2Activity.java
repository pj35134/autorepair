package com.mechanic.autorepair;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    //List<Product> productList;
    ArrayList<Product> productList = new ArrayList<Product>();
    private static final String PRODUCT_URL ="http://192.168.1.69/MyApi/apii.php" ;
    //the recyclerviewr
    RecyclerView recyclerView;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //loadProducts();
        //initializing the productlist
        new LongOperation().execute("");
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
                                String title = productObject.getString("title");
                                String shortdesc =productObject.getString("shortdesc");
                                double price = productObject.getDouble("price");
                                double rating = productObject.getDouble("rating");
                                String image = productObject.getString("image").toString();
                                Product product = new Product(id,title,image);
                                productList.add(product);
                                Log.i("tag", title);


                            }
                            adapter = new ProductAdapter(Main2Activity.this,productList);
                            recyclerView.setAdapter(adapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Main2Activity.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Main2Activity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }


        );
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
