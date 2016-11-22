package com.android.listviewrecycler;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SongActivity extends AppCompatActivity {

    ProgressDialog pDialog;

    RecyclerView recyclerView;

    private List<Song>songList = new ArrayList<>();

    private SongAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recycler);


        pDialog = new ProgressDialog(SongActivity.this);
                    pDialog.setMessage("Loading");
                    pDialog.show();

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://api.androidhive.info/songs/albums.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();


                        //Toast.makeText(getApplicationContext(),""+response.toString(),Toast.LENGTH_LONG).show();


                        try {

                            JSONArray jsonArray = new JSONArray(response);
                            for (int i=0; i<jsonArray.length(); i++){


                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String name = jsonObject.getString("name");
                                String songs_count = jsonObject.getString("songs_count");

                                //Toast.makeText(getApplicationContext(),""+name.toString(),Toast.LENGTH_LONG).show();

                                Song songs = new Song();



                                songs.setContext(SongActivity.this);
                                songs.setId(id);
                                songs.setSongnames(name);
                                songs.setSongcounts(songs_count);

                                songList.add(songs);
                            }


                            madapter = new SongAdapter(songList);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(madapter);


                        }catch(Exception e){

                        }

                    }


                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error" + error, Toast.LENGTH_SHORT).show();


            }
        });
        queue.add(stringRequest);
    }

}
