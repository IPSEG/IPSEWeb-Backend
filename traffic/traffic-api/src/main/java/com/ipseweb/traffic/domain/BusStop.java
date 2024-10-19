package com.ipseweb.traffic.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tb_bus_stop")
public class BusStop {

    @Id
    @Column(name = "bus_stop_id")
    private String busStopId;

    private String busStopName;

    private LocalDate informationCollectDate;

    private String city;

    private String detailCity;

    private String cityCode;

    private String mobileCode;

    private String longitude;

    private String latitude;
}
