package com.example.store;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.OnItemClickListener  {
    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       mRecyclerView = findViewById(R.id.recycler_view);
       mRecyclerView.setHasFixedSize(true);
       mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
       mExampleList = new ArrayList<>();

      //  textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.100.15:3000/").addConverterFactory(GsonConverterFactory.create()).build();

        NodejsApi nodejsApi = retrofit.create(NodejsApi.class);

        //POST
        createPost();

        Call<ArrayList<ExampleItem>> call = nodejsApi.getStores();

        call.enqueue(new Callback<ArrayList<ExampleItem>>() {
            @Override
            public void onResponse(Call<ArrayList<ExampleItem>> call, Response<ArrayList<ExampleItem>> response) {

                if (!response.isSuccessful()) {
                 /*   textViewResult.setText("Code: " + response.code()); */
                    return;
                }

                ArrayList<ExampleItem> items = response.body();
                mExampleList = items;
                mExampleAdapter = new ExampleAdapter(MainActivity.this, items);
                mRecyclerView.setAdapter(mExampleAdapter);
                mExampleAdapter.setOnItemClickListener(MainActivity.this);

            }

            @Override
            public void onFailure(Call<ArrayList<ExampleItem>> call, Throwable t) {
                /*textViewResult.setText(t.getMessage());*/
            }
        });
    }


    @Override
    public void onItemClick(int position) {
        ExampleItem clickedItem = mExampleList.get(position);
        final Double latpos = clickedItem.getLatitude();
        final Double longpos = clickedItem.getLongitude();
        Intent detailIntent = new Intent(MainActivity.this, MapsActivity.class);

        Bundle bundle = new Bundle();
        bundle.putDouble("latpos", latpos);
        bundle.putDouble("longpos", longpos);

        detailIntent.putExtras(bundle);

   startActivity(detailIntent);
    }

    private void createPost(){
        ExampleItem item = new ExampleItem("Rruga xx",12.3,43.2);
        Call<ExampleItem> call = NodejsApi.createStore(item);
    }
}