package edu.telecom.project_calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView textResult;
    String num1, num2;
    Integer result;
    Calculator cal1 = new Calculator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
        //익명 클래스 방식 (전통적인 방식):
        //mybutton.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        // 동작 내용
        //    }
        //});

        setTitle("초간단 계산기");

        edit1 = findViewById(R.id.Edit1);
        edit2 = findViewById(R.id.Edit2);
        btnAdd = findViewById(R.id.BtnAdd);
        btnSub = findViewById(R.id.BtnSub);
        btnMul = findViewById(R.id.BtnMul);
        btnDiv = findViewById(R.id.BtnDiv);
        textResult = findViewById(R.id.TextResult);

        btnAdd.setOnClickListener(v -> { //람다식 방식 (간결한 방식)
            num1 = edit1.getText().toString();  // 첫 번째 숫자 입력값을 가져옴
            num2 = edit2.getText().toString();  // 두 번째 숫자 입력값을 가져옴
            if (!num1.isEmpty() && !num2.isEmpty()) {  // 입력값이 비어있지 않은지 체크
                result = cal1.add(Integer.parseInt(num1), Integer.parseInt(num2));  // 더하기 계산
                textResult.setText("계산 결과 : " + result);  // 계산 결과 출력
            } else {
                textResult.setText("숫자를 입력하세요!");  // 입력값이 없으면 에러 메시지 출력
            }
        }); //람다식 방식 (간결한 방식)

        btnSub.setOnClickListener(v -> {
            num1 = edit1.getText().toString();
            num2 = edit2.getText().toString();
            if (!num1.isEmpty() && !num2.isEmpty()) {
                result = cal1.sub(Integer.parseInt(num1), Integer.parseInt(num2));  // 빼기 계산
                textResult.setText("계산 결과 : " + result);
            } else {
                textResult.setText("숫자를 입력하세요!");
            }
        });

        btnMul.setOnClickListener(v -> {
            num1 = edit1.getText().toString();
            num2 = edit2.getText().toString();
            if (!num1.isEmpty() && !num2.isEmpty()) {
                result = cal1.mul(Integer.parseInt(num1), Integer.parseInt(num2));  // 곱하기 계산
                textResult.setText("계산 결과 : " + result);
            } else {
                textResult.setText("숫자를 입력하세요!");
            }
        });

        btnDiv.setOnClickListener(v -> {
            num1 = edit1.getText().toString();
            num2 = edit2.getText().toString();
            if (!num1.isEmpty() && !num2.isEmpty()) {
                try {
                    double result = cal1.div(Integer.parseInt(num1), Integer.parseInt(num2));
                    textResult.setText("계산 결과 : " + result);  // 실수 결과 출력
                } catch (ArithmeticException e) {
                    textResult.setText(e.getMessage());  // 예외 메시지 출력
                }
            } else {
                textResult.setText("숫자를 입력하세요!");
            }
        });










    }
}