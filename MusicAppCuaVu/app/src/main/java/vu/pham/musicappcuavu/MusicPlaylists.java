package vu.pham.musicappcuavu;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MusicPlaylists implements Parcelable{
    private int HinhPlaylists;
    private String TenPlaylists;
    private ArrayList<Music> Musiclist;
    private int SoLuotThich;
    private boolean TrangThaiThich;

    public MusicPlaylists(int hinhPlaylists, String tenPlaylists, ArrayList<Music> musiclist, int soLuotThich, boolean trangThaiThich) {
        HinhPlaylists = hinhPlaylists;
        TenPlaylists = tenPlaylists;
        Musiclist = musiclist;
        SoLuotThich = soLuotThich;
        TrangThaiThich = trangThaiThich;
    }

    protected MusicPlaylists(Parcel in) {
        HinhPlaylists = in.readInt();
        TenPlaylists = in.readString();
        SoLuotThich = in.readInt();
        TrangThaiThich = in.readByte() != 0;
    }

    public static final Creator<MusicPlaylists> CREATOR = new Creator<MusicPlaylists>() {
        @Override
        public MusicPlaylists createFromParcel(Parcel in) {
            return new MusicPlaylists(in);
        }

        @Override
        public MusicPlaylists[] newArray(int size) {
            return new MusicPlaylists[size];
        }
    };

    public int getTongSoBaiHat(){
        return Musiclist.size();
    }

    public int getHinhPlaylists() {
        return HinhPlaylists;
    }

    public void setHinhPlaylists(int hinhPlaylists) {
        HinhPlaylists = hinhPlaylists;
    }

    public String getTenPlaylists() {
        return TenPlaylists;
    }

    public void setTenPlaylists(String tenPlaylists) {
        TenPlaylists = tenPlaylists;
    }

    public ArrayList<Music> getMusiclist() {
        return Musiclist;
    }

    public void setMusiclist(ArrayList<Music> musiclist) {
        Musiclist = musiclist;
    }

    public int getSoLuotThich() {
        return SoLuotThich;
    }

    public void setSoLuotThich(int soLuotThich) {
        SoLuotThich = soLuotThich;
    }

    public boolean getTrangThaiThich() {
        return TrangThaiThich;
    }

    public void setTrangThaiThich(boolean trangThaiThich) {
        TrangThaiThich = trangThaiThich;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(HinhPlaylists);
        dest.writeString(TenPlaylists);
        dest.writeInt(SoLuotThich);
        dest.writeByte((byte) (TrangThaiThich ? 1 : 0));
    }
}
