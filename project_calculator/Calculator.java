package edu.telecom.project_calculator;

public class Calculator {

    public Calculator() {

    }

    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public int sub(int num1, int num2) {
        return num1 - num2;
    }

    public int mul(int num1, int num2) {
        return num1 * num2;
    }

    public double div(int num1, int num2) { //나눗셈은 정수형으로 부족해서 실수 선언.
        if (num2 == 0) { //나누는 숫자가 0이면
            throw new ArithmeticException("0으로 나눌 수 없습니다."); // 0으로 나누는 경우 try-catch에 예외를 던진다
        } else {
            return (double) num1 / num2;  // num1을 double로 형변환하여 실수 나누기
        }
    }
}
