package edu.telecom.xy_relativelayout;

public class xycal {

    int x = 0;
    int y = 0;

    // x 좌표 변경 함수
    void moveX(int value) {

        x += value;
    }

    // y 좌표 변경 함수
    void moveY(int value) {

        y += value;
    }

    // x, y 초기화 함수
    void reset() {
        x = 0;
        y = 0;
    }

    // x 값 가져오는 함수
    int getX() {

        return x;
    }

    // y 값 가져오는 함수
    int getY() {

        return y;
    }
}