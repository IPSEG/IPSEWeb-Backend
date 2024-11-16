package com.ipseweb.traffic.service;

import com.ipseweb.traffic.dto.JoinUserRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void joinUser(JoinUserRequest joinUserRequest) {

        //1. userId 중복 확인

        //2. email 주소 패턴 검사
        //3. 비밀번호 salt로 해싱하여 저장

    }
}
