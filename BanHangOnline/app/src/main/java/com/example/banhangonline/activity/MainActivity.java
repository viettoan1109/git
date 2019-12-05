package com.example.banhangonline.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.banhangonline.R;
import com.example.banhangonline.adapter.LoaiVeAdapter;
import com.example.banhangonline.model.LoaiVe;
import com.example.banhangonline.ulti.CheckInternet;
import com.example.banhangonline.ulti.Server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    ListView listView;
    DrawerLayout drawerLayout;
    ArrayList<LoaiVe> arraylistLoaive;
    LoaiVeAdapter loaiVeAdapter;
    int id = 0;
    String tenLoaive = "";
    String hinhLoaive = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniViews();

        if (CheckInternet.haveNetworkConnection(getApplicationContext())) {
            ActionBar();
            ActionViewFlipper();
            GetDuLieuLoaiVe();
        } else {
            CheckInternet.ShowToast(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối Internet!!!");
            finish();
        }

    }

    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        // Gắn địa chỉ vào imageview
        mangquangcao.add("https://pigllet.000webhostapp.com/Datvexe/qc1.png");
        mangquangcao.add("https://pigllet.000webhostapp.com/Datvexe/qc2.png");
        mangquangcao.add("https://pigllet.000webhostapp.com/Datvexe/qc3.png");
        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_out);
        viewFlipper.setInAnimation(animation_slide_in);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size); // Set Icon
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void GetDuLieuLoaiVe() {
        final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        Log.d("HHHHHH", "1");
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.urlLoaive, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("HHHHHH", "2");
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        id = jsonObject.getInt("id");
                        tenLoaive = jsonObject.getString("tenLoaisp");
                        hinhLoaive = jsonObject.getString("hinhanhLoaisp");
                        arraylistLoaive.add(new LoaiVe(id, tenLoaive, hinhLoaive));
                        loaiVeAdapter.notifyDataSetChanged();  // Update
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("HHHHHH", "3");
                arraylistLoaive.add(3, new LoaiVe(0, "Liên Hệ", "https://pigllet.000webhostapp.com/Datvexe/phone.png"));
                arraylistLoaive.add(4, new LoaiVe(0, "Thông Tin", "https://pigllet.000webhostapp.com/Datvexe/support.png"));
                //loaiVeAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        Log.d("HHHHHH", "4");
        requestQueue.add(jsonArrayRequest);
    }

    private void iniViews() {
        toolbar = findViewById(R.id.toolbar);
        viewFlipper = findViewById(R.id.viewFlipper);
        recyclerView = findViewById(R.id.recycleView);
        navigationView = findViewById(R.id.navigationView);
        listView = findViewById(R.id.listView);
        drawerLayout = findViewById(R.id.drawerLayout);
        arraylistLoaive = new ArrayList<>();
        arraylistLoaive.add(new LoaiVe(0, "Trang chủ", "https://pigllet.000webhostapp.com/Datvexe/home.png"));
        loaiVeAdapter = new LoaiVeAdapter(arraylistLoaive, getApplicationContext());
        listView.setAdapter(loaiVeAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /* void dispatchLayout() {
        if(this.mAdapter == null) {
            Log.e("RecyclerView", "No adapter attached; skipping layout");
        } else if(this.mLayout == null) {
            Log.e("RecyclerView", "No layout manager attached; skipping layout");
        } else {*/

}
