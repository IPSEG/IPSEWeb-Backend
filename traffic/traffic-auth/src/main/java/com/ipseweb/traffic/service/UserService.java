package com.ipseweb.traffic.service;

import com.ipseweb.traffic.dto.JoinUserRequest;
import com.ipseweb.traffic.entity.User;
import com.ipseweb.traffic.repository.UserRepository;
import com.ipseweb.traffic.util.SecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@AllArgsConstructor
@Service
public class UserService {

    private final SecurityUtil securityUtil;
    private final UserRepository userRepository;
    private static final String EMAIL_PATTERN = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

    public ResponseEntity<Object> joinUser(JoinUserRequest joinUserRequest) {

        //1. userId 중복 확인
        if(userRepository.existsById(joinUserRequest.getUserId())) {
            // 에러리턴 추후 정의 필요
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        //2. email 주소 패턴 검사
        if(!Pattern.matches(EMAIL_PATTERN, joinUserRequest.getUserEmail())) {
            // 에러리턴 추후 정의 필요
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //3. rsa 암호화된 password 복호화
        String decryptedPassword = securityUtil.getDecryptedPassword(joinUserRequest.getRandomString(), joinUserRequest.getEncryptedPassword());

        //4. 비밀번호 salt로 해싱하여 저장
        String salt = securityUtil.generateSalt();
        String hashedPassword = securityUtil.hashPassword(decryptedPassword, salt);

        userRepository.save(User.dtoToEntity(joinUserRequest, hashedPassword, salt));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
