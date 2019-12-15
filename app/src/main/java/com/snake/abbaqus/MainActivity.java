package com.snake.abbaqus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<Children> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        recyclerView=findViewById(R.id.recycleView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        adapter = new Adapter(mContext, list);
        recyclerView.setAdapter(adapter);

        executeData();
    }

    private void executeData() {
        String url = "https://www.reddit.com/subreddits/popular.json";
        final ProgressDialog progressDialog = new ProgressDialog(mContext);

        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Log.d(this.getClass().getSimpleName(), "URL-" + url);

        Retrofit.Builder builder =new Retrofit.Builder()
                //this is the base URL. URL we created in interface will be added to it
                .baseUrl(" https://www.reddit.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        RetrofitClient client = retrofit.create(RetrofitClient.class);
        //this is where we call the method fromRetrofitClient.
        // I passed my ID from github for getting data. Try your own
        Call<Data> call =client.reposForUser();

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                // this is where we handle the response ofc
                progressDialog.dismiss();
                Log.d(this.getClass().getSimpleName(), "onResponse: "+response.body().getKind());
                if(response.body().getOuterdata().getChildren().size()>0) {
                    for (int i = 0; i < response.body().getOuterdata().getChildren().size(); i++) {
                        list.add(new Children(response.body().getOuterdata().getChildren().get(i).getKind(),response.body().getOuterdata().getChildren().get(i).getInnerData()));
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this,"Something wrong",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save) {
            startActivity(new Intent(this,SavedData.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
