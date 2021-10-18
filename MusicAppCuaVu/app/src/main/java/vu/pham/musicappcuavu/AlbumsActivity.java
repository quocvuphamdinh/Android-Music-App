package vu.pham.musicappcuavu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import vu.pham.musicappcuavu.albums.AlbumsFavorAdapter;

public class AlbumsActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    AlbumsFavorAdapter adapter;
    ArrayList<MusicPlaylists>albumsList;
    ListView lsvAlbums;
    EditText edtTimKiem;
    public static final String tenluutru2="ListAlbums";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        lsvAlbums=(ListView)findViewById(R.id.listviewAlbumsFavorite);
        edtTimKiem=(EditText)findViewById(R.id.edittextTimKiemAlbumsFavor);
        KhoiTaoNavigationBottom();
        GetAlbums();
        TimKiemAlbums();
        EventsInAlbums();
    }

    private void EventsInAlbums() {
        lsvAlbums.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(AlbumsActivity.this, MySongsActivity.class);
                intent.putExtra("songinplaylists", albumsList.get(position));
                startActivity(intent);
            }
        });
    }

    private void TimKiemAlbums() {
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void LoadAlbums(){
        SharedPreferences sharedPreferences=getSharedPreferences(tenluutru2, MODE_PRIVATE);
        Gson gson=new Gson();
        String json=sharedPreferences.getString("danhsachAlbums", null);
        Type type=new TypeToken<ArrayList<MusicPlaylists>>(){}.getType();
        albumsList=gson.fromJson(json, type);
    }
    private void SaveAlbums(){
        SharedPreferences sharedPreferences=getSharedPreferences(tenluutru2, MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(albumsList);
        editor.putString("danhsachAlbums", json);
        editor.apply();
    }

    private void GetAlbums() {
        Intent intentLayAlbums=getIntent();
        albumsList=new ArrayList<>();
        if(intentLayAlbums.getExtras()==null){
            LoadAlbums();
        }else {
            albumsList=intentLayAlbums.getExtras().getParcelableArrayList("albums");
        }
        adapter=new AlbumsFavorAdapter(AlbumsActivity.this, R.layout.albums_favorite_layout, albumsList);
        lsvAlbums.setAdapter(adapter);
    }

    private void KhoiTaoNavigationBottom() {
        bottomNavigationView=findViewById(R.id.bottomNavigationMusic);
        bottomNavigationView.setSelectedItemId(R.id.nav_albums);// mặc định mở lên là discover activity(main)
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.nav_albums:
                    return true;
                case R.id.nav_discover:
                    Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_mysongs:
                    SaveAlbums();
                    startActivity(new Intent(getApplicationContext(), MySongsActivity.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        }
    };
}