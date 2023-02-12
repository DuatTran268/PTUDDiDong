package deso2.trannhatduat.dlu_2012254;

import java.util.ArrayList;
import java.util.List;

// lưu trữ local
public class StorageLocal {
    public static List<VatTu>  listVatTu = new ArrayList<>();

    public static int createIDNew(){
        if (listVatTu.size() > 0){
            return  listVatTu.get(listVatTu.size() - 1).maVatTu + 1;
        }
        return 1;
    }
}
