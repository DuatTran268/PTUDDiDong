package deso2.trannhatduat.dlu_2012254;

import android.net.Uri;

import java.io.Serializable;

public class VatTu implements Serializable {

   public int maVatTu;
    public int donGia;
    public int hinhVatTu;
   public String tenVatTu;
   public String donViTinh;


    public VatTu(int maVatTu,  int hinhVatTu, String tenVatTu, int donGia, String donViTinh) {
        this.maVatTu = maVatTu;
        this.hinhVatTu = hinhVatTu;
        this.tenVatTu = tenVatTu;
        this.donGia = donGia;
        this.donViTinh = donViTinh;
    }

}

