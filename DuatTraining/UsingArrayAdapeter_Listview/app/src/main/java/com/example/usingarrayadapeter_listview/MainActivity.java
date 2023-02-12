package com.example.usingarrayadapeter_listview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] items = {"Android", "IOS", "Windows phone", "Blackbery", "Symbian"};
    ListView lstMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstMain = findViewById(R.id.lstListMain);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//               chọn kiểu hiển thị danh sách lên màn hình với kiểu simple_list_item1
                this, android.R.layout.simple_list_item_1, android.R.id.text1, items
        );
        lstMain.setAdapter(adapter);
        lstMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapter.getItem(i);

//                hiển thị toast message khi chọn vào list view
                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_LONG).show();
            }
        });
    }
}