package edu.telecom.table_calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText Edit1, Edit2;
    TextView Result;
    Button btnAdd, btnSub, btnMul, btnDiv, btnReset;

    Button[] btns = new Button[10]; //0~9까지 버튼 10개 리스트 만들기
    int[] btnIDs = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9}; //버튼 id 리스트

    btncal cal = new btncal(); //계산기 클래스

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Edit1 = findViewById(R.id.Edit1);
        Edit2 = findViewById(R.id.Edit2);
        Result = findViewById(R.id.Result);

        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        btnReset = findViewById(R.id.btnReset);

        for (int i = 0; i < btnIDs.length; i++) {  //0~9 버튼 ID할당
            btns[i] = findViewById(btnIDs[i]);
        }

        //btnIDs를 클릭하면 edittext1 과 2에 실시간으로 해당숫자 입력할 것
        for (int i = 0; i < btnIDs.length; i++) {
            final int index = i;
            btns[i].setOnClickListener(v -> {   //0~9버튼 눌리면
                if (Edit1.isFocused()) {    //누를때 edittext1을 선택하고 눌렀다면
                    Edit1.setText(Edit1.getText().toString() + String.valueOf(index)); //원래 Edit1에 있던 문자에 index를 문자로바꿔서 더해 Edit1에 다시 넣는다.
                }
                else if (Edit2.isFocused()) {   //누를때 edittext2를 선택하고 눌렀다면
                    Edit2.setText(Edit2.getText().toString() + String.valueOf(index));
                }
                else {
                    Toast.makeText(getApplicationContext(), "먼저 Editext 둘중 하나를 선택하세요", Toast.LENGTH_SHORT).show();
                }
            });
        }
        //edittext1 과 2에 입력된 숫자를 가져와서 더하기 빼기 곱하기 나누기 연산 수행하기. -> 클래스가서 계산하고 결과를 가져오기.
        btnAdd.setOnClickListener(v -> {
            // 만약 둘중 하나라도 비어있는 상태에서 연산을 클릭하면 해당 edittext (1또는2)에 숫자를 기입하라는 경고 창 띄울것
            if (Edit1.getText().isEmpty() || Edit2.getText().isEmpty()) {
                if (Edit1.getText().isEmpty() && Edit2.getText().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "두개의 숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                }
                else if (Edit1.getText().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "첫번째 숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(getApplicationContext(), "두번째 숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                int a = Integer.parseInt(Edit1.getText().toString()); //문자를 정수로 바꿔서 a에 저장
                int b = Integer.parseInt(Edit2.getText().toString()); //문자를 정수로 바꿔서 b에 저장

                int result = cal.sum(a, b); //cal 클래스 sum 메소드 실행
                Result.setText(String.format("계산 결과 : %s ", String.valueOf(result)));
            }
        });

        btnSub.setOnClickListener(v -> {
            // 만약 둘중 하나라도 비어있는 상태에서 연산을 클릭하면 해당 edittext (1또는2)에 숫자를 기입하라는 경고 창 띄울것
            if (Edit1.getText().isEmpty() || Edit2.getText().isEmpty()) {
                if (Edit1.getText().isEmpty() && Edit2.getText().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "두개의 숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                }
                else if (Edit1.getText().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "첫번째 숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(getApplicationContext(), "두번째 숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                int a = Integer.parseInt(Edit1.getText().toString());
                int b = Integer.parseInt(Edit2.getText().toString());

                int result = cal.sub(a, b);
                Result.setText(String.format("계산 결과 : %s ", String.valueOf(result)));
                // 결과 텍스트를 셋하는데, 문자열포멧 %s로 출력한다. 계산된 실수값 result를 문자열로 변환하여 출력한다.
            }
        });

        btnMul.setOnClickListener(v -> {
            // 만약 둘중 하나라도 비어있는 상태에서 연산을 클릭하면 해당 edittext (1또는2)에 숫자를 기입하라는 경고 창 띄울것
            if (Edit1.getText().isEmpty() || Edit2.getText().isEmpty()) {
                if (Edit1.getText().isEmpty() && Edit2.getText().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "두개의 숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                }
                else if (Edit1.getText().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "첫번째 숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(getApplicationContext(), "두번째 숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }
            else {

                int a = Integer.parseInt(Edit1.getText().toString());
                int b = Integer.parseInt(Edit2.getText().toString());

                int result = cal.mul(a, b);
                Result.setText(String.format("계산 결과 : %s ", String.valueOf(result)));
                // 결과 텍스트를 셋하는데, 문자열포멧 %s로 출력한다. 계산된 실수값 result를 문자열로 변환하여 출력한다.
            }
        });

        btnDiv.setOnClickListener(v -> {
            // 만약 둘중 하나라도 비어있는 상태에서 연산을 클릭하면 해당 edittext (1또는2)에 숫자를 기입하라는 경고 창 띄울것
            if (Edit1.getText().isEmpty() || Edit2.getText().isEmpty()) {
                if (Edit1.getText().isEmpty() && Edit2.getText().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "두개의 숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                }
                else if (Edit1.getText().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "첫번째 숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(getApplicationContext(), "두번째 숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }
            else {

                if (Edit2.getText().toString().equals("0")) {
                    Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    int a = Integer.parseInt(Edit1.getText().toString());
                    int b = Integer.parseInt(Edit2.getText().toString());

                    double result = cal.div(a, b);
                    Result.setText(String.format("계산 결과 : %s ", String.valueOf(result)));
                }
            }
        });

        btnReset.setOnClickListener(view -> {
            Edit1.setText("");
            Edit2.setText("");
            Result.setText("계산 결과 : ");
        });

    }
}