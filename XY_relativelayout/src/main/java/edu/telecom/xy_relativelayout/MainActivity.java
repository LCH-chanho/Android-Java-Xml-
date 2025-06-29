package edu.telecom.xy_relativelayout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView x, y;
    Button center, left, right, up, down;
    xycal cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main); //요놈이 xml을 가지고와줘서 ID 할당가능함
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        x = findViewById(R.id.X);
        y = findViewById(R.id.Y);
        center = findViewById(R.id.CENTER);
        left = findViewById(R.id.LEFT);
        right = findViewById(R.id.RIGHT);
        up = findViewById(R.id.UP);
        down = findViewById(R.id.DOWN);

        cal = new xycal();  // 객체 생성

        // 버튼 클릭 이벤트 처리
        center.setOnClickListener(v -> {
            cal.reset();
            updateXY();
        });

        left.setOnClickListener(v -> {
            cal.moveX(-1);
            updateXY();
        });

        right.setOnClickListener(v -> {
            cal.moveX(1);
            updateXY();
        });

        up.setOnClickListener(v -> {
            cal.moveY(1);
            updateXY();
        });

        down.setOnClickListener(v -> {
            cal.moveY(-1);
            updateXY();
        });

    }

    // 화면의 X, Y 텍스트를 업데이트하는 함수
    private void updateXY() {
        //x.setText("X = " + cal.getX());
        //y.setText("Y = " + cal.getY());
        x.setText(String.format("X = %s", String.valueOf(cal.getX())));
        y.setText(String.format("Y = %s", String.valueOf(cal.getY())));
        // cal.get()으로 변환된 숫자값을 받아 문자로 바꾸고, 그 값을 스트링 포멧(%s)으로 표시해라.
        // 이렇게 해야 보안성이 높아짐.x,y값이 숫자아닌 다른값들어오면 에러발생해서 그다음 동작실행이 안되기때문에
    }
}
