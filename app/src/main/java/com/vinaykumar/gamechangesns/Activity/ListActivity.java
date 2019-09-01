package com.vinaykumar.gamechangesns.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.ColorSpace;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vinaykumar.gamechangesns.Adapter.CountryAdapter;
import com.vinaykumar.gamechangesns.Model.CountryModel;
import com.vinaykumar.gamechangesns.R;
import com.vinaykumar.gamechangesns.Rest.ApiClass;
import com.vinaykumar.gamechangesns.Rest.ApiInterface;

import org.json.JSONArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ArrayList<CountryModel> countryModelArrayList;
    CountryAdapter countryAdapter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Fetching Country data ...");

        initCreate();

        // // 3 rows country name , capital , button learn more >  summary (  3 fields >region , sub region & population )


        //  Base Url :  https://restcountries.eu/rest
        // end Pount :  /v2/all
        // JsonArray

        // JsonObject
        // node name :  name
        // capital
        // on Button click
        //region
        //subregion
        //population

    }

    private void initCreate(){
        countryModelArrayList = new ArrayList<>();
        countryAdapter = new CountryAdapter(this,countryModelArrayList);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
        if (!isNetworkAvailable()) {
        Toast.makeText(getApplicationContext(),"Kindly check your internet connection",Toast.LENGTH_LONG).show();
        }
        else {
            fetchCountryDetails();
        }

    }

    public void fetchCountryDetails(){
        progressDialog.show();
        ApiInterface apiService =
                ApiClass.getClient().create(ApiInterface.class);

        Call<JsonArray> call = apiService.getCounrtyData();
        Log.e("url",call.request().url().toString());

        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if(response.isSuccessful()) {
                    JsonArray jsonArray = response.body();
                    if(jsonArray.size()>0){
                        for(int i = 0; i<jsonArray.size();i++){
                            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();

                            // capital
                            // on Button click
                            //region
                            //"
                            //population
                            String countryName = jsonObject.get("name").getAsString();
                            String   capital = jsonObject.get("capital").getAsString();
                            String   region = jsonObject.get("region").getAsString();
                            String   subregion = jsonObject.get("subregion").getAsString();
                            String   population = jsonObject.get("population").getAsString();

                            CountryModel countryModel = new CountryModel();
                            countryModel.setCountryName(countryName);
                            countryModel.setCapitalName(capital);
                            countryModel.setRegion(region);
                            countryModel.setSubregion(subregion);
                            countryModel.setPopulation(population);

                            countryModelArrayList.add(countryModel);
                        }
                        progressDialog.dismiss();
                        recyclerView.setAdapter(new CountryAdapter(ListActivity.this,countryModelArrayList));
                    }
                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"some error occurred",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {

            }
        });

    }


    private boolean isNetworkAvailable() {
        // Using ConnectivityManager to check for Network Connection
        ConnectivityManager connectivityManager = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

}
