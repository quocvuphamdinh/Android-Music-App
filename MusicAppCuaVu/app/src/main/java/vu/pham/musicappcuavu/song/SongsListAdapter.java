package vu.pham.musicappcuavu.song;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vu.pham.musicappcuavu.Music;
import vu.pham.musicappcuavu.R;

public class SongsListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Music>musicList;

    public SongsListAdapter(Context context, int layout, List<Music> musicList) {
        this.context = context;
        this.layout = layout;
        this.musicList = musicList;
    }

    @Override
    public int getCount() {
        return musicList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolderSongLists{
        ImageView imgHinhSong;
        TextView txtTenSong, txtTenCasiSong;
        ImageButton imgbtnPlaySong;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderSongLists holderSongLists;
        if(convertView==null){
            holderSongLists=new ViewHolderSongLists();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout, null);
            holderSongLists.imgHinhSong=(ImageView) convertView.findViewById(R.id.imageviewHinhSong);
            holderSongLists.txtTenSong=(TextView) convertView.findViewById(R.id.textviewTenSong);
            holderSongLists.txtTenCasiSong=(TextView) convertView.findViewById(R.id.textviewTenCaSiSong);
            holderSongLists.imgbtnPlaySong=(ImageButton) convertView.findViewById(R.id.imagebuttonPlaySong);
            convertView.setTag(holderSongLists);
        }else{
            holderSongLists= (ViewHolderSongLists) convertView.getTag();
        }
        Music music=musicList.get(position);
        holderSongLists.imgHinhSong.setImageResource(music.getHinhAnh());
        holderSongLists.txtTenSong.setText(music.getTenBaiHat());
        holderSongLists.txtTenCasiSong.setText(music.getTenCaSi());

        return convertView;
    }
}
