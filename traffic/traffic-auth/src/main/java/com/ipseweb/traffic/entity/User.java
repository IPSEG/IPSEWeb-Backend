package com.ipseweb.traffic.entity;

import com.ipseweb.traffic.dto.JoinUserRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "user_info")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "user_id", length = 12, nullable = false)
    @Comment("사용자 아이디")
    private String userId;

    @Column(name = "user_name", length = 15, nullable = false)
    @Comment("사용자 이름")
    private String userName;

    @Column(name = "user_salt", nullable = false)
    @Comment("사용자 솔트")
    private String userSalt;

    @Column(name = "user_password", nullable = false)
    @Comment("사용자 비밀번호")
    private String userPassword;

    @Column(name = "user_previous_password", nullable = true)
    @Comment("사용자 비밀번호")
    private String userPreviousPassword;

    @Column(name = "user email", nullable = false)
    @Comment("사용자 이메일 주소")
    private String userEmail;

    public static User dtoToEntity(JoinUserRequest joinUserRequest, String decryptedPassword, String salt) {
        return User.builder()
                .userId(joinUserRequest.getUserId())
                .userName(joinUserRequest.getUserName())
                .userPassword(decryptedPassword)
                .userSalt(salt)
                .userEmail(joinUserRequest.getUserEmail())
                .build();
    }
}
