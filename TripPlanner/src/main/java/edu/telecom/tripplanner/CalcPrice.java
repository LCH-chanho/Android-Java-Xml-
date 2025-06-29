package edu.telecom.tripplanner;

public class CalcPrice {

    /**
     * 여행 경비 계산 메서드
     * @param placeName 선택한 장소 이름
     * @param duration 여행 기간 - "당일", "2박3일", "일주일"
     * @return 총 예상 경비 (int)
     */

    public static int calculateCost(String placeName, String duration) { // 비용계산 메소드에서는 장소 이름과 여행 기간을 변수로 받는다.

        // 1. 장소 정보 가져오기 (HashMap에서 키값으로 찾기)
        PlaceInfo info = PlaceData.placeMap.get(placeName); // PlaceData 클래스의 placeMap에서 장소 이름에 해당하는 정보를 가져옴

        // 2. 존재하지 않는 장소일 경우 예외 처리
        if (info == null) {
            return -1;
        }

        // 3. 왕복 교통비는 고정 요소이므로 먼저 더해둠
        int total = info.getTransportCost();

        // 4. 기간과 할인율 초기 설정
        int days = 1;
        double discount = 0.0;

        // 5. 여행 기간 조건에 따른 일수와 할인율 설정
        switch (duration) {
            case "2박3일":
                days = 3;
                discount = 0.10;
                break;
            case "일주일":
                days = 7;
                discount = 0.30;
                break;
            default: // "당일"
                days = 1;
                discount = 0.0;
                break;
        }

        // 6. 가변 비용 계산 (식비 + 숙박비 + 관광비) * 일수
        double variableCost = (info.getFoodCost() + info.getHotelCost() + info.getTourCost()) * days;

        // 7. 할인율 적용
        variableCost = variableCost * (1.0 - discount);

        // 8. 최종 비용 계산 (총합 = 교통비 + 할인 적용된 가변 비용)
        total += (int) Math.round(variableCost); // 반올림 후 정수 변환

        return total;
    }
}
