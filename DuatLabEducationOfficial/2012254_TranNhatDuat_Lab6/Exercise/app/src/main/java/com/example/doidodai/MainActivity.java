package com.example.doidodai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtNumber;
    private Spinner spnUnits;
    private TextView[] lblResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // ket noi cac doi tuong
        txtNumber = (EditText) findViewById(R.id.txtNumber);
        spnUnits = (Spinner) findViewById(R.id.spnUnit);
        lblResult = new TextView[]{
                (TextView) findViewById(R.id.lbl_haily),
                (TextView) findViewById(R.id.lbl_dam),
                (TextView) findViewById(R.id.lbl_kilometer),
                (TextView) findViewById(R.id.lbl_ly),
                (TextView) findViewById(R.id.lbl_met),
                (TextView) findViewById(R.id.lbl_Yard),
                (TextView) findViewById(R.id.lbl_foot),
                (TextView) findViewById(R.id.lbl_Inches),
        };


        // khoi tao doi tuong trung chuyen
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_spinner_item, units);

        // thiet lap cach hien thi cua spinner
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        spnUnits.setAdapter(adapter);

        // ham xu ly su kien
        spnUnits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ChangeLength();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // thiep lap ham xu ly su kien thay doi noi dung o nhap
        txtNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ChangeLength();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    // khai báo biến chứa danh sách đơn vị quy đổi độ dài
    private String[] units = {
            "Hải lý", "Dặm", "Kilometer", "Lý", "Met", "Yard", "Foot", "Inches"
    };

    private double[][] ratio = {
            {1, 1.5077945, 1.8520000, 20.2537183, 1852.0000, 2025.37183, 6076.11549, 72913.38583},
            {0.06897624, 1, 1.6093440, 17.6000000, 1609.3440, 1760.00000, 5280.00000, 63360.00000},
            {0.53995680, 0.62137119, 1, 10.9361330, 1000.0000, 1093.61330, 3280.83990, 39370.07874},
            {0.04937365, 0.05681818, 0.0914400, 1, 91.4400, 100.00000, 300.00000, 3600.00000},
            {0.00053996, 0.00062137, 0.0010000, 0.0109361, 1.0000, 1.09361, 3.28084, 39.37008},
            {0.00049374, 0.00056818, 0.0009144, 0.0100000, 0.9144, 1.00000, 3, 36},
            {0.00016458, 0.00018939, 0.0003048, 0.0033333, 0.3048, 0.33333, 1, 12},
            {0.00001371, 0.00001578, 0.0000254, 0.0002778, 0.0254, 0.02778, 0.08333, 1}
    };

    // hàm thay đổi độ dài
    private void ChangeLength(){
        // lấy ra vị trí của đơn vị được chọn
        int rowIndex = spnUnits.getSelectedItemPosition();
        if (rowIndex < 0) rowIndex = 0;
        // lay ra gia tri tu o nhap
        String inputNub = txtNumber.getText().toString();
        if (inputNub.isEmpty())
            inputNub = "0";
        // doi gia tri nhap sang so thuc
        double number = Double.parseDouble(inputNub);

        // tinh gia tri quy doi ung voi tung loai tien
        for (int i = 0; i < lblResult.length; i++){
            double temp = number * ratio[rowIndex][i];

            // hien thi ket qua len textview tuong ung
            lblResult[i].setText(String.valueOf(temp));
        }
    }





}