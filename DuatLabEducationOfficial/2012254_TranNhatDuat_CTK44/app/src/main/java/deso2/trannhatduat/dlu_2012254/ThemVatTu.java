package deso2.trannhatduat.dlu_2012254;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ThemVatTu extends AppCompatActivity {

    ImageView imgAvtVatTu;
    EditText txt_TenVatTu, txt_DonGiaVatTu, txt_DonViTinh;
    Button btn_ThemVatTu;

    Uri choseImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_vat_tu);

        // ánh xạ
        imgAvtVatTu = findViewById(R.id.imgVatTuAdd);
        txt_TenVatTu = findViewById(R.id.tenVatTuAdd);
        txt_DonGiaVatTu = findViewById(R.id.giaVatTuAdd);
        txt_DonViTinh = findViewById(R.id.donViTinhAdd);
        btn_ThemVatTu = findViewById(R.id.btnThemMoiAdd);


        // callback button add vật tư
        themVatTu();
    }

    // add vật tư
    private void themVatTu(){
        btn_ThemVatTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenVatTu = txt_TenVatTu.getText().toString();
                String donViVatTu = txt_TenVatTu.getText().toString();

                // kiểm tra thông tin người dùng nhập vào nếu chỗ trống ko nhập thì xuất thông báo cho người dùng nhập vào
                if(tenVatTu.equals("") || donViVatTu.equals("") || txt_DonViTinh.getText().toString().equals("")){
                    Toast.makeText(ThemVatTu.this, "Nhập thiếu thông tin cần thêm", Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    Toast toast = Toast.makeText(ThemVatTu.this, "Thêm vật tư thành công", Toast.LENGTH_SHORT
                    );
                    toast.show();
                }
//
//                int idVatTu = StorageLocal.createIDNew();
//                int donGiaVatTu = Integer.parseInt(txt_TenVatTu.getText().toString());


//                VatTu vatTu = new VatTu(idVatTu, choseImage, tenVatTu, donGiaVatTu, donViVatTu);


            }
        });
    }
}