package com.vinaykumar.gamechangesns.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.vinaykumar.gamechangesns.R;

public class SummaryActivity extends AppCompatActivity {

    TextView tvRegion , tvSubRegion , tvPopulation;

    String region ,subRegion,population;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Intent intent = getIntent();
        region = intent.getStringExtra("region");
        subRegion = intent.getStringExtra("subregion");
        population = intent.getStringExtra("population");

        initCreate();
    }

    public void initCreate(){
        tvRegion = (TextView) findViewById(R.id.tv_region_name);
        tvSubRegion = (TextView) findViewById(R.id.tv_sub_region_name);
        tvPopulation = (TextView) findViewById(R.id.tv_population);

        if(!TextUtils.isEmpty(region))
        tvRegion.setText("Region : "+region);
        if(!TextUtils.isEmpty(subRegion))
        tvSubRegion.setText("subregion : "+subRegion);
        if(!TextUtils.isEmpty(population))
        tvPopulation.setText("Population : "+population);
    }
}
