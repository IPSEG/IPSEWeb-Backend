package com.ipseweb.traffic.service;

import com.ipseweb.traffic.util.WebClientUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubwayService {

    WebClientUtil webClientUtil;

    public SubwayService(WebClientUtil webClientUtil) {
        this.webClientUtil = webClientUtil;
    }

    public void getStationArrivalInfo(String stationName) {

        String url = "http://swopenAPI.seoul.go.kr/api/subway/sample/json/realtimeStationArrival/0/10/양재";
        String response = webClientUtil.get(url);
    }
}
