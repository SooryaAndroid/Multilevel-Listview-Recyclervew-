package com.android.listviewrecycler;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by arunkumar on 21/11/16.
 */

public class SonglistActivity extends AppCompatActivity {

    ProgressDialog pDialog;

    private List<Songlist>listitems = new ArrayList<Songlist>();

    SonglistAdapter madapter;

    RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);


        Bundle extras = getIntent().getExtras();
        String albumid = extras.getString("ALBUMID");
        //Toast.makeText(this, ""+tmp, Toast.LENGTH_SHORT).show();


        recyclerview = (RecyclerView)findViewById(R.id.recycler1);

        pDialog = new ProgressDialog(SonglistActivity.this);
        pDialog.setMessage("Loading");
        pDialog.show();

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://api.androidhive.info/songs/album_tracks.php?id="+albumid;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();


                        //Toast.makeText(getApplicationContext(),""+response.toString(),Toast.LENGTH_LONG).show();


                        try {

                            JSONObject jsonobj = new JSONObject(response);
                           JSONArray jsonarray = jsonobj.getJSONArray("songs");
                            for(int i =0; i<jsonarray.length(); i++){

                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                String id = jsonobject.getString("id");
                                String name = jsonobject.getString("name");
                                String duration = jsonobject.getString("duration");



                                Songlist songlist1 = new Songlist();

                                songlist1.setContext(SonglistActivity.this);

                                songlist1.setIdno(id);
                                songlist1.setSongnamess(name);
                                songlist1.setDuration(duration);

                                listitems.add(songlist1);

                            }


                        try{

                            madapter = new SonglistAdapter(listitems);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SonglistActivity.this);
                            recyclerview.setLayoutManager(layoutManager);
                            recyclerview.setAdapter(madapter);

                            }catch (Exception e){

                            Toast.makeText(SonglistActivity.this, "exception"+e.toString(), Toast.LENGTH_SHORT).show();

                        }


                        }catch(Exception e){

                            Toast.makeText(SonglistActivity.this, "exception"+e.toString(), Toast.LENGTH_SHORT).show();
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
