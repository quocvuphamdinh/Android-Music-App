package vu.pham.musicappcuavu.albums;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.invoke.LambdaConversionException;
import java.util.ArrayList;
import java.util.List;

import vu.pham.musicappcuavu.MusicPlaylists;
import vu.pham.musicappcuavu.R;

public class AlbumsFavorAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private int layout;
    private List<MusicPlaylists>musicPlaylists;
    private List<MusicPlaylists>musicPlaylistsFiltered;

    public AlbumsFavorAdapter(Context context, int layout, List<MusicPlaylists> musicPlaylists) {
        this.context = context;
        this.layout = layout;
        this.musicPlaylists = musicPlaylists;
        this.musicPlaylistsFiltered=musicPlaylists;
    }

    @Override
    public int getCount() {
        return musicPlaylistsFiltered.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolderAlbums{
        ImageView imgHinhAlbums;
        TextView txtTenAlbums, txtTenCasi;
        ImageButton imgbtnPlayAlbums;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderAlbums holderAlbums;
        if (convertView == null) {
            holderAlbums=new ViewHolderAlbums();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout, null);
            holderAlbums.imgHinhAlbums=(ImageView) convertView.findViewById(R.id.imageviewHinhAlbumFavourite);
            holderAlbums.txtTenAlbums=(TextView) convertView.findViewById(R.id.textviewTenAlbumsFavorite);
            holderAlbums.txtTenCasi=(TextView) convertView.findViewById(R.id.textviewTenCaSiAlbumsFavor);
            holderAlbums.imgbtnPlayAlbums=(ImageButton) convertView.findViewById(R.id.imagebuttonPlayAlbums);
            convertView.setTag(holderAlbums);
        }
        else{
            holderAlbums= (ViewHolderAlbums) convertView.getTag();
        }
        MusicPlaylists musicPlaylistsItem=musicPlaylistsFiltered.get(position);
        holderAlbums.imgHinhAlbums.setImageResource(musicPlaylistsItem.getHinhPlaylists());
        holderAlbums.txtTenAlbums.setText(musicPlaylistsItem.getTenPlaylists());
        holderAlbums.txtTenCasi.setText(musicPlaylistsItem.getTenPlaylists());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults=new FilterResults();
                if(constraint==null || constraint.length()==0){
                    filterResults.count=musicPlaylists.size();
                    filterResults.values=musicPlaylists;
                }
                else{
                    List<MusicPlaylists>resultMusicPlaylists=new ArrayList<>();
                    String searchString=constraint.toString();
                    for(MusicPlaylists musicPlaylists1 : musicPlaylists){
                        if(musicPlaylists1.getTenPlaylists().contains(searchString)){
                            resultMusicPlaylists.add(musicPlaylists1);
                        }
                        filterResults.count=resultMusicPlaylists.size();
                        filterResults.values=resultMusicPlaylists;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                musicPlaylistsFiltered= (List<MusicPlaylists>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
