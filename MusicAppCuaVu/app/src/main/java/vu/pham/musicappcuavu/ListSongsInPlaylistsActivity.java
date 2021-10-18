package vu.pham.musicappcuavu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListSongsInPlaylistsActivity extends AppCompatActivity {

    ImageView imgHinhSonginPlaylists;
    TextView txtTenCaSiinPlaylists;
    ListView lsvSonginPlaylists;
    ArrayList<Music>musicArrayList;
    SongInPlaylistsAdapter adapterSonginPlaylists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_songs_in_playlists);

        anhxa();
        KhoiTaoDanhSachSong();
        EventsInListView();
    }

    private void EventsInListView() {
        lsvSonginPlaylists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ListSongsInPlaylistsActivity.this, PlaySongActivity.class);
                intent.putExtra("music", musicArrayList.get(position));
                startActivity(intent);
            }
        });
    }

    private void KhoiTaoDanhSachSong() {
        musicArrayList=new ArrayList<>();
        Intent intent=getIntent();
        musicArrayList=intent.getParcelableArrayListExtra("songinplaylists");
        adapterSonginPlaylists=new SongInPlaylistsAdapter(ListSongsInPlaylistsActivity.this, R.layout.song_in_playlists_layout, musicArrayList);
        lsvSonginPlaylists.setAdapter(adapterSonginPlaylists);
    }

    private void anhxa() {
        imgHinhSonginPlaylists=(ImageView) findViewById(R.id.imageviewHinhCaSiSongInPlaylist);
        txtTenCaSiinPlaylists=(TextView) findViewById(R.id.textviewTenCaSiInPlaylists);
        lsvSonginPlaylists=(ListView) findViewById(R.id.listviewSongInPlaylists);
    }
}