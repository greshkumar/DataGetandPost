package com.example.greshkumartharwani.getplusset;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText title, body, userid;
    TextView result;
    Button btn;
    RecyclerView rv;
    model resultpost;
    ArrayList<model> mlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = (EditText) findViewById(R.id.ttl);
        body = (EditText) findViewById(R.id.body);
        userid = (EditText) findViewById(R.id.userid);
        result = (TextView) findViewById(R.id.result);
        btn = (Button) findViewById(R.id.btn);
        rv = (RecyclerView) findViewById(R.id.rv);
        mlist = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this));
        getdata();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userid.getText().toString().equals("")||title.getText().toString().equals("")||body.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Please Enter Data",Toast.LENGTH_SHORT).show();
                }else {
                    model m = new model();
                    m.setUserId(Integer.parseInt(userid.getText().toString()));
                    m.setTitle(title.getText().toString());
                    m.setBody(body.getText().toString());
                    postdata(m);
                }
            }
        });
    }

    public void postdata(model post) {
        try {
            apiservice service = apiclient.getRetrofit().create(apiservice.class);
            Call<model> call = service.postdata(post);
            call.enqueue(new Callback<model>() {
                @Override
                public void onResponse(Call<model> call, Response<model> response) {
                    resultpost = response.body();
                    result.setText("Post ID : " + String.valueOf(resultpost.getId()) + "\nUserID : " + String.valueOf(resultpost.getUserId()) + "\nTitle : " + resultpost.getTitle() + "\nBody : " + resultpost.getBody());
                }
                @Override
                public void onFailure(Call<model> call, Throwable t) {
                    Log.v("Error","Failed");
                }
            });

        } catch (Exception e) {
            Log.v("exception","error : "+e);
        }
    }

    public void getdata() {
        try {
            apiservice service = apiclient.getRetrofit().create(apiservice.class);
            Call<ArrayList<model>> call = service.getdata();
            call.enqueue(new Callback<ArrayList<model>>() {
                @Override
                public void onResponse(Call<ArrayList<model>> call, Response<ArrayList<model>> response) {
                    mlist = response.body();
                    rv.setAdapter(new rvadapter(mlist));
                }
                @Override
                public void onFailure(Call<ArrayList<model>> call, Throwable t) {
                    Log.v("Error","Failed");
                }
            });
        } catch (Exception e) {
            Log.v("exception","error : "+e);
        }
    }
}
