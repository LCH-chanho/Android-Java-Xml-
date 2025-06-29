package edu.telecom.bank;

//1. 은행 클래스를 정의한다. (이름, 연락처, 나이, 고객정보, 계좌번호, 예금액)
public class Bank {
    private String name;
    private String contact;
    private int age;
    private String customerInfo;
    private String accountNumber;
    private double balance;
//-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -

    //2. 은행 클래스의 인스턴스를 생성한다.
    //Bank 인스턴스1은 6개의 변수를 모두 받는다.
    public Bank(String name, String contact, int age, String customerInfo,
                String accountNumber, double balance) { //은행 인스턴스 (계좌정보상속)
        this.name = name;
        this.contact = contact;
        this.age = age;
        this.customerInfo = customerInfo;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
//-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -

    //Bank 인스턴스2는 3개의 변수(이름,연락처,나이)만 외부에서 정보를 받고
    // 고객정보, 계좌번호, 예금액은 해당 인스턴스2에서 정의한 정보를 불러온다.
    public Bank(String name, String contact, int age) { //은행 인스턴스 (계좌정보상속)
        this.name = name;
        this.contact = contact;
        this.age = age;
        // 고객정보 3개만 넣어주면 밑에는 자동으로 인스턴스 된다식으로 설계할수도 있다!
        this.customerInfo = "customer1";
        this.accountNumber = "12345678";
        this.balance = 10000.0;
    }
//-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -

    //3. 예금 메소드를 작성한다.
    //void쓰면 리턴값이 없기때문에 사용하지 않음.
    // 항상 상태값 리턴메소드를 만드는 버릇을 들이고, 동작이 잘 수행됐는지 피드백 받게 설계해야함.
    int deposit(double money) { //입금 메소드
        int status = 0;
        balance = balance + money; //기존balance + 입금액 -> setbalance

        return status;
    }
//-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -

    //4. 출금 메소드를 작성한다. (예외처리 해줘야 함.)
    int withdraw(double money) { //출금 메소드
        int status = 0;

        balance = balance - money; //예금에서 출금액을 뺀다.
        if (balance <0) //결과가 0보다작으면 출금못하므로
            balance = balance + money; //다시 더한다.
        return status;
    }
//-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -

    //5. 예금액을 반환해주는 매소드
    double getBalance() {
        return balance;
    }
}
