package vu.pham.musicappcuavu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SongInPlaylistsAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Music>musicList;

    public SongInPlaylistsAdapter(Context context, int layout, List<Music> musicList) {
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
    private class ViewHolderSongInPlaylists{
        TextView txttenbaihat;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderSongInPlaylists holderSongInPlaylists;
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout, null);
            holderSongInPlaylists=new ViewHolderSongInPlaylists();
            holderSongInPlaylists.txttenbaihat=(TextView) convertView.findViewById(R.id.textviewTenBaiHatSongItemInPlaylists);
            convertView.setTag(holderSongInPlaylists);
        }else{
            holderSongInPlaylists= (ViewHolderSongInPlaylists) convertView.getTag();
        }
        Music music=musicList.get(position);
        holderSongInPlaylists.txttenbaihat.setText(music.getTenBaiHat());
        return convertView;
    }
}
