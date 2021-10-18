package vu.pham.musicappcuavu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MusicHotRecomAdapter extends RecyclerView.Adapter<MusicHotRecomAdapter.ViewHolder> {
    Context context;
    ArrayList<Music>musiclist;

    public MusicHotRecomAdapter(Context context, ArrayList<Music> musiclist) {
        this.context = context;
        this.musiclist = musiclist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.hot_recommened_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Music music=musiclist.get(position);
        holder.txttenbaihat.setText(music.getTenBaiHat());
        holder.txttencasi.setText(music.getTenCaSi());
        holder.imghinh.setImageResource(music.getHinhAnh());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){
                    Toast.makeText(context, music.getTenBaiHat() +"-"+ music.getTenCaSi(), Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent=new Intent(context, PlaySongActivity.class);
                    intent.putExtra("music", music);
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return musiclist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        TextView txttenbaihat, txttencasi;
        ImageView imghinh;
        ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttenbaihat=itemView.findViewById(R.id.textviewTenBaiHatHotRecom);
            txttencasi=itemView.findViewById(R.id.textviewTenCaSiHotRecom);
            imghinh=itemView.findViewById(R.id.imgHinhHotRecom);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener=itemClickListener;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), true);
            return true;
        }
    }
}
