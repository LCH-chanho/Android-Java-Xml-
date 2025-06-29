package edu.telecom.bank;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

//-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
        Bank myAccount1 = new Bank("Lee", "010-1234-5343",33,
                "customer1","12345678",10000.0); //클래스1 생성
        myAccount1.deposit( 500000.0); // 입금메소드 실행
        myAccount1.withdraw( 50000.0); // 출금메소드 실행
        System.out.println("My current balance1 = " + myAccount1.getBalance()); //확인을위해 계좌 잔액 출력

        Bank myAccount2 = new Bank("Chan","010-2334-5343",40); //클래스2 생성
        myAccount2.deposit( 700000.0); // 입금메소드 실행
        myAccount2.withdraw( 70000.0); // 출금메소드 실행
        System.out.println("My current balance2 = " + myAccount2.getBalance()); //확인을위해 계좌 잔액 출력
//-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    }
}