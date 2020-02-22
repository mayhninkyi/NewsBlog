package com.mhk.newsblog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=findViewById(R.id.news_list);
        String url="https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=1b8936a1d12f41b98bc73648440eda9c";
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest objectRequest=new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ArrayList<NewsModel> models = new ArrayList<NewsModel>();
                            JSONArray array = response.getJSONArray("articles");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                NewsModel model = new NewsModel();
                                model.author = object.getString("author");
                                model.title = object.getString("title");
                                model.date = object.getString("publishedAt");
                                model.imageUrl = object.getString("urlToImage");
                                model.newsUrl = object.getString("url");
                                models.add(model);
                            }
                            ProgressBar bar=findViewById(R.id.progress_bar);
                            bar.setVisibility(View.GONE);
                            NewsAdapter adapter=new NewsAdapter(models,getApplicationContext());
                            list.setAdapter(adapter);
                            list.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(objectRequest);
        RetryPolicy retryPolicy=new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 30000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 5;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        };
        objectRequest.setRetryPolicy(retryPolicy);
    }
}
