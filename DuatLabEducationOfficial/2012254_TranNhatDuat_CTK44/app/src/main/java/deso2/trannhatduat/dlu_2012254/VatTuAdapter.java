package deso2.trannhatduat.dlu_2012254;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class VatTuAdapter extends ArrayAdapter<VatTu> {

    private Activity context;
    private int layout;
    private List<VatTu> dsVatTu;


    public VatTuAdapter(Activity context, int layout,List<VatTu> dsVatTu) {
        super(context, layout, dsVatTu);
        this.context = context;
        this.layout = layout;
        this.dsVatTu = dsVatTu;
    }

    @NonNull
    @Override
    public View getView(int i, @Nullable View view, @NonNull ViewGroup viewGroup) {
        LayoutInflater inflater = context.getLayoutInflater();

        view = inflater.inflate(layout, null);

        // ánh xạ
        ImageView imageVatTu =view.findViewById(R.id.imageVatTu);
        TextView txt_tenVatTu = view.findViewById(R.id.tenVatTu);
        TextView txt_giaVatTu = view.findViewById(R.id.GiaVatTu);
        TextView txt_dvtVatTu = view.findViewById(R.id.txt_dvt);
        ImageView icEdit = view.findViewById(R.id.iconEdit);

        // getData index list
        VatTu vatTu = dsVatTu.get(i);

        // hien thi du lieu len man hinh
        imageVatTu.setImageResource(vatTu.hinhVatTu);
        txt_tenVatTu.setText(vatTu.tenVatTu);
        txt_giaVatTu.setText(Converts.convertVND(vatTu.donGia));
        txt_dvtVatTu.setText(vatTu.donViTinh);

        // xử lý khi click vào icon edit
        icEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewGroup.getContext(), EditVatTu.class);
                ((MainActivity)viewGroup.getContext()).startActivity(intent);
            }
        });

        return view;

    }
}
