package com.ipseweb.traffic.service.busarrival;

import com.ipseweb.error.Response;
import com.ipseweb.traffic.dto.busarrival.BusArrivalDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;


@SpringBootTest
class BusArrivalServiceTest {

    @Autowired
    BusArrivalService busArrivalService;


    String apiKey = "ldBxM3pP5Yn7M4b7Rva1vIySjE8KSyi5WfIlUsMwAWOfCnvq9o2r2jqoe12wQXw8SLWkZe8DlR2RGvgXIt2IRQ%3D%3D";

    @Test
    @DisplayName("버스 도착 정보 OPEN API 테스트")
    public void busArrivalInfoOpenApiTest() throws Exception {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1613000/ArvlInfoInqireService/getSttnAcctoArvlPrearngeInfoList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=ldBxM3pP5Yn7M4b7Rva1vIySjE8KSyi5WfIlUsMwAWOfCnvq9o2r2jqoe12wQXw8SLWkZe8DlR2RGvgXIt2IRQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*데이터 타입(xml, json)*/
        urlBuilder.append("&" + URLEncoder.encode("cityCode","UTF-8") + "=" + URLEncoder.encode("25", "UTF-8")); /*도시코드 [상세기능3 도시코드 목록 조회]에서 조회 가능*/
        urlBuilder.append("&" + URLEncoder.encode("nodeId","UTF-8") + "=" + URLEncoder.encode("DJB8001793", "UTF-8")); /*정류소ID [국토교통부(TAGO)_버스정류소정보]에서 조회가능*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }

    @Test
    @DisplayName("버스 도착 OPEN API 테스트2")
    public void busArrivalInfoOpenApiTest2() throws Exception {
        ResponseEntity<Response<List<BusArrivalDto.BusArrivalInfoResponse>>> response = busArrivalService.findBusArrivalInfo("DJB8001793", "25");
        System.out.println(response);
    }

}