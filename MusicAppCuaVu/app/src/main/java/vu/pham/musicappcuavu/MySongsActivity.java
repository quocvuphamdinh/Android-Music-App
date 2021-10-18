package vu.pham.musicappcuavu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import vu.pham.musicappcuavu.song.SongsListAdapter;

public class MySongsActivity extends AppCompatActivity {

    ListView lsvtab1, lsvtab2;
    ArrayList<Music>musicLofiArrayList;
    SongsListAdapter adaptertab1;
    ArrayList<Music>musicVietNamArrayList;
    SongsListAdapter adaptertab2;
    TabHost tabHost;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_songs);

        anhxa();
        KhoiTaoNavigationBottom();
        KhoiTaoTabSelector();
        KhoiTaoSongs();
        Events();
    }

    private void Events() {
        lsvtab1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MySongsActivity.this, musicLofiArrayList.get(position).getTenBaiHat(), Toast.LENGTH_LONG).show();
                Intent intentTab1=new Intent(MySongsActivity.this, PlaySongActivity.class);
                intentTab1.putExtra("music", musicLofiArrayList.get(position));
                startActivity(intentTab1);
            }
        });
        lsvtab2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MySongsActivity.this, musicVietNamArrayList.get(position).getTenBaiHat(), Toast.LENGTH_LONG).show();
                Intent intentTab2=new Intent(MySongsActivity.this, PlaySongActivity.class);
                intentTab2.putExtra("music", musicVietNamArrayList.get(position));
                startActivity(intentTab2);
            }
        });

    }

    private void KhoiTaoSongs() {
        musicLofiArrayList=new ArrayList<>();
        musicLofiArrayList.add(new Music("Classical Lofi 1", "Chill Ghosties", R.drawable.classical_lofi, false, R.raw.classical_lofi_1));
        musicLofiArrayList.add(new Music("Classical Lofi 2", "Chill Ghosties", R.drawable.classical_lofi, false, R.raw.classical_lofi_2));
        musicLofiArrayList.add(new Music("Classical Lofi 3", "Chill Ghosties", R.drawable.classical_lofi, false, R.raw.classical_lofi_3));
        musicLofiArrayList.add(new Music("Classical Lofi 4", "Chill Ghosties", R.drawable.classical_lofi, false, R.raw.classical_lofi_4));
        musicLofiArrayList.add(new Music("HipHop Lofi 1", "Chill HipHop", R.drawable.hiphop_lofi, false, R.raw.hiphop_lofi_1));
        musicLofiArrayList.add(new Music("HipHop Lofi 2", "Chill HipHop", R.drawable.hiphop_lofi, false, R.raw.hiphop_lofi_2));
        musicLofiArrayList.add(new Music("HipHop Lofi 3", "Chill HipHop", R.drawable.hiphop_lofi, false, R.raw.hiphop_lofi_3));
        musicLofiArrayList.add(new Music("HipHop Lofi 4", "Chill HipHop", R.drawable.hiphop_lofi, false, R.raw.hiphop_lofi_4));
        adaptertab1=new SongsListAdapter(MySongsActivity.this, R.layout.song_list_layout, musicLofiArrayList);
        lsvtab1.setAdapter(adaptertab1);
        musicVietNamArrayList=new ArrayList<>();
        musicVietNamArrayList.add(new Music("25", "Táo - YoungH", R.drawable.tao_25_image, false, R.raw.tao_25_youngh));
        musicVietNamArrayList.add(new Music("Tay To", "MCK - PhongKhin", R.drawable.tayto_mck_image, false, R.raw.tayto_mck_phongkhin));
        musicVietNamArrayList.add(new Music("Điều Bí Mật", "ItsLee - Binz", R.drawable.dieu_bi_mat_image, false, R.raw.dieubimat_itslee_binz));
        musicVietNamArrayList.add(new Music("Crying Over U", "Justatee - Binz", R.drawable.cryingoveru_image, false, R.raw.crying_over_u_jutatee_binz));
        musicVietNamArrayList.add(new Music("Anh Sai Rồi", "Sơn Tùng MTP", R.drawable.songtungmtp_image, false, R.raw.anhsairoi_mtp));
        musicVietNamArrayList.add(new Music("Bình Yên Nơi Đâu", "Sơn Tùng MTP", R.drawable.songtungmtp_image, false, R.raw.binhyennoidau_mtp));
        musicVietNamArrayList.add(new Music("Em Đừng Đi", "Sơn Tùng MTP", R.drawable.songtungmtp_image, false, R.raw.emdungdi_mtp));
        musicVietNamArrayList.add(new Music("Cơn Mưa Ngang Qua", "Sơn Tùng MTP", R.drawable.songtungmtp_image, false, R.raw.conmuangangqua_mtp));
        adaptertab2=new SongsListAdapter(MySongsActivity.this, R.layout.song_list_layout, musicVietNamArrayList);
        lsvtab2.setAdapter(adaptertab2);
    }

    private void anhxa() {
        lsvtab1=(ListView) findViewById(R.id.listviewSongTab1);
        lsvtab2=(ListView) findViewById(R.id.listviewSongTab2);
    }

    private void KhoiTaoTabSelector() {
        tabHost=(TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tab1=tabHost.newTabSpec("t1");
        tab1.setIndicator("Lofi Music");
        tab1.setContent(R.id.tab1);
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2=tabHost.newTabSpec("t2");
        tab2.setIndicator("VietNam Music");
        tab2.setContent(R.id.tab2);
        tabHost.addTab(tab2);

    }

    private void KhoiTaoNavigationBottom() {
        bottomNavigationView=findViewById(R.id.bottomNavigationMusic);
        bottomNavigationView.setSelectedItemId(R.id.nav_mysongs);// mặc định mở lên là discover activity(main)
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.nav_mysongs:
                    return true;
                case R.id.nav_discover:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_albums:
                    startActivity(new Intent(getApplicationContext(), AlbumsActivity.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        }
    };
}