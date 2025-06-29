package edu.telecom.animal_picture;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView text1, text2;
    CheckBox chkAgree;
    RadioGroup rGroup1;
    RadioButton rdolongkkam, rdolong, rdokkam;
    Button btnOK;
    ImageView imgPet;
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
        setTitle("애완동물 사진보기");

        text1 = findViewById(R.id.Text1);
        chkAgree = findViewById(R.id.ChkAgree);

        text2 = findViewById(R.id.Text2);
        rGroup1 = findViewById(R.id.Rgroup1);
        rdolongkkam = findViewById(R.id.Rdolongkkam);
        rdolong = findViewById(R.id.Rdolong);
        rdokkam = findViewById(R.id.Rdokkam);

        btnOK = findViewById(R.id.BtnOK);
        imgPet = findViewById(R.id.ImgPet);

        chkAgree.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) { //사용자가 체크하면 isChecked == true, 해제하면 false
                text2.setVisibility(View.VISIBLE);
                rGroup1.setVisibility(View.VISIBLE);
                btnOK.setVisibility(View.VISIBLE);
                imgPet.setVisibility(View.VISIBLE);
            } else {
                text2.setVisibility(View.INVISIBLE);
                rGroup1.setVisibility(View.INVISIBLE);
                btnOK.setVisibility(View.INVISIBLE);
                imgPet.setVisibility(View.INVISIBLE);
            }
        });

        btnOK.setOnClickListener(v -> { //"선택 완료" 버튼 눌렀을 때 실행될 코드 등록
            int checkedId = rGroup1.getCheckedRadioButtonId(); //현재 선택된 라디오버튼의 ID를 가져옴
            //아무것도 선택 안 했으면 -1이 나옴
            if (checkedId == R.id.Rdolongkkam) {
                imgPet.setImageResource(R.drawable.longkkam);
            } else if (checkedId == R.id.Rdolong) {
                imgPet.setImageResource(R.drawable.along);
            } else if (checkedId == R.id.Rdokkam) {
                imgPet.setImageResource(R.drawable.kkam);
            } else {
                Toast.makeText(getApplicationContext(), "선택된 항목이 없습니다!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

