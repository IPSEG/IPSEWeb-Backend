package com.ipseweb.traffic.domain.subway;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "subway_line_info")
public class SubwayLineInfo {
    @Id
    @Column(name = "line_id")
    private int lineId;

    @Column(name = "line_name")
    private String lineName;
}
