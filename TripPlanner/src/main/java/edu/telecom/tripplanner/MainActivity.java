package edu.telecom.tripplanner;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnMountain, btnSea, btnCity;
    TextView txtPrice, txtIntro;
    ImageView checkOverlay; // 체크 이미지


    // [데이터1] 9개의 이미지뷰 ID를 배열로 정리
    int[] imageViewIds = {
            R.id.img1, R.id.img2, R.id.img3,
            R.id.img4, R.id.img5, R.id.img6,
            R.id.img7, R.id.img8, R.id.img9
    };

    // [데이터2] 여행지 이미지 리소스 이름 배열 (카테고리별)
    String[] mountainImages = {
            "seoraksan", "jirisan", "hallasan", "baekdu", "rocky", "fuji", "alps", "taebaeksan", "bukhansan"
    };
    String[] seaImages = {
            "jeju", "busan", "pohang", "yeosu", "ulsan", "maldives", "guam", "saipan", "hawaii"
    };
    String[] cityImages = {
            "seoul", "daejeon", "daegu", "gwangju", "newyork", "paris", "osaka", "tokyo", "budapest"
    };


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

        // 1. 버튼 및 텍스트 뷰 객체 초기화 (이미지 id 9개는 아래 setupImageClickEvents에서 for문으로 정의)
        btnMountain = findViewById(R.id.btnMountain);
        btnSea = findViewById(R.id.btnSea);
        btnCity = findViewById(R.id.btnCity);
        txtPrice = findViewById(R.id.txtPrice);
        txtIntro = findViewById(R.id.txtIntro);
        checkOverlay = findViewById(R.id.checkOverlay); // 체크 이미지 연결


        setTitle("TermProject 16039033 이찬호");


        // [함수1] 초기 카테고리별 3개이미지 랜덤 출력 함수
        initImageGrid();

        // [함수2] 이미지 클릭 시 경비, 장소설명 출력 함수
        setupImageClickEvents();


        // [함수3] 카테고리 선택에따른 이미지 9개 출력
        btnMountain.setOnClickListener(v -> {
            showImagesByCategory("산");
            clearTravelInfo();
            updateCategoryButtonColors("산");
            checkOverlay.setVisibility(View.GONE);
        });

        btnSea.setOnClickListener(v -> {
            showImagesByCategory("바다");
            clearTravelInfo();
            updateCategoryButtonColors("바다");
            checkOverlay.setVisibility(View.GONE);
        });

        btnCity.setOnClickListener(v -> {
            showImagesByCategory("도시");
            clearTravelInfo();
            updateCategoryButtonColors("도시");
            checkOverlay.setVisibility(View.GONE);
        });

        // 아래 선언한 리스트에 배열의 데이터를 리스트로 변환하여 넣는다.
        mountainList = Arrays.asList(mountainImages);
        seaList = Arrays.asList(seaImages);
        cityList = Arrays.asList(cityImages);

    } //onCreate

    // [함수 8] getCategoryBackgroundColor에서 쓸 리스트를 선언.
    List<String> mountainList;
    List<String> seaList;
    List<String> cityList;



    @Override //연결
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater(); //MenuInflater는 menu xml을 mainactivity에 연동해주는거임
        mInflater.inflate(R.menu.menu_trip, menu);
        //                    내 menu1을 menu 연동
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mountain) {
            showImagesByCategory("산");
            clearTravelInfo();
            updateCategoryButtonColors("산");
            checkOverlay.setVisibility(View.GONE);
            return true;
        } else if (item.getItemId() == R.id.sea) {
            showImagesByCategory("바다");
            clearTravelInfo();
            updateCategoryButtonColors("바다");
            checkOverlay.setVisibility(View.GONE);
            return true;
        } else if (item.getItemId() == R.id.city) {
            showImagesByCategory("도시");
            clearTravelInfo();
            updateCategoryButtonColors("도시");
            checkOverlay.setVisibility(View.GONE);
            return true;
        } else if (item.getItemId() == R.id.reset) {
            initImageGrid();
            clearTravelInfo();
            updateCategoryButtonColors(""); // 아무것도 선택 안 함
            checkOverlay.setVisibility(View.GONE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // [ 함수 정의 1: 초기 랜덤 이미지 출력 ]=  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =
    private void initImageGrid() {
        List<String> selected = new ArrayList<>(); //"selected" 빈 리스트 생성

        // 1. 카테고리별 리스트 준비
        List<String> mountain = Arrays.asList(mountainImages); //[데이터2]배열을 리스트형태로 바꿈 (셔플 메소드 사용을 위해)
        List<String> sea = Arrays.asList(seaImages);
        List<String> city = Arrays.asList(cityImages);

        // 2. 각 9개의 카테고리별 이미지를 랜덤으로 섞음
        Collections.shuffle(mountain);
        Collections.shuffle(sea);
        Collections.shuffle(city);

        // 3. 섞인 이미지를 각 카테고리별로 3개씩 선택
        selected.addAll(mountain.subList(0, 3)); //리스트 0,1,2 3개를 뽑아서 selected에 저장
        selected.addAll(sea.subList(0, 3));
        selected.addAll(city.subList(0, 3));

        // 4. 세로정렬 방식으로 순서 변경 (3x3 그리드)
        List<String> verticalSelected = new ArrayList<>(); //"verticalSelected" 빈 리스트 생성
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                verticalSelected.add(selected.get(row * 3 + col));
                    // col : 0일때 row : 0,1,2 -> 0,3,6 순서섞인 산 이미지 이름 3개
                    // col : 1일때 row : 0,1,2 -> 1,4,7 순서섞인 바다 이미지 이름 3개
                    // col : 2일때 row : 0,1,2 -> 2,5,8 순서섞인 도시 이미지 이름 3개
                        // selected는 이럼             verticalSelected는 이럼
                        // 산1(0)   산2(1)   산3(2)               산1(0), 바다1(3), 도시1(6)
                        //바다1(3)  바다2(4)  바다3(5)             산2, 바다2, 도시2
                        //도시1(6)  도시2(7)  도시3(8)             산3, 바다3, 도시3
            }
        }

        // 5. 실제 이미지뷰에 이미지 표시 및 태그 설정
        for (int i = 0; i < imageViewIds.length; i++) { //0~8까지 9회반복
            ImageView img = findViewById(imageViewIds[i]); //imageViewIds[i] = findViewById(R.id.img1~9)
                //img는 현재 처리할 1개의 ImageView를 가리킴
                //getIdentifier(리소스이름_문자열, 리소스타입, 패키지이름)
                //verticalSelected.get(i)는 랜덤하게 섞인 산,바다,도시 이미지별 이름들임
            int resId = getResources().getIdentifier(verticalSelected.get(i), "drawable", getPackageName());
                //즉, 카테고리별 지정된 이름을 가져와 그 이름과 동일한 파일의 이름을 drawable에서 찾음
            img.setImageResource(resId); //이미지 리소스를 ImageView에 연결
            img.setTag(verticalSelected.get(i)); // 이후 클릭 이벤트에 쓸 이름 저장
        }
      // getIdentifier()의 예시 : "seoraksan"이라는 이름을 가진 drawable 이미지 리소스를 찾아서 시스템이 이해하는 int형 리소스 ID로 변환해주는 함수
    } //=  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =




    // [ 함수 정의 2: 카테고리 버튼 클릭 시 이미지 9개 출력 ]=  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =
    private void showImagesByCategory(String category) {
        List<String> selected;

        // 1. 카테고리 이름에 따라 이미지 배열 선택
        switch (category) {
            case "산":
                selected = Arrays.asList(mountainImages); //산 카테고리의 이미지 이름 9개 넣기
                break;
            case "바다":
                selected = Arrays.asList(seaImages);
                break;
            case "도시":
                selected = Arrays.asList(cityImages);
                break;
            default:
                return; // 이상한 값 들어오면 걍 리턴
        }

        // 2. img ID 9개를 순차할당하면서 drawble폴더의 이미지를 연결하고 해당 이미지를 띄운다.
        for (int i = 0; i < imageViewIds.length; i++) { //선택된 카테고리의 9개이미지만큼 반복
            ImageView img = findViewById(imageViewIds[i]); //id 순차할당 1~9
            //selected의 9개 여행지 이름에 맞게 drawble에 연결
            int resId = getResources().getIdentifier(selected.get(i), "drawable", getPackageName());
            img.setImageResource(resId); // imgPet.setImageResource(R.drawable.longkkam);
            img.setTag(selected.get(i)); // 이미지뷰id.이미지리소스띄운다(이미지리소스이름)
        }
    } //=  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =



    // [ 함수 정의 3: 이미지 클릭 이벤트 처리 ]=  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =
    private void setupImageClickEvents() {
        // 1. img ID 9개가 순차적으로 img에 할당되어 반복
        for (int id : imageViewIds) { //id 변수에 이미지 id 1~9를 넣음
            ImageView img = findViewById(id); //id에 해당하는 ImageView를 가져옴

            // 2. for문이 돌다가 어떤 이미지 1개가 클릭되면
            img.setOnClickListener(v -> { //이미지 1~9를 순차적으로 클릭리스너 할당해서 처리

                // 3. 해당 이미지의 Tag를 placekey에 저장한다.
                String placeKey = (String) v.getTag();  // drawable name = PlaceData key
                if (placeKey == null) return; //테그 없으면 끝내기

                // 4. 다이얼로그 내 setSingleChoiceItems에서 바깥지역변수 쓰기위해 할당해준다. (체크 표시 이미지 띄우는게 목적이다)
                final View clickedView = v; //내부클래스 setSingleChoiceItems에서 바깥지역 변수 쓰기위해 final 할당

                // 5. 옵션으로 선택할 여행일자 3개를 배열로 정의한다.
                String[] options = {"당일", "2박3일", "일주일"}; //라디오버튼 목록으로 사용할 배열

                // 6. 다이얼로그 선언한다.
                new AlertDialog.Builder(this) //AlertDialog팝업창을 (this 이 화면에 띄우겠다)
                        .setTitle("여행 기간 선택") //다이얼로그 제목 선언
                        .setIcon(R.mipmap.airplane)
                        .setSingleChoiceItems(options, -1, (dialog, which) -> { //.라디오버튼고르기(위 옵션배열, 기본선택x, (사용자가 고른번호))

                            // 7. 다이얼로그 선택이후 선택된 이미지 위치 왼쪽아래에 체크 마크 이미지를 표시하는 메소드를 불러온다.
                            showCheckOverlay(clickedView);

                            // 8. 사용자가 선택한 옵션(여행기간)을 받고 tag정보가 들어있는 placekey와함께 calculateCost를 호출, 해당 여행지명을 info에 저장한다.
                            String duration = options[which]; //  duration = 배열원소[사용자가선택한]
                            int cost = CalcPrice.calculateCost(placeKey, duration); //CalcPrice(여행지명, 여행기간)
                            PlaceInfo info = PlaceData.placeMap.get(placeKey); //PlaceData(여행지명)을 PlaceInfo에 저장

                            // 9. 할인율 문구 설정 (선택기간에따라 할인율을 설명하기위해 discountInfo에 저장한다.)
                            String discountInfo; //할인율을 적용여부를 설명할 변수
                            switch (duration) {
                                case "2박3일":
                                    discountInfo = "\n(장기 여행 할인율 10% 적용됨)";
                                    break;
                                case "일주일":
                                    discountInfo = "\n(장기 여행 할인율 30% 적용됨)";
                                    break;
                                default:
                                    discountInfo = "\n(장기 여행 할인율 적용 안됨)";
                                    break;
                            }

                            // 10. 화면에 텍스트 출력
                                //10-1. 비용에 , 단위써서 가독성 좋게 만든다.
                            String formattedCost = String.format("%,d", cost);  // %d에 ,를 넣어서 출력하는 쉼표포맷

                                //SpannableString: 부분 스타일 적용이 가능한 문자열 클래스
                            SpannableString sp = new SpannableString("\"" + info.name + "\" 여행 예상 경비 " +
                                    "[" + duration + "] : " + formattedCost + "원" + discountInfo);

                            // 10-2. name 부분 볼드 처리
                            int nameStart = sp.toString().indexOf("\"" + info.name + "\""); //시작위치 0
                            int nameEnd = nameStart + info.name.length() + 2; //끝위치 0 + 문자길이 + 따옴표(2)
                            sp.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), nameStart, nameEnd, 0);

                            // 10-3. duration 부분 볼드 처리
                            int durStart = sp.toString().indexOf("[" + duration + "]");
                            int durEnd = durStart + duration.length() + 2;
                            sp.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), durStart, durEnd, 0);

                            // 10-4. 출력
                            txtPrice.setText(sp);
                            txtIntro.setText("여행지 소개:\n" + info.introText); //info 여행지소개를 불러와서 띄워준다.

                            // 11. 선택된 이미지에 맞는 배경색 적용
                            int bgColor = getCategoryBackgroundColor(placeKey);
                            txtPrice.setBackgroundTintList(ColorStateList.valueOf(bgColor));
                            txtIntro.setBackgroundTintList(ColorStateList.valueOf(bgColor));


                            dialog.dismiss();  // 선택하면 바로 닫기
                        })
                        .setNegativeButton("닫기", null) // 맨하단에 닫기 버튼 만들기
                        .show(); //화면에 보여주기
            }); //이미지클릭 이벤트
        } //for문
    } //=  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =



    // [ 함수 정의 4: 여행정보 초기화 ]=  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =
    private void clearTravelInfo() {
        txtPrice.setText("여행경비는");
        txtIntro.setText("여행지 소개는");
        txtPrice.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white)));
        txtIntro.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white)));

    }//=  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =



    // [ 함수 정의 5: 누른 버튼의 색상 변경 ]=  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =
    private void updateCategoryButtonColors(String selected) {
        // 모두 기본 색으로 초기화
        // 버튼ID.버튼 배경 색 변경(색상 정보(ColorStateList 형식으로 감쌈(정수 색상으로 바꿈(현재 액티비티에서 색 가져옴))))
        btnMountain.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.btn_default)));
        btnSea.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.btn_default)));
        btnCity.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.btn_default)));

        // 기본 배경색은 흰색으로 초기화
        int bgColor = ContextCompat.getColor(this, R.color.white);

        // 배경색 흰색으로 초기화
        txtPrice.setBackgroundTintList(ColorStateList.valueOf(bgColor));
        txtIntro.setBackgroundTintList(ColorStateList.valueOf(bgColor));


        // 선택된 버튼만 색 변경
        switch (selected) {
            case "산":
                btnMountain.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.btn_mountain)));
                break;
            case "바다":
                btnSea.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.btn_sea)));
                break;
            case "도시":
                btnCity.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.btn_city)));
                break;
            default: // 아무것도 선택 안 한 경우
                break;

        }
    }//=  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =



    // [ 함수 정의 6: dpToPx() 유틸 메서드 ]=  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =
    private int dpToPx(int dp) { //dp를 px로 바꿔줌, 디바이스별로 해상도, 밀도 전부달라서 dp로 레이아웃 짠이후 디바이스에 맞게 px로 환산해야함
        return (int) TypedValue.applyDimension( //dp,sp 등의 단위값을 float 픽셀 값으로 바꿔주는 표준 API
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
                //dp단위, 우리가 변환할 숫자값, 해당 디바이스 화면 밀도정보 가져옴
    }//=  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =



    // [ 함수 정의 7: 선택된 이미지에 체크이미지 표시 ]=  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =
    private void showCheckOverlay(final View clickedView) {
        checkOverlay.setVisibility(View.INVISIBLE); // 초기화

        clickedView.post(() -> { //post는 클릭된 뷰의 UI 작업 큐에 해당 코드를 등록하는것. (즉, 현재 레이아웃이 끝난 직후 실행되도록 미뤄줌) getX(), getHeight() 안정화 위해
            checkOverlay.setX(clickedView.getX()); //체크 이미지를 클릭된 이미지와 같은 X 좌표에 둠. (왼쪽 끝)
            checkOverlay.setY(clickedView.getY() + clickedView.getHeight() - dpToPx(40)); // (클릭된이미지 상단위치 + 클릭된 이미지 높이 -  체크이미지 예상높이)
            checkOverlay.bringToFront(); //checkOverlay를 화면 맨 앞으로 가져옴
            checkOverlay.setVisibility(View.VISIBLE); //화면에 표시
        });
    }//=  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =



    // [ 함수 정의 8: 선택된 이미지에 따른 경비,소개 배경색 표시 ]=  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =
    private int getCategoryBackgroundColor(String placeKey) {
        String categoryType = "";

        if (mountainList.contains(placeKey)) {
            categoryType = "산";
        } else if (seaList.contains(placeKey)) {
            categoryType = "바다";
        } else if (cityList.contains(placeKey)) {
            categoryType = "도시";
        }
        switch (categoryType) {
            case "산":
                return ContextCompat.getColor(this, R.color.btn_mountain);
            case "바다":
                return ContextCompat.getColor(this, R.color.btn_sea);
            case "도시":
                return ContextCompat.getColor(this, R.color.btn_city);
            default:
                return ContextCompat.getColor(this, R.color.white); // fallback
        }
    }



} //MainActivity