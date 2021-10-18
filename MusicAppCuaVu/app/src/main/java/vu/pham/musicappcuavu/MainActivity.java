package vu.pham.musicappcuavu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewHotRecommened;
    GridView grvPlaylists;
    ArrayList<Music>musicArrayList;
    MusicHotRecomAdapter adapterHotRecommened;
    ArrayList<MusicPlaylists>musicPlaylistsArrayList;
    ArrayList<Music>musicClassicalLofi;
    ArrayList<Music>musicHipHopLofi;
    ArrayList<Music>musicRap;
    ArrayList<Music>musicSonTungMTP;
    MusicPlaylistsAdapter adapterPlaylists;

    BottomNavigationView bottomNavigationView;

    ArrayList<MusicPlaylists>listAlbumsFavor;
    Button btnsave;

    public static final String tenluutru="TrangThaiAlbums";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        anhxa();
        KhoiTaoNavigationBottom();
        CauHinhRecyclerView();

        CauHinhPlaylists();
        LoadTrangThaiAlbums();
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveTrangThaiAlbums();
            }
        });
        eventsInPlaylists();
        //eventsInHotRecommened();

    }



    private void eventsInPlaylists() {
        grvPlaylists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this, ListSongsInPlaylistsActivity.class);
                intent.putExtra("songinplaylists", musicPlaylistsArrayList.get(position).getMusiclist());
                startActivity(intent);
            }
        });
    }

    private void KhoiTaoNavigationBottom() {
        bottomNavigationView=findViewById(R.id.bottomNavigationMusic);
        bottomNavigationView.setSelectedItemId(R.id.nav_discover);// mặc định mở lên là discover activity(main)
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.nav_discover:
                    return true;
                case R.id.nav_albums:
                    Intent intent =new Intent(getApplicationContext(), AlbumsActivity.class);
                    Bundle bundleAlbums=new Bundle();
                    bundleAlbums.putParcelableArrayList("albums", listAlbumsFavor);
                    intent.putExtras(bundleAlbums);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_mysongs:
                    startActivity(new Intent(getApplicationContext(), MySongsActivity.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        }
    };

    public void XoaAlbumsFavorite(MusicPlaylists musicPlaylists){
        for(int i=0;i<listAlbumsFavor.size();i++){
            if(listAlbumsFavor.get(i).getTenPlaylists().equals(musicPlaylists.getTenPlaylists())){
                listAlbumsFavor.remove(i);
                break;
            }
        }
//        SharedPreferences sharedPreferences=getSharedPreferences(tenluutru, MODE_PRIVATE);
//        sharedPreferences.edit().remove("trangthaialbums").apply();
    }
    public void TruyenAlumsFavorite(MusicPlaylists musicPlaylists){
        listAlbumsFavor.add(musicPlaylists);
    }

    private void SaveTrangThaiAlbums(){
        SharedPreferences sharedPreferences=getSharedPreferences(tenluutru, MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson= new Gson();
        String json = gson.toJson(listAlbumsFavor);
        editor.putString("trangthaialbums", json);
        editor.apply();
    }
    private void LoadTrangThaiAlbums(){
        SharedPreferences sharedPreferences=getSharedPreferences(tenluutru, MODE_PRIVATE);
        Gson gson= new Gson();
        String json=sharedPreferences.getString("trangthaialbums", null);
        Type type=new TypeToken<ArrayList<MusicPlaylists>>(){}.getType();
        listAlbumsFavor=gson.fromJson(json, type);
        if(listAlbumsFavor==null){
            listAlbumsFavor=new ArrayList<>();
        }else{
            for(int i=0;i<musicPlaylistsArrayList.size();i++){
                for(int j=0;j<listAlbumsFavor.size();j++){
                    if(musicPlaylistsArrayList.get(i).getTenPlaylists().equals(listAlbumsFavor.get(j).getTenPlaylists())){
                        musicPlaylistsArrayList.set(i, listAlbumsFavor.get(j));
                        adapterPlaylists.notifyDataSetChanged();
                        break;
                    }
                }
            }
        }
    }

    private void ThemBaiHatVaoPlaylists(){
        musicClassicalLofi=new ArrayList<>();
        musicClassicalLofi.add(new Music("Classical Lofi 1", "Chill Ghosties", R.drawable.classical_lofi, false, R.raw.classical_lofi_1));
        musicClassicalLofi.add(new Music("Classical Lofi 2", "Chill Ghosties", R.drawable.classical_lofi, false, R.raw.classical_lofi_2));
        musicClassicalLofi.add(new Music("Classical Lofi 3", "Chill Ghosties", R.drawable.classical_lofi, false, R.raw.classical_lofi_3));
        musicClassicalLofi.add(new Music("Classical Lofi 4", "Chill Ghosties", R.drawable.classical_lofi, false, R.raw.classical_lofi_4));
        musicHipHopLofi=new ArrayList<>();
        musicHipHopLofi.add(new Music("HipHop Lofi 1", "Chill HipHop", R.drawable.hiphop_lofi, false, R.raw.hiphop_lofi_1));
        musicHipHopLofi.add(new Music("HipHop Lofi 2", "Chill HipHop", R.drawable.hiphop_lofi, false, R.raw.hiphop_lofi_2));
        musicHipHopLofi.add(new Music("HipHop Lofi 3", "Chill HipHop", R.drawable.hiphop_lofi, false, R.raw.hiphop_lofi_3));
        musicHipHopLofi.add(new Music("HipHop Lofi 4", "Chill HipHop", R.drawable.hiphop_lofi, false, R.raw.hiphop_lofi_4));
        musicRap=new ArrayList<>();
        musicRap.add(new Music("25", "Táo - YoungH", R.drawable.tao_25_image, false, R.raw.tao_25_youngh));
        musicRap.add(new Music("Tay To", "MCK - PhongKhin", R.drawable.tayto_mck_image, false, R.raw.tayto_mck_phongkhin));
        musicRap.add(new Music("Điều Bí Mật", "ItsLee - Binz", R.drawable.dieu_bi_mat_image, false, R.raw.dieubimat_itslee_binz));
        musicRap.add(new Music("Crying Over U", "Justatee - Binz", R.drawable.cryingoveru_image, false, R.raw.crying_over_u_jutatee_binz));
        musicSonTungMTP=new ArrayList<>();
        musicSonTungMTP.add(new Music("Anh Sai Rồi", "Sơn Tùng MTP", R.drawable.songtungmtp_image, false, R.raw.anhsairoi_mtp));
        musicSonTungMTP.add(new Music("Bình Yên Nơi Đâu", "Sơn Tùng MTP", R.drawable.songtungmtp_image, false, R.raw.binhyennoidau_mtp));
        musicSonTungMTP.add(new Music("Em Đừng Đi", "Sơn Tùng MTP", R.drawable.songtungmtp_image, false, R.raw.emdungdi_mtp));
        musicSonTungMTP.add(new Music("Cơn Mưa Ngang Qua", "Sơn Tùng MTP", R.drawable.songtungmtp_image, false, R.raw.conmuangangqua_mtp));
    }

    private void CauHinhPlaylists() {
        ThemBaiHatVaoPlaylists();
        musicPlaylistsArrayList=new ArrayList<>();
        musicPlaylistsArrayList.add(new MusicPlaylists(R.drawable.classical_lofi, "Classical Lofi Playlists", musicClassicalLofi, 200, false));
        musicPlaylistsArrayList.add(new MusicPlaylists(R.drawable.hiphop_lofi, "HipHop Lofi Playlists", musicHipHopLofi, 300, false));
        musicPlaylistsArrayList.add(new MusicPlaylists(R.drawable.sontungmtp, "Sơn Tùng M-TP Playlists", musicSonTungMTP, 500, false));
        musicPlaylistsArrayList.add(new MusicPlaylists(R.drawable.rap, "Rap Playlists", musicRap, 400, false));
        adapterPlaylists=new MusicPlaylistsAdapter(MainActivity.this, R.layout.playlists_layout, musicPlaylistsArrayList);
        grvPlaylists.setAdapter(adapterPlaylists);
    }

    private void CauHinhRecyclerView() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false);
        recyclerViewHotRecommened.setLayoutManager(layoutManager);
        SpacingItemDecorator itemDecorator=new SpacingItemDecorator(10);
        recyclerViewHotRecommened.addItemDecoration(itemDecorator);
        musicArrayList=new ArrayList<>();
        musicArrayList.add(new Music("Red Swan", "kurochu", R.drawable.lofi_music_hot, false, R.raw.red_swan));
        musicArrayList.add(new Music("My War", "kurochu", R.drawable.lofi_music_hot2, false, R.raw.coding_lofi));
        musicArrayList.add(new Music("Red Swan", "kurochu", R.drawable.lofi_music_hot, false, R.raw.red_swan));
        musicArrayList.add(new Music("My War", "kurochu", R.drawable.lofi_music_hot2, false, R.raw.coding_lofi));
        adapterHotRecommened=new MusicHotRecomAdapter(MainActivity.this, musicArrayList);
        recyclerViewHotRecommened.setAdapter(adapterHotRecommened);
   }

    private void anhxa() {
        recyclerViewHotRecommened=(RecyclerView) findViewById(R.id.recyclerviewHotRecommened);
        grvPlaylists=(GridView) findViewById(R.id.gridviewPlaylists);
        btnsave=(Button)findViewById(R.id.buttonViewAllPlaylists);
        listAlbumsFavor=new ArrayList<>();
    }
}