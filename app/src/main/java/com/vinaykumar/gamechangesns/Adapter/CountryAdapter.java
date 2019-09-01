package com.vinaykumar.gamechangesns.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.vinaykumar.gamechangesns.Activity.SummaryActivity;
import com.vinaykumar.gamechangesns.Model.CountryModel;
import com.vinaykumar.gamechangesns.R;

import java.util.ArrayList;

import retrofit2.Callback;

/**
 * Created by admin on 01-Sep-19.
 */

public class CountryAdapter  extends RecyclerView.Adapter<CountryAdapter.MyViewHolder>{

    Context context ;
    ArrayList<CountryModel> countryModelArrayList;

    public class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView tvCountryName , tvCountryCapital ;
        Button btLearnMore ;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvCountryName = (TextView) itemView.findViewById(R.id.tv_country_name);
            tvCountryCapital = (TextView) itemView.findViewById(R.id.tv_country_capital);
            btLearnMore = (Button) itemView.findViewById(R.id.bt_learn_more);


        }
    }


    public CountryAdapter(Context context , ArrayList<CountryModel> countryModelArrayList){
        this.context = context;
        this.countryModelArrayList = countryModelArrayList;
    }





    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_country, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
    holder.tvCountryName.setText("Country : "+countryModelArrayList.get(position).getCountryName());
    holder.tvCountryCapital.setText("Capital : "+countryModelArrayList.get(position).getCapitalName());

    holder.btLearnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SummaryActivity.class);
                intent.putExtra("region",countryModelArrayList.get(position).getRegion());
                intent.putExtra("subregion",countryModelArrayList.get(position).getSubregion());
                intent.putExtra("population",countryModelArrayList.get(position).getPopulation());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryModelArrayList.size();
    }


}
