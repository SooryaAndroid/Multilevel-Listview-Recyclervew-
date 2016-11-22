package com.android.listviewrecycler;

import android.app.ProgressDialog;
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
 * Created by arunkumar on 22/11/16.
 */

public class SongdetailActivity extends AppCompatActivity {
    ProgressDialog pDialog;

    RecyclerView recyclerView;

    private List<Songdetail>songdetail = new ArrayList<>();

    private SongdetailAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Bundle bundle = getIntent().getExtras();
        String songid = bundle.getString("SONGID");
        String albumid = bundle.getString("ALBUMID");

        Toast.makeText(this, "positionnn"+albumid, Toast.LENGTH_SHORT).show();


        recyclerView = (RecyclerView)findViewById(R.id.recycler2);


        pDialog = new ProgressDialog(SongdetailActivity.this);
        pDialog.setMessage("Loading");
        pDialog.show();

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://api.androidhive.info/songs/track.php?album="+songid+"&song="+songid;//album=3&song=1
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();


                        //Toast.makeText(getApplicationContext(),""+response.toString(),Toast.LENGTH_LONG).show();
                        try {


                            JSONObject jsonObject = new JSONObject(response);
                            String name  = jsonObject.getString("name");
                            String duration  = jsonObject.getString("duration");
                            String album  = jsonObject.getString("album");



                            Songdetail songdetails = new Songdetail();
                            songdetails.setSongs(name);
                            songdetails.setAlbums(album);
                            songdetails.setDurations(duration);


                            songdetail.add(songdetails);


                        }catch (Exception e){

                        }


                        madapter = new SongdetailAdapter(songdetail);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SongdetailActivity.this);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(madapter);

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
