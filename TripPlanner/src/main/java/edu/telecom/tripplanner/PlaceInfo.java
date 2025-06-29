package edu.telecom.tripplanner;

//PlaceInfo는 각 장소의 정보를 저장하는 클래스이다.
//각 장소마다 들어가야할 모든 정보를 담는 틀 역할이다.

public class PlaceInfo {
    public String name;         // 장소 이름
    public String category;     // 산, 바다, 도시
    public String region;       // 국내, 해외
    public int transportCost;   // 왕복 교통비
    public int foodCost;        // 하루 식비 (3끼 기준)
    public int hotelCost;        // 하루 숙박비
    public int tourCost;        // 하루 관광비
    public String introText;    // 여행지 소개 글귀

    // 생성자: 모든 정보 초기화
    public PlaceInfo(String name, String category, String region, int transportCost, int foodCost, int hotelCost, int tourCost, String introText) {
        this.name = name;
        this.category = category;
        this.region = region;
        this.transportCost = transportCost;
        this.foodCost = foodCost;
        this.hotelCost = hotelCost;
        this.tourCost = tourCost;
        this.introText = introText;

    }

    public int getTransportCost() {
        return transportCost;
    }

    public int getFoodCost() {
        return foodCost;
    }

    public int getHotelCost() {
        return hotelCost;
    }

    public int getTourCost() {
        return tourCost;
    }

    public String getIntroText() {
        return introText;
    }
}
