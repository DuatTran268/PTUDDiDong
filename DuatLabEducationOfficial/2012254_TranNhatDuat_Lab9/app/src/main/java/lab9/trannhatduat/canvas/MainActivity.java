package lab9.trannhatduat.canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    MyCanvas canvas;
    Button button;
    int[] colors = new int[]{
            Color.RED, Color.GREEN, Color.GRAY, Color.YELLOW, Color.CYAN, Color.WHITE
    };
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyCanvas canvas = findViewById(R.id.myCanvas);
        Button btnDraw = findViewById(R.id.btnDraw);
        btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // random chỉ số màu
                int colorIndex = random.nextInt(colors.length);
                // lấy màu trong mảng và gán cho giá trị màu trong lớp myCanvas
                MyCanvas.color = colors[colorIndex];
                canvas.invalidate();

            }
        });
    }

}