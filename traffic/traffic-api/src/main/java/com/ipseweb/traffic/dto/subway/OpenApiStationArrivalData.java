package com.ipseweb.traffic.dto.subway;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenApiStationArrivalData {
    public Integer subwayId; // 지하철호선ID
    public String subwayName; // 지하철호선이름
    public String updnLine; // 상행하행구분
    public String trainLineNm; // 도착지방면
    public String trainNm; // 도착지이름
    public String barvlDt; // 열차도착예정시간
    public String btrainNo; // 열차번호
    public String bstatnId; // 종착지하철역ID
    public String bstatnNm; //종착지하철역명
    public String arvlMsg2;
}
