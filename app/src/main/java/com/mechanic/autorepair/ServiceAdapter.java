package com.mechanic.autorepair;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.android.volley.VolleyLog.TAG;

/**
 * Created by Belal on 10/18/2017.
 */


public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;
    Bitmap mIcon_val;

    int i=0;
    String data = "";

    //we are storing all the products in a list
    private     ArrayList<Service> serviceList = new ArrayList<Service>();

    //getting the context and product list with constructor
    public ServiceAdapter(Context mCtx, List<Service> serviceList) {
        this.mCtx = mCtx;
        this.serviceList = (ArrayList<Service>) serviceList;
    }

    @Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_selectionofservice, parent,false);
        return new ServiceViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final ServiceViewHolder holder, final int position) {
        //getting the product of the specified position
        final Service service = serviceList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(service.getTitle());

        /*holder.textViewShortDesc.setText(product.getShortdesc());
        holder.textViewRating.setText(String.valueOf(product.getRating()));*/
        holder.textViewPrice.setText(String.valueOf("Rs. "+service.getPrice()));
        Log.i("tagg",service.getImage());

       /* Glide.with(mCtx)
                .load(product.getImage())
                .into(holder.imageView);*/
        Picasso.get().load(service.getImage()).into(holder.imageView);

        holder.checkbox.setChecked(service.isSelected());
        holder.checkbox.setTag(serviceList.get(position));


        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int j=0;




                    Service service1 = (Service) holder.checkbox.getTag();
                    service1.setSelected(holder.checkbox.isChecked());
                    serviceList.get(position).setSelected(holder.checkbox.isChecked());








             /*   Service service1 = (Service) holder.checkbox.getTag();

                    String data = "";


                    service1.setSelected(holder.checkbox.isChecked());

                    serviceList.get(position).setSelected(holder.checkbox.isChecked());

                  *//*  if (serviceList.get(position).isSelected() == true) {

                        data = "\n" + serviceList.get(position).getTitle().toString() + "   " + serviceList.get(position).getPrice();
                        datas.add(data);
                        //   Log.i("datas",datas.get(position));
                        Toast.makeText(mCtx, "" + data, Toast.LENGTH_SHORT).show();

                    }*//*









                for (j=0; j<serviceList.size();j++){

                    if (serviceList.get(j).isSelected() == true){


                        data = "\n" + serviceList.get(j).getTitle().toString() + "   " + serviceList.get(j).getPrice();
                        datas.add(data);
                        Log.i("datas",datas.get(i));
                        i++;

                    }


                }
                Toast.makeText(mCtx, "" + data, Toast.LENGTH_SHORT).show();
*/






            }
        });

 /*   holder.order.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ArrayList<String> datas = new ArrayList<>();

            for (int j=0; j<serviceList.size();j++){

                if (serviceList.get(j).isSelected() == true){


                    data = "\n" + serviceList.get(j).getTitle().toString() + "   " + serviceList.get(j).getPrice();
                    datas.add(data);
                   *//* Log.i("datas",datas.get(i));
                    i++;*//*

                }


            }




            Intent intent=new Intent(v.getContext(), Order_activity.class);
            intent.putStringArrayListExtra("datas", (ArrayList<String>) datas);
            mCtx.startActivity(intent);
        }
    });*/

      //  holder.checkbox.setChecked(service.isSelected());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Service service1 = (Service) holder.checkbox.getTag();

                Toast.makeText(mCtx, ""+service.getTitle(), Toast.LENGTH_SHORT).show();
              //  serviceList = service.setSelected(true);

                if (serviceList.get(position).isSelected() == true){

                    service1.setSelected(false);
                    serviceList.get(position).setSelected(false);
                    holder.checkbox.setChecked(false);
                    holder.cardView.setBackgroundResource(R.drawable.bordercardview);


                }
                else{

                    holder.checkbox.setChecked(true);
                    service1.setSelected(holder.checkbox.isChecked());
                    serviceList.get(position).setSelected(holder.checkbox.isChecked());
                    holder.cardView.setBackgroundResource(R.drawable.border);


                }







            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent intent = new Intent(view.getContext(), Model_Info.class);
                intent.putExtra("title",service.getTitle());
                intent.putExtra("image",service.getImage());
                mCtx.startActivity(intent);*/


            }
        });
       /* Glide.with(mCtx).
                load(product.getImage()).
                error(R.drawable.ic_directions_bike_black_24dp).
                into(holder.imageView);*/

    }


    @Override
    public int getItemCount() {
        return serviceList.size();
    }


    class ServiceViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewRating, textViewPrice;
        ImageView imageView;
        CardView cardView;
        CheckBox checkbox;
       // Button order;

        public ServiceViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
           // order=itemView.findViewById(R.id.order);
          /*  textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);*/
            checkbox = itemView.findViewById(R.id.checkbox);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.cardview);

        }


    }



}
