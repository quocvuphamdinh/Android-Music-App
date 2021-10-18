package vu.pham.musicappcuavu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MusicPlaylistsAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<MusicPlaylists>musicPlaylistsList;

    public MusicPlaylistsAdapter(MainActivity context, int layout, List<MusicPlaylists> musicPlaylists) {
        this.context = context;
        this.layout = layout;
        this.musicPlaylistsList = musicPlaylists;
    }

    @Override
    public int getCount() {
        return musicPlaylistsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView txttenplaylists, txtsoluotlike, txttongsobaihat;
        ImageView imgHinhPlaylists;
        ImageButton imgbtnLike;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(layout, null);
            holder.txttenplaylists=(TextView) convertView.findViewById(R.id.textviewTenPlaylists);
            holder.txtsoluotlike=(TextView) convertView.findViewById(R.id.textviewLuotLike);
            holder.txttongsobaihat=(TextView) convertView.findViewById(R.id.textviewTongBaiHat);
            holder.imgHinhPlaylists=(ImageView) convertView.findViewById(R.id.imageviewHinhPlaylists);
            holder.imgbtnLike=(ImageButton) convertView.findViewById(R.id.imagebuttonLike);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        MusicPlaylists musicPlaylists=musicPlaylistsList.get(position);
        holder.imgHinhPlaylists.setImageResource(musicPlaylists.getHinhPlaylists());
        holder.txttenplaylists.setText(musicPlaylists.getTenPlaylists());
        holder.txtsoluotlike.setText(musicPlaylists.getSoLuotThich()+"");
        holder.txttongsobaihat.setText(musicPlaylists.getTongSoBaiHat()+" tracks");

        holder.imgbtnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((musicPlaylists.getTrangThaiThich())==false){
                    holder.imgbtnLike.setImageResource(R.drawable.like);
                    musicPlaylists.setTrangThaiThich(true);
                    context.TruyenAlumsFavorite(musicPlaylists);
                }
                else{
                    holder.imgbtnLike.setImageResource(R.drawable.not_like);
                    musicPlaylists.setTrangThaiThich(false);
                    context.XoaAlbumsFavorite(musicPlaylists);
                }
            }
        });
        return convertView;
    }
}
