package deso2.trannhatduat.dlu_2012254;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView_dsVatTu;
    FloatingActionButton btnThemVatTu;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ánh xạ
        listView_dsVatTu = findViewById(R.id.listViewVatTu);

        // tao du lieu danh sach cac vat tu
//        List<VatTu> listVatTu =new ArrayList<>();
        StorageLocal.listVatTu.add(new VatTu(1, R.drawable.capmang, "Cáp mạng", 6000, "mét"));
        StorageLocal.listVatTu.add(new VatTu(2, R.drawable.daunoi, "Đầu nối RJ45", 5000, "cái"));
        StorageLocal.listVatTu.add(new VatTu(3, R.drawable.giaya, "Giấy in A4", 80000, "ram"));

        // create adapter
        VatTuAdapter vatTuAdapter = new VatTuAdapter(
                this, R.layout.custom_listview_vattu, StorageLocal.listVatTu
        );

        listView_dsVatTu.setAdapter(vatTuAdapter);
//      call back function AddButton
        AddButton();
    }

    // add button show new activity
    private void AddButton(){
        btnThemVatTu = findViewById(R.id.btnAdd);
        btnThemVatTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ThemVatTu.class);
                view.getContext().startActivity(intent);
            }
        });
    }

}