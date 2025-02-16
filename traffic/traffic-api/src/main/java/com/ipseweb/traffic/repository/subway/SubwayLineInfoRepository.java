package com.ipseweb.traffic.repository.subway;

import com.ipseweb.traffic.domain.subway.SubwayLineInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubwayLineInfoRepository extends JpaRepository<SubwayLineInfo, Integer> {
}
