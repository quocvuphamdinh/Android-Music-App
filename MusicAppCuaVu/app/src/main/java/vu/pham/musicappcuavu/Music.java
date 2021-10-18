package vu.pham.musicappcuavu;

import android.os.Parcel;
import android.os.Parcelable;

public class Music implements Parcelable {
    private String TenBaiHat;
    private String TenCaSi;
    private int HinhAnh;
    private boolean TrangThaiThich;
    private int FileBaiHat;

    public Music(String tenBaiHat, String tenCaSi, int hinhAnh, boolean trangThaiThich, int fileBaiHat) {
        TenBaiHat = tenBaiHat;
        TenCaSi = tenCaSi;
        HinhAnh = hinhAnh;
        TrangThaiThich = trangThaiThich;
        FileBaiHat = fileBaiHat;
    }

    protected Music(Parcel in) {
        TenBaiHat = in.readString();
        TenCaSi = in.readString();
        HinhAnh = in.readInt();
        TrangThaiThich = in.readByte() != 0;
        FileBaiHat = in.readInt();
    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };

    public String getTenBaiHat() {
        return TenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        TenBaiHat = tenBaiHat;
    }

    public String getTenCaSi() {
        return TenCaSi;
    }

    public void setTenCaSi(String tenCaSi) {
        TenCaSi = tenCaSi;
    }

    public int getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public boolean isTrangThaiThich() {
        return TrangThaiThich;
    }

    public void setTrangThaiThich(boolean trangThaiThich) {
        TrangThaiThich = trangThaiThich;
    }

    public int getFileBaiHat() {
        return FileBaiHat;
    }

    public void setFileBaiHat(int fileBaiHat) {
        FileBaiHat = fileBaiHat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(TenBaiHat);
        dest.writeString(TenCaSi);
        dest.writeInt(HinhAnh);
        dest.writeByte((byte) (TrangThaiThich ? 1 : 0));
        dest.writeInt(FileBaiHat);
    }
}
