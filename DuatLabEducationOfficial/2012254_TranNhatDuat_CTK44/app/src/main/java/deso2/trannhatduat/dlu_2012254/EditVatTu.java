package deso2.trannhatduat.dlu_2012254;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EditVatTu extends AppCompatActivity {

    Button btnEditThongTin, btnXoaThongTin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vat_tu);

        btnEditThongTin = findViewById(R.id.btnSuaThongTin);
        btnXoaThongTin = findViewById(R.id.btnXoaThongTin);

        // bắt sự kiện buton Edit vật tư
        btnEditThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Toast toast = Toast.makeText(EditVatTu.this, "Sửa thông tin", Toast.LENGTH_SHORT
              );
               toast.show();
            }
        });

        // bắt sự kiện Delete thông tin vật tư
        btnXoaThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(EditVatTu.this, "Xoá thông tin", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
}